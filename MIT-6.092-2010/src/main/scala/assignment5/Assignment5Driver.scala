package assignment5

//import assignment5.{BouncingBox,DrawGraphics}

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

import javax.swing.JFrame
import javax.swing.JPanel

class SimpleDraw(drawer:DrawGraphics) extends JPanel with Runnable {
  private final val SerialVersionUID:Long =  -7469734580960165754L
  private var animate:Boolean = true
  private final val FRAME_DELAY:Int = 50
  final val WIDTH:Int = 300
  final val HEIGHT:Int = 300
  val draw:DrawGraphics = drawer

//  def SimpleDraw(drawer:DrawGraphics) = {
//    this.draw = drawer
//  }

  override def paintComponent(g:Graphics):Unit = {
    super.paintComponent(g) // allow g to invoke method of parent class

    //enable anti-aliasing for better looking graphics
    val g2:Graphics2D = g.asInstanceOf[Graphics2D]//this is called type casting
    // can also use pattern matching to achieve the same task, but it takes longer

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON)
    draw.draw(g2)
  }

  def start:Unit = animate = true

  def stop:Unit = animate = false

  def animationEnabled = animate

  def run:Unit = {
    while (true) {
      if (animationEnabled) {
        repaint()
      }
      try {
        Thread.sleep(FRAME_DELAY)
      }
      catch {
        case e:InterruptedException => throw new  RuntimeException(e)
      }
    }
  }
}

object Main extends App {
  val content:SimpleDraw = new SimpleDraw(new DrawGraphics())

  val frame:JFrame = new JFrame("MyGraphics2")
  val bgColor:Color = Color.white
  frame.setBackground(bgColor)
  content.setBackground(bgColor)

  content.setPreferredSize(new Dimension(400, 400))

  frame.setContentPane(content)
  frame.setResizable(true)
  frame.pack()
  frame.addWindowListener(new WindowAdapter() {
    override def windowClosing(e:WindowEvent):Unit = System.exit(0)
    override def windowDeiconified(e:WindowEvent):Unit = content.start
    def WindowIconified(e:WindowEvent):Unit = content.stop
  })

  new Thread(content).start()

  frame.setVisible(true)
}
