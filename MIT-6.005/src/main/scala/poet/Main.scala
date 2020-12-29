/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 *//* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet

import java.io.File
import java.io.IOException

/**
 * Example program using GraphPoet.
 *
 * <p>PS2 instructions: you are free to change this example class.
 */
object Main {
  /**
   * Generate example poetry.
   *
   * @param args unused
   * @throws IOException if a poet corpus file cannot be found or read
   */
    //@throws[IOException]
    def main(args: Array[String]):Unit = {
      val nimoy = new GraphPoet(new File("src/main/java/poet/paradiseLost_book1.txt"))
      val input = "O Father, what intends thy hand, she cry’d, \n" +
        "Against thy only Son?  What fury O Son, \n" +
        "Possesses thee to bend that mortal Dart \n" +
        "Against thy Fathers head? and know’st for whom; \n" +
        "For him who sits above and laughs the while \n" +
        "At thee ordain’d his drudge, to execute \n" +
        "What e’re his wrath, which he calls Justice, bids, \n" +
        "His wrath which one day will destroy ye both.\n"
      println("the lines start here")
      //println(nimoy)
      println(input)
      val output = nimoy.poem(input)
      println(output)
    }
}