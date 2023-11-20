package controllers

import com.google.common.base.CharMatcher
import com.google.common.util.concurrent.AtomicLongMap
import play.api.libs.json.Json
import play.api.mvc._

import javax.inject._
import scala.collection.immutable.ListMap
import scala.jdk.CollectionConverters._

@Singleton
class RestController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  // Candidates might use any mutable map from Scala or Java packages
  // Here I am using a map from Google's Guava library as it natively supports atomic operations
  private val wordCountersMap  = AtomicLongMap.create[String]

  def word(word: String): Action[AnyContent] = Action {
    Ok(s"something: $word")
  }

  def reset(): Action[AnyContent] = Action {
    wordCountersMap.clear
    Ok
  }

  def top(limit: Int): Action[AnyContent] = Action {
    if (limit<0) {
      BadRequest("Count cannot be negative")  // check for negative values
    } else {
      //this call is tricky as we might need to return more words that limit
      val wordCountersSeq = wordCountersMap.asMap.asScala.toSeq
      val includeCountsSet = wordCountersSeq.sortBy(-_._2).map(_._2).take(limit).toSet
      Ok(Json.toJson(
        ListMap(wordCountersSeq.filter { pair => includeCountsSet.contains(pair._2) }: _*).view.mapValues(_.longValue())
      ))
    }
  }

}
