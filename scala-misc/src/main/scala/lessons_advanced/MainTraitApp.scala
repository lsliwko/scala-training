package lessons


trait Person {
  var id : Int = 123  //trait can have variables

  def wave() = println("Les is waving") //trait can have functions
}

class Les extends Person {

  override def toString: String = s"Les has id $id"

}

object MainTraitApp extends App {

  //traits support multiple-inheritance (AVOID traits with the same def and variables !!!!)
  //for trait linearization: https://www.geeksforgeeks.org/trait-linearization-in-scala/

  val les = new Les
  les.id  = 200
  println {
    les
  }

  les.wave()

}
