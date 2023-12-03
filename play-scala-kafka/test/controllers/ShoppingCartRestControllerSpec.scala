package controllers

import com.google.gson.GsonBuilder
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._

class ShoppingCartRestControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  private val gson = new GsonBuilder()
    .create

  "ShoppingCartRestController GET /shopping-cart/*" should {

    def createShoppingCartId: Long = {
      val response = route(app, FakeRequest(GET, "/shopping-cart/new")).get
      status(response) mustBe OK
      val responseString = contentAsString(response)
      responseString must fullyMatch regex """^\+?0*[1-9]+[0-9]*$"""
      responseString.toLong
    }

    "create new empty shopping cart" in {
      val shoppingCartId = createShoppingCartId
      val response = route(app, FakeRequest(GET, s"/shopping-cart/${shoppingCartId}/items/all")).get
      status(response) mustBe OK
      val responseString = contentAsString(response)
      //validate shopping cart is empty
      //val shoppingCart = gson.fromJson(responseString, classOf[ShoppingCart])
      //...
    }

    "add and remove items" in {
      val shoppingCartId = createShoppingCartId
      val response = route(app, FakeRequest(GET, s"/shopping-cart/${shoppingCartId}/items/add/1")).get
      status(response) mustBe OK
      val responseString = contentAsString(response)
      //validate shopping cart has apple
      //val shoppingCart = gson.fromJson(responseString, classOf[ShoppingCart])
      //...
    }

  }

}
