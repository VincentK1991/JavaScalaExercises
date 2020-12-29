package assignment5

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

//import assignment5._ //{SimpleDraw,DrawGraphics}

class BouncingBox(var x:Int, var y:Int,val color:Color, var xDirection:Int = 0, var yDirection:Int =0) {

  val SIZE:Int = 20

  def draw(surface:Graphics):Unit = {

    surface.setColor(color)
    surface.fillRect(x -SIZE/2, y - SIZE/2, SIZE, SIZE)
    surface.setColor(Color.BLACK)
    (surface.asInstanceOf[Graphics2D]).setStroke(new BasicStroke(3.0f))
    surface.drawRect(x - SIZE/2, y - SIZE/2, SIZE, SIZE)

    x += xDirection
    y += yDirection

    if ((x - SIZE/2 <= 0 && xDirection < 0 ) ||
      (x + SIZE/2 >= 300 && xDirection > 0)) {
        xDirection = -xDirection
      }
    if ((y - SIZE/2 <= 0 && yDirection < 0 ) ||
      (y + SIZE/2 >= 300 && yDirection > 0)) {
        yDirection = -yDirection
      }
  }

  def setMovementVector(xIncrement:Int,yIncrement:Int) {
    xDirection = xIncrement
    yDirection = yIncrement
  }

}
