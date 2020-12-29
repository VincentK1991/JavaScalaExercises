package assignment6

import java.awt.Graphics

class Bouncer(var x:Int, var y:Int, var xDirection:Int = 0, var yDirection:Int = 0, var sprite:Sprite) extends Mover  {

 // this.sprite = sprite

  def setMovementVector(xIncrement:Int, yIncrement:Int){
    xDirection = xIncrement
    yDirection = yIncrement
  }

  def draw(surface:Graphics):Unit = {
    sprite.draw(surface, x, y)
    x += xDirection
    y += yDirection

    if ((x <= 0 && xDirection < 0) ||
          (x + sprite.getWidth >= SimpleDraw.WIDTH && xDirection > 0 )) {
      xDirection = -xDirection
    }
    if (( y <= 0 && yDirection < 0) ||
          (y + sprite.getHeight >= SimpleDraw.HEIGHT && yDirection > 0)) {
      yDirection = -yDirection
    }
  }
}
