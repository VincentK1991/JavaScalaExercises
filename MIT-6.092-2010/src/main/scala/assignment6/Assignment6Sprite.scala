package assignment6

import java.awt.Graphics

// trait in scala can be used as public interface in java
trait Sprite {

  def draw(surface:Graphics, leftX:Int, topY:Int):Unit

  def getWidth:Int

  def getHeight:Int

}
