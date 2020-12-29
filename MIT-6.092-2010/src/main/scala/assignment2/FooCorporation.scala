package assignment2

class FooCorporation{
  val minimumWage = 8.0
  val extraRate = 1.5
  val maxTime = 60

  println("create FooCorporation instant:  minimum wage = "+  minimumWage+ " extra rate = "+ extraRate+ " maximum time = "+ maxTime)

  def checkBasePay(basePay:Double):Unit = {
    if (basePay < minimumWage){
      //println("ERROR: basepay is lower than minimum wage")}
      throw new ArithmeticException("Your basepay is lower than the minimum wage")
    }
  }

  def checkNumHour(hourWork:Int):Unit = {
    if (hourWork > maxTime){
      throw new ArithmeticException("You work too much !!")
    }
  }

  def calculateWage(basePay:Double,hourWork:Int):Double = {
    val extratime = math.max(0,(hourWork - 40))
    val basetime = math.min(40,hourWork)

    return basePay*basetime + extraRate*basePay*extratime
  }
}
