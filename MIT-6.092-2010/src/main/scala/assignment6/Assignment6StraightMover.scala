package assignment6

import java.awt.Graphics

class StraightMover(var x:Int,var y:Int,var xDirection:Int,var yDirection:Int, sprite:Sprite) extends Mover {

  //this.sprite = sprite

  def setMovementVector(xIncrement:Int, yIncrement:Int):Unit = {
    xDirection = xIncrement
    yDirection = yIncrement
  }

  def draw(graphics:Graphics):Unit = {
    sprite.draw(graphics, x, y)

    x += xDirection
    y += yDirection
  }
}
