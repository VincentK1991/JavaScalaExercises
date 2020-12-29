package assignment1
object Main extends App {
  //val gravity = -9.81 // default if written in decimal is double
  val initialVelocity = 0.0
  val fallingTime = 10.0
  val initialPosition = 0.0
  //val finalPosition = 0.0

  //println("The object's position after  " + fallingTime + " second is " + finalPosition + " m.")

  def gravity: Double = -9.81 //this function is called parameterless method
  def updatePosition: Double = 0.5*gravity*math.pow(fallingTime,2) + initialVelocity*fallingTime + initialPosition // again parameterless method
  val finalPosition = updatePosition // function is the value of the return type (similar to f(x) is another numeric like )

  println("The object's position after  " + fallingTime + " second is " + finalPosition + " m.")

}
