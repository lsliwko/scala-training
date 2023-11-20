import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.scaladsl.{Sink, Source}
import com.akkatest.domain.{EventData, ObjectData}
import org.supercsv.io.CsvBeanReader
import org.supercsv.prefs.CsvPreference

import java.io.{InputStream, InputStreamReader}
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.util._


object MainApp extends App {

  val actorSystem = ActorSystem()
  implicit val materializer = Materializer(actorSystem)

  //load objects from objects.csv
  val objectsList = {
    val objectsList = new scala.collection.mutable.ListBuffer[ObjectData]()
    val objectsInputStream: InputStream = MainApp.getClass.getResourceAsStream("objects.csv")
    val beanReader = new CsvBeanReader(new InputStreamReader(objectsInputStream), CsvPreference.STANDARD_PREFERENCE)
    val headers: Array[String] = beanReader.getHeader(true)

    var objectDataOption = Option.empty[ObjectData]
    do {
      objectDataOption = Option(beanReader.read(classOf[ObjectData], headers: _*))
      objectsList ++= objectDataOption
    } while (objectDataOption.isDefined)
    objectsList
  }

  //load events from objects.csv
  val eventsList = {
    val eventsList = new scala.collection.mutable.ListBuffer[EventData]()
    val eventsInputStream: InputStream = MainApp.getClass.getResourceAsStream("events.csv")
    val beanReader = new CsvBeanReader(new InputStreamReader(eventsInputStream), CsvPreference.STANDARD_PREFERENCE)
    val headers: Array[String] = beanReader.getHeader(true)

    var eventDataOption = Option.empty[EventData]
    do {
      eventDataOption = Option(beanReader.read(classOf[EventData], headers: _*))
      eventsList ++= eventDataOption
    } while (eventDataOption.isDefined)
    eventsList
  }

  println(objectsList)
  println(eventsList)

  //create map (objectId, object)
  val objectsMap = objectsList.map { objectData => (objectData.objectId, objectData) }.toMap

  def processEvent(eventData: EventData): Either[String, Boolean] = {
    val updatedObject = objectsMap.get(eventData.objectId)

    val result = updatedObject match {
      case None => Left(s"[${eventData.eventId}] Object not found") //error
      case Some(objectData) => {
        eventData.variableName match {
          case "VarA" =>
            println(s"[${eventData.eventId}] Object A: ${objectData.varA} -> ${eventData.variableValue}")
            objectData.varA = eventData.variableValue
            Right(true)
          case "VarB" =>
            println(s"[${eventData.eventId}] Object B: ${objectData.varB} -> ${eventData.variableValue}")
            objectData.varB = eventData.variableValue
            Right(true)
          case "VarC" =>
            println(s"[${eventData.eventId}] Object C: ${objectData.varC} -> ${eventData.variableValue}")
            objectData.varC = eventData.variableValue
            Right(true)
          case _ => Left(s"[${eventData.eventId}] Variable Name Not Found.")  //error
        }
      }
    }

    result
  }


  val numberOfSubstreams = Runtime.getRuntime.availableProcessors //or objectsList.length

  //Akka Streams
  val resultFuture = Source.fromIterator(() => eventsList.iterator)
    .groupBy(                                 //split into substreams
      numberOfSubstreams,
      //use object id's hashcode as substream identifier (0,1,2...)
      { event => event.objectId.hashCode.abs % numberOfSubstreams }
    )
    .map { event => processEvent(event) }
    .mergeSubstreams                          //merge into one stream
    .runWith(Sink.ignore)(materializer)

  Await.result(resultFuture, 10.seconds)
  println("DONE")

  //QUEUE (1:objA, 2:objB, 3:objA, 4:objB, 5:objB, 6:objA)
  //SUB-QUEUES (processed in parallel):
  // objA (by hashcode, e.g. "1"): 1,3,6
  // objB (by hashcode, e.g. "0"): 2,4,5


  actorSystem.terminate()
}