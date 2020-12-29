package assignment5

import java.awt.Color
import java.awt.Graphics

//import assignment5.BouncingBox

class DrawGraphics {
  var listBox:List[BouncingBox] = List.empty[BouncingBox]

  val rand = scala.util.Random

  def addBox(item: BouncingBox):Unit = listBox = item :: listBox

  this.addBox(new BouncingBox(rand.nextInt(200), rand.nextInt(200), Color.GREEN))
  this.addBox(new BouncingBox(rand.nextInt(200), rand.nextInt(200), Color.BLUE))
  this.addBox(new BouncingBox(rand.nextInt(200), rand.nextInt(200), Color.RED))
  this.addBox(new BouncingBox(rand.nextInt(200), rand.nextInt(200), Color.YELLOW))

  def setMovement:Unit = {
    listBox.map((item:BouncingBox) => item.setMovementVector(3 - rand.nextInt(6),3 - rand.nextInt(6)))
  }

  def draw(surface:Graphics):Unit = {
    //surface.drawLine(50, 50, 250, 250)
    surface.drawOval(150,250,150,150)
    surface.drawArc(100,10,1,1,30,10)
    //box1.draw(surface)
    //box2.draw(surface)
    //box3.draw(surface)
    this.setMovement
    listBox.map((item:BouncingBox) => item.draw(surface))
  }
}
