package assignment6

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class Triangle (width:Int, height:Int, color:Color) extends Sprite {
  //this.width = width
  //this.height = height
  //this.color = color

  def draw(surface:Graphics, x:Int, y:Int) {
    surface.setColor(color)
    //surface.fillRect(x, y, width, height)
    //surface.setColor(Color.WHITE)
    surface.asInstanceOf[Graphics2D].setStroke(new BasicStroke(3.0f))
    surface.drawPolygon(Array(x, x+width, x), Array(y, y, y + height),3)
    surface.fillPolygon(Array(x, x+width, x), Array(y, y, y + height),3)
  }

  def getWidth = width

  def getHeight = height

}

class Oval (width:Int, height:Int, color:Color) extends Sprite {

  def getWidth = width
  def getHeight = height
  def draw(surface:Graphics, x:Int, y:Int){
    surface.setColor(color)
    surface.asInstanceOf[Graphics2D].setStroke(new BasicStroke(3.0f))
    surface.drawOval(x, y, width, height)
    surface.fillOval(x, y, width, height)
  }

}
