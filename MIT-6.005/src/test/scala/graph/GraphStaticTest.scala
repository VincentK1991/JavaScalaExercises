/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 *//* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph

import scala.collection.mutable
import org.scalatest.FunSuite

/**
 * Tests for static methods of Graph.
 *
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
class GraphStaticTest extends FunSuite {

  test("test empty vertices empty"){
    assert(Graph.empty.vertices ==  mutable.HashSet.empty ,"expect empty() graph to have no vertices")
  }
  // TODO test other vertex label types in Problem 3.2
  test("test different types of labels"){
    val graph1 =  Graph.empty[String]
    graph1.set("Paris","London",5)
    val graph2 = Graph.empty[Int]
    graph2.set(1,2,3)
    assert(graph1.toString == "Paris->London weight = 5", "generic type as string")
    assert(graph2.toString == "1->2 weight = 3", "generic type as int")
  }
}