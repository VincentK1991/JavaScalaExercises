package assignment6

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class Rectangle (width:Int, height:Int, color:Color) extends Sprite {
  //this.width = width
  //this.height = height
  //this.color = color

  def draw(surface:Graphics, x:Int, y:Int) {
    surface.setColor(color)
    surface.fillRect(x, y, width, height)
    surface.setColor(Color.BLACK)
    surface.asInstanceOf[Graphics2D].setStroke(new BasicStroke(3.0f))
    surface.drawRect(x, y, width, height)
    }

  def getWidth = width

  def getHeight = height

}
