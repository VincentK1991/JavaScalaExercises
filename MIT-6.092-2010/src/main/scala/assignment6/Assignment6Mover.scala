package assignment6
import java.awt.Graphics

 trait Mover {
  def setMovementVector(xIncrement:Int,yIncrement:Int)
  def draw(surface:Graphics):Unit
}
// The difference between trait and abstract class is that abstract class allow constructor
//
//abstract class Mover(var x:Int, var y:Int, var xDirection:Int = 0, var yDirection:Int = 0, var sprite:Sprite) {
//
//  def setMovementVector(xIncrement:Int, yIncrement:Int){
//    xDirection = xIncrement
//    yDirection = yIncrement
//  }
//  def draw(graphics:Graphics):Unit = {
//    sprite.draw(graphics, x, y)
//    x += xDirection
//    y += yDirection
//  }
//}
