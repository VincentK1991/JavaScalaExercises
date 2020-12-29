package assignment6

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SimpleDraw(drawer:DrawGraphics) extends JPanel with Runnable {
  private final val SerialVersionUID:Long = -7469734580960165754L
  private var animate:Boolean = true
  private final val FRAME_DELAY:Int = 50
  //final val WIDTH:Int = 300
  //final val HEIGHT:Int = 300
  private val draw:DrawGraphics = drawer

  override def paintComponent(g:Graphics):Unit = {
    super.paintComponent(g)

    val g2:Graphics2D = g.asInstanceOf[Graphics2D]

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    draw.draw(g2)
  }

  def WIDTH:Int = 300
  def HEIGHT:Int = 300
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
        case e:InterruptedException => throw new RuntimeException(e)
      }
    }
  }
}

object SimpleDraw {
  def WIDTH:Int = 300
  def HEIGHT:Int = 300
}

object Main extends App {
  val content:SimpleDraw = new SimpleDraw(new DrawGraphics())

  val frame:JFrame = new JFrame("MyGraphics!!")
  val bgColor:Color = Color.black
  frame.setBackground(bgColor)
  content.setBackground(bgColor)

  content.setPreferredSize(new Dimension(300,300))

  frame.setContentPane(content)
  frame.setResizable(false)
  frame.pack()
  frame.addWindowListener(new WindowAdapter() {
    override def windowClosing(e:WindowEvent):Unit = System.exit(0)
    override def windowDeiconified(e:WindowEvent):Unit = content.start
    override def windowIconified(e:WindowEvent):Unit = content.stop
  })

  new Thread(content).start()

  frame.setVisible(true)

}
