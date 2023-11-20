package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._

class RestControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "RestController GET /word" should {

    "increment 'sample-one' word count by 1" in {
      val response = route(app, FakeRequest(GET, "/word/sample-one")).get
      status(response) mustBe OK
      contentAsString(response) must be ("1")
    }


    // the candidate should provide a test for multiple increments for a word
    "increment 'sample-two' word count by 2" in {
      val responseOne = route(app, FakeRequest(GET, "/word/sample-two")).get
      status(responseOne) mustBe OK
      contentAsString(responseOne) must be("1")

      val responseTwo = route(app, FakeRequest(GET, "/word/sample-two")).get
      status(responseTwo) mustBe OK
      contentAsString(responseTwo) must be("2")
    }

    // the candidate should provide a test for empty word (incorrect api call)
    "return bad request when no word is provided" in {
      val response = route(app, FakeRequest(GET, "/word/")).get
      status(response) mustBe NOT_FOUND
    }

    // the candidate should provide at least one test for a word with whitespace character
    "return bad request when word contains whitespaces" in {
      val response = route(app, FakeRequest(GET, "/word/sample\n")).get
      status(response) mustBe BAD_REQUEST
    }

    // the candidate should provide at least one test for a word with non-ascii character
    "return bad request when word contains non-ascii characters" in {
      val response = route(app, FakeRequest(GET, "/word/sample∞")).get
      status(response) mustBe BAD_REQUEST
    }
  }

  //the candidate should provide at least one test for 'reset' api
  "RestController GET /reset" should {

    "increment 'sample-one' word count by 1 and then reset" in {
      route(app, FakeRequest(GET, "/reset")).get
      val responseOne = route(app, FakeRequest(GET, "/word/sample-one")).get
      status(responseOne) mustBe OK
      contentAsString(responseOne) must be("1")

      val responseReset = route(app, FakeRequest(GET, "/reset")).get
      status(responseReset) mustBe OK

      val responseTwo = route(app, FakeRequest(GET, "/word/sample-one")).get
      status(responseTwo) mustBe OK
      contentAsString(responseTwo) must be("1")
    }

  }

  //Note: there are multiple scenarios to be tested here, e.g. negative count, only-first word, etc.
  "RestController GET /top" should {

    //the candidate should provide at least one test for 'top' api with different counts
    "increment 'sample-one' word count by 1, 'sample-two' by 2, and then call 'top' with limits 5,1" in {
      route(app, FakeRequest(GET, "/reset")).get
      route(app, FakeRequest(GET, "/word/sample-one")).get

      val requestTwo = FakeRequest(GET, "/word/sample-two")
      route(app, requestTwo).get
      route(app, requestTwo).get
      route(app, requestTwo).get

      val responseTop5 = route(app, FakeRequest(GET, "/top/5")).get
      status(responseTop5) mustBe OK

      val responseTop5Json = Json.parse(contentAsString(responseTop5))
      (responseTop5Json \ "sample-one").get.toString mustBe "1"
      (responseTop5Json \ "sample-two").get.toString mustBe "3"
      (responseTop5Json \ "sample-three").isEmpty mustBe true

      val responseTop1 = route(app, FakeRequest(GET, "/top/1")).get
      status(responseTop1) mustBe OK

      val responseTop1Json = Json.parse(contentAsString(responseTop1))
      (responseTop1Json \ "sample-one").isEmpty mustBe true
      (responseTop1Json \ "sample-two").get.toString mustBe "3"
      (responseTop1Json \ "sample-three").isEmpty mustBe true
    }

    //the candidate should provide at least one test for 'top' api with different counts
    "increment 'sample-one' word count by 2, 'sample-two' by 2, 'sample-two' by 3, and then call 'top' with limit 1" in {
      val requestOne = FakeRequest(GET, "/word/sample-one")
      route(app, requestOne).get
      route(app, requestOne).get

      val requestTwo = FakeRequest(GET, "/word/sample-two")
      route(app, requestTwo).get
      route(app, requestTwo).get

      val requestThree = FakeRequest(GET, "/word/sample-three")
      route(app, requestThree).get


      val responseTop2 = route(app, FakeRequest(GET, "/top/1")).get
      status(responseTop2) mustBe OK

      val responseTop2Json = Json.parse(contentAsString(responseTop2))
      (responseTop2Json \ "sample-two").get.toString mustBe "2"
      (responseTop2Json \ "sample-one").get.toString mustBe "2"
      (responseTop2Json \ "sample-three").isEmpty mustBe true
    }
  }

}
