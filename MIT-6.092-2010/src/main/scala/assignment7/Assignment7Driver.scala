package assignment7
import scala.io.Source


object Main extends App {
  // We use Either expression which allow us to return 2 types of output Right[X] and Left[X]
  def testString(pathName:String):Either[Boolean,Int] = {
    val sourceLine = Source.fromFile(pathName).getLines.toList

    val rowLine = sourceLine.map(_.split("\t").toList).filter((x:List[Any]) => !(x == List(""))).zipWithIndex
    val rowOnly = for {(item,index) <- rowLine} yield item.map(_.toInt)
    val numRow = rowOnly.size

    val colOnly2 = for (index <- (0 to numRow -1)) yield rowOnly.flatten.zipWithIndex.filter{ case(a,b) => b % numRow == index}.map{case(a,b) => a}
    val colOnly = colOnly2.toList

    val diagLine =for {(item,index) <- rowLine} yield item(index).toInt
    val revdiagLine = for {(item,index) <- rowLine} yield item(numRow - 1 - index).toInt

    if ((diagLine.sum == revdiagLine.sum) && (diagLine.sum == rowOnly.map(_.sum).distinct(0))
      && (rowOnly.map(_.sum).distinct == colOnly.map(_.sum).distinct) && (colOnly.map(_.sum).distinct.size == 1)){
      return Right(diagLine.sum)
    }
    else{
      return Left(false)
    }

  }

  val dirPath = "src/main/scala/assignment7/"
  val nameList = List("Mercury.txt", "Jupiter.txt", "Luna.txt")
  val absPathList = nameList.map(dirPath + _)
  val resultList = absPathList.map(testString(_) match { case Left(s) => "false"  case Right(s) => "true and the magic number is " +s.toString})
  nameList zip resultList map{ case(name,result) => println(s"$name is a magic square? $result \n")}

}
