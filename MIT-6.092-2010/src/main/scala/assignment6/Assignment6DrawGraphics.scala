package assignment6

import java.awt.Color
import java.awt.Graphics

class DrawGraphics{
  //val box:Rectangle = new Rectangle(15, 20, Color.RED)
  var listBox:List[Sprite] = List.empty[Sprite]
  var listMovingSprite:List[Mover] = List.empty[Mover]
  val rand = scala.util.Random

  def addBox(item: Sprite):Unit = listBox = item :: listBox
  //val box:Triangle = new Triangle(15,20,Color.BLUE)
  //
  this.addBox(new Triangle(15, 20,Color.RED))
  this.addBox(new Triangle(10, 25,Color.BLUE))
  this.addBox(new Rectangle(10, 20,Color.GREEN))
  this.addBox(new Oval(15,20,Color.YELLOW))
  def addMovingSprite(item:Mover):Unit = listMovingSprite = item :: listMovingSprite

  listBox.map((item:Sprite) => addMovingSprite(new Bouncer(this.getNum(200),this.getNum(200),sprite = item)))

  //val movingSprite = new Bouncer(100, 170,sprite =  box)
  //movingSprite.setMovementVector(3, 1)
  //
  def getNum(maxNum:Int):Int = maxNum - rand.nextInt(2*maxNum)
  listMovingSprite.map((item:Mover) => item.setMovementVector(this.getNum(10),this.getNum(10)))

  def draw(surface:Graphics) {
    //movingSprite.draw(surface)
    listMovingSprite.map((item:Mover) => item.draw(surface))
  }
}
