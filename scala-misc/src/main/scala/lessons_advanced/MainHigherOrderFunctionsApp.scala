package lessons_advanced

object MainHigherOrderFunctionsApp extends App {

  //higher-order function = a function that works with a function
  def apply(value: Int, functionArgument: Int => String): String =
    functionArgument(value)

  println {
    apply(
      functionArgument = NumberWordConverter.convert,   //Java classes works with Scala code
      value = 11
    )
  }

}
