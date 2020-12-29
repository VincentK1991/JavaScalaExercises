package turtle

import turtle.TurtleSoup.drawRegularPolygon


object TurtleSoup {

  def drawSquare(turtle: Turtle, sideLength: Int): Unit = {
    //throw new RuntimeException("implement me!")
    (0 to 3).foreach{
      _ => turtle.forward(sideLength)
        turtle.turn(-90)
    }
  }

  def calculateRegularPolygonAngle(sides: Int): Double = {
    //throw new RuntimeException("implement me!")
    180 - (((sides - 2) * 180.0) / sides)
  }

  def calculatePolygonSidesFromAngle(angle: Double): Int = {
    //throw new RuntimeException("implement me!")
    (360 / (180 - angle)).toInt
  }

  def drawRegularPolygon(turtle: Turtle, sides: Int, sideLength: Int): Unit = {
    //throw new RuntimeException("implement me!")
    val numAngles = calculateRegularPolygonAngle(sides)
    //val numSides = calculatePolygonSidesFromAngle(numAngles)
    (0 until sides).foreach{
      _ => turtle.forward(sideLength)
      turtle.turn(numAngles)
    }
  }

  def calculateHeadingToPoint(currentHeading: Double, currentX: Int, currentY: Int, targetX: Int,
                              targetY: Int):Double = {
    //throw new RuntimeException("implement me!")
    val slope = (targetY.toDouble - currentY.toDouble)/(targetX.toDouble - currentX.toDouble)
    val degreetoX = math.atan(slope).toDegrees
    degreetoX - currentHeading
  }

  def calculateHeadings(xCoords: List[Int], ycoords: List[Int]): List[Double] = {
    //throw new RuntimeException("implement me!")
    val temp1 = xCoords zip ycoords
    def adjustHeading(coord:List[((Int,Int),(Int,Int))],listHeading:List[Double] = List(0.0)):List[Double] = {
      if (coord.isEmpty){
        return listHeading.reverse.tail
      }
      val newHeading = calculateHeadingToPoint(listHeading.head, coord.head._1._1, coord.head._1._2, coord.head._2._1, coord.head._2._2)
      adjustHeading(coord.tail, newHeading :: listHeading)
    }
    adjustHeading(temp1 zip temp1.tail)
  }

  implicit class PowerInt(val i: Double) {
    def ** (exp:Double):Double = math.pow(i, exp)
  }

  def equationRange(minRange:Double,maxRange:Double,range:Double,tolerance:Double):List[(Double,Double)] ={
    val range1 = (BigDecimal(minRange) to BigDecimal(maxRange)).by(range).map(_.toDouble).toList
    val answer1 = for {
      List(x,y) <- range1.combinations(2).toList
      if (circularEquation(x,y,tolerance))
      //x <- range1
      //y = math.sqrt(math.cos(x))*math.cos(400*x) + math.sqrt(math.abs(x)) - 0.4 * math.pow((4 - x*x),0.1)
    } yield (x,y)
    answer1
  }

  def circularEquation(X:Double,Y:Double,tolerance:Double):Boolean = {
    //(X**2 + Y**2 < 3**2 + tolerange) || (X**2 + Y**2 > 3**2 - tolerange)
    Y < math.abs(X) + math.sqrt( 1 - X**2) + tolerance //|| Y == math.abs(X) - math.sqrt( 1 - X**2)
  }

  def drawPersonalArt(turtle: Turtle): Unit = {
    val listCoord = equationRange(-100,100,1,0.001)
    //val listCoord = List((0,0),(100,0),(0,100),(0,0))
    println(listCoord)
    val listAngles = calculateHeadings(listCoord.map(_._1.toInt), listCoord.map(_._2.toInt))
    println(listAngles)
    listAngles.foreach{
      x$1 => turtle.turn(x$1)
        turtle.forward(30)

    }

  }

  def circleColor(turtle: DrawableTurtle): Unit ={
    //val color_item = turtle.getCurrentColor()
    turtle.getCurrentColor() match {
      case PenColor.BLACK => turtle.color(PenColor.BLUE)
      case PenColor.BLUE => turtle.color(PenColor.GREEN)
      case PenColor.GREEN => turtle.color(PenColor.YELLOW)
      case PenColor.YELLOW => turtle.color(PenColor.ORANGE)
      case PenColor.ORANGE => turtle.color(PenColor.MAGENTA)
      case PenColor.RED => turtle.color(PenColor.RED)
      case _ => turtle.color((PenColor.BLACK))
    }
    //println(color_item)
  }

  def main(args: Array[String]): Unit = {
    val turtle: DrawableTurtle = new DrawableTurtle()
    drawSquare(turtle, sideLength = 40)
    turtle.color(PenColor.BLUE)
    (3 until 25).foreach {
      x$1 => drawRegularPolygon(turtle, sides = x$1, sideLength = 40)
        circleColor(turtle)
    }
    //drawPersonalArt(turtle)
    turtle.draw()
    //turtle.c
  }
}
