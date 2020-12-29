package assignment2

//import foocorporation.FooCorporation // import the class

// in the driver class, everthing has to be define inside the driver
// or instantiated inside from imported package
object Main extends App{
  case class Employee(basepay:Double,hourwork:Int)
  val fooObject = new FooCorporation

  // define each employee from case class.
  // when use case class, we do not need new
  val employee1 = Employee(7.5, 35)
  val employee2 = Employee(8.20, 47)
  val employee3 = Employee(10.00, 73)

  val listemployee = List(employee1,employee2,employee3)


  println("==========Start the program============")
  println(" ")
  for (item <- listemployee){
    try {
      fooObject.checkBasePay(item.basepay)
      fooObject.checkNumHour(item.hourwork)
      print("wages are = "+fooObject.calculateWage(item.basepay, item.hourwork))
      println(" ")
      //fooObject.calculateWage(item.basepay, item.hourwork)
    }
    catch{
      case err: ArithmeticException => println(err)
    }
  }
  println(" ")
  println("--------Finish the program------------")
}
