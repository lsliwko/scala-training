package lessons


class Customer(val name: String)

object Customer {

  def apply(name: String) = new Customer(name)

  //data extractor
  def unapply(customer: Customer): Option[String] = Some(customer.name)

}


object MainCaseMatchesClass extends App {

  val customer : Any = new Customer("Tom")
  //val customer : Any = Customer("Tom")  //apply method lets us skip new keyword

  customer match {

    case Customer("Les") => println("1: Les")                        //matching on extracted value
    case Customer(name) if name == "Les" => println(s"2: ${name}")   //guards
    case reference@Customer(_) => println(s"3: ${reference}")        //carrying reference to object

    case None => println("4: None")                                    //matching on class type

    case _ => println("default")                                       //matching default

  }
}


