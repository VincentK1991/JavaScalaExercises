package assignment3

// there are teo ways to create executable program.
// 1. create main method inside object that takes Array[String] as input returns Unit
// This is similar to `public static void main(String[] arguments) ` in Java
// or
// 2. Use main object extends App and write stuff inside the Main object
// This is exclusively in Scala.
object Main extends App {
  val list_marathon:List[(String,Int)] = List(("Elena",341),("Thomas",273),("Hamilton",278),
                                              ("Suzie",329),("Phil",445),("Matt",402),
                                              ("Alex",388),("Emma",275),("John",243),
                                              ("James",334),("Jane",412),("Emily",393),
                                              ("Daniel",299),("Neda",343),("Aaron",317),("Kate",205))

  def findNthRunner(num:Int):(String,Int) = list_marathon.sortBy(_._2).toList(num)
  def findBestRunner:(String,Int) = findNthRunner(0)
  def findSecondRunner:(String,Int) = findNthRunner(1)

  println("--start the program--")
  println(" ")
  println(">> the best runner is "+ findBestRunner._1 + " who took " + findBestRunner._2 + " min")
  println(">> the second best runner is "+ findSecondRunner._1 + " who took " + findSecondRunner._2 + " min")
  println(" ")
  println("--end of the program--")
}
