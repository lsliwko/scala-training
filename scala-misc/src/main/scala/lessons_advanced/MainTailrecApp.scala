package lessons_advanced

object MainTailrecApp extends App {

  // Fibonacci
  //  F(0) = 1
  //  F(1) = 1
  //  F(n) = F(n - 1) + F(n - 2)


  //tail recursion is an alternative for loops

  def fibonacci(number: Int): BigInt = {

    @annotation.tailrec //ensures compiler to use recursion optimisation (so no more StackoverflowException)
    def fibonacciInner(number: Int, currentNumber: BigInt, nextNumber: BigInt): BigInt =
      number match {
        case 0 => currentNumber
        case 1 => nextNumber

        //recurrent call can be optimised
        //if my last step in function is just to call itself, then compiler can optimise it into loop
        //that what tailrec checks, i.e. if compiler optimised it
        case _ => fibonacciInner(number - 1, nextNumber, currentNumber + nextNumber)

        ////recurrent call cannot be optimised
        //case _ => fibonacciInner(number - 1, nextNumber, currentNumber + nextNumber) + 5 //after executing function we still need to add 5
      }

    fibonacciInner(number, 0, 1)
  }

  @annotation.tailrec
  def total(currentTotal: Int, elements: List[Int]) : Int = {
    elements match {
      case Nil => currentTotal
      case headElement :: tailElements => total(headElement + currentTotal, tailElements)
    }
  }

  //List(1, 2, 3, 4, 5).sum // <- alternative :)
  println {
    total(0, List(1, 2, 3, 4, 5))
  }

  println {
    fibonacci(10)
  }

}
