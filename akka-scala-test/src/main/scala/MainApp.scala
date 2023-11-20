import akka.actor.ActorSystem
import akka.stream.{Materializer, RestartSettings}
import akka.stream.scaladsl.{RestartSource, Sink, Source}
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
    objectsMap.get(eventData.objectId) match {
      case None => Left(s"${eventData.objectId} is not a valid Object ID - event ${eventData.eventId} not applied")
      case Some(objectData) =>
        eventData.variableName match {
          case "VarA" =>
            objectData.varA = eventData.variableValue
            println(s"Updating ${eventData.objectId}: ${eventData.variableName} = ${eventData.variableValue}")
            Right(true)
          case "VarB" =>
            objectData.varB = eventData.variableValue
            println(s"Updating ${eventData.objectId}: ${eventData.variableName} = ${eventData.variableValue}")
            Right(true)
          case "VarC" =>
            objectData.varC = eventData.variableValue
            println(s"Updating ${eventData.objectId}: ${eventData.variableName} = ${eventData.variableValue}")
            Right(true)
          case _ => Left(s"${eventData.variableName} is not a valid Object attribute - event ${eventData.eventId} not applied")
        }
    }
  }

  val numberOfSubstreams = Runtime.getRuntime.availableProcessors()

  //Akka Streams
  //TODO process events in parallel (make sure events are processed in right order per object)
  val resultFuture =
    RestartSource.onFailuresWithBackoff(
      RestartSettings(1.second, 10.seconds, 0.1)
    )(() => Source.fromIterator(() => eventsList.iterator))
      .groupBy(numberOfSubstreams, event => event.objectId.hashCode.abs % numberOfSubstreams)
      .map { event => processEvent(event) }
      .mergeSubstreams  //preserves order
      .runWith(Sink.ignore) (materializer)

  //STREAM:  event1(A), event2(B), event3(A), event4(C), event5(A)
  //SUBSTREAM (in parallel):
  //0 (A and B): event1(A), event3(B), event3(A), event5(A)
  //1 (C): event4(C)
  //MERGE SUBSTREAMS:
  // event1(A), event2(B), event3(A), event4(C), event5(A)



  Await.result(resultFuture, 10.seconds)
  println("DONE")

  actorSystem.terminate()
}


//TODO apply / unapply methods on companion Object