/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 *//* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet

import java.io.{BufferedReader, File, FileReader, IOException}
import java.util.Scanner

import scala.collection.mutable
import scala.jdk.CollectionConverters._
import graph.{Graph, Vertex}

/**
 * A graph-based poetry generator.
 *
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 *
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 * <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 *
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 *
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 *
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */

/**
 * Create a new poet with the graph from corpus (as described above).
 *
 * @param corpus text file from which to derive the poet's affinity graph
 * @throws IOException if the corpus file cannot be found or read
 */

class GraphPoet(val corpus: File) {
  //throw new RuntimeException("not implemented")
  final private val graph: Graph[String] = Graph.empty

  /** constructor
  * */
  //val wordList: List[String] = List.empty[String]
  val wordList:List[String] = new Scanner(new BufferedReader(new FileReader(corpus))).asScala.toList
  //val wordList: List[String] = s.toList
  val wordListLower:List[String] = wordList.map(_.toLowerCase)

  // add word to graph
  wordListLower.map{
    word:String => if(!graph.vertices.contains(word))
                    graph.add(word)
  }
  // set edges
  for ((src, trg) <- wordListLower zip wordListLower.tail) {
    val targets: mutable.Map[String, Int] = graph.targets(src)
    if (targets.contains(trg)){
      val oldWeight:Int = targets(trg)
      graph.set(src,trg,oldWeight + 1)
    } else {
      graph.set(src,trg,1)
    }
    checkRep()
  }


  def checkRep(): Unit = {
    val vertex: mutable.Set[String] = graph.vertices
    for {item: String <- vertex
         copy = item.toLowerCase.trim.replaceAll("\\s+","")
         _ = assert((item equals copy) && (!(item equals "")))
         } () // the expression () is used to return Unit
  }

  /**
   * Generate a poem.
   *
   * @param input string from which to create the poem
   * @return poem (as described above)
   */
  def poem(input: String):String = {
    val inputList: List[String] = input.trim.split("\\s+").toList
    val result = for {(src, trg) <- inputList zip inputList.tail
                      (srcLower, trgLower) = (src.toLowerCase, trg.toLowerCase)
                      trgSources: mutable.Map[String, Int] = graph.sources(trgLower)
                      srcTargets: mutable.Map[String, Int] = graph.targets(srcLower)
                      commonKeys: Set[String] = trgSources.keys.toSet.intersect(srcTargets.keys.toSet)
                      bridgeMap = new mutable.HashMap[String,Int]()
                      bridge =
                      if (commonKeys.nonEmpty){
                        // "common key is not not empty return it along with the source text"
                        commonKeys.map {
                          bridgeKey: String => bridgeMap.put(bridgeKey, srcTargets(bridgeKey) + trgSources(bridgeKey))
                        }
                        " \n"+ src + " " + commonKeys.head +" " + "\n "
                      }
                      else{
                        src //common keys is empty send out src
                      }
                      } yield bridge
    checkRep()
    println("======================================")
    println("======================================")
    println("======================================")
    result.foldLeft("")(_ + " "+_).tail + " "+ inputList.reverse.head
  }

  // TODO toString()
  override def toString: String = graph.toString
}
