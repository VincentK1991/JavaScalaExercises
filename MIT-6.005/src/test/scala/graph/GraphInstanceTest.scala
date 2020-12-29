/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 *//* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph

import scala.collection.mutable.HashSet
import org.scalatest.FunSuite

import scala.collection.mutable

/**
 * Tests for instance methods of Graph.
 *
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance ( )}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
class GraphInstanceTest extends FunSuite {
  /**
   * Overridden by implementation-specific test classes.
   *
   * @return a new empty graph of the particular implementation being tested
   */
  //private val emptyInstance = Graph.empty[String]

  test("testInitialVerticesEmpty"){
    // TODO you may use, change, or remove this test
    //val emptyInstance = Graph.empty[String]
    val emptyInstance = Graph.empty[String]
    val d1 = emptyInstance.vertices
    assert(d1 == mutable.HashSet.empty, "expected new graph to have no vertices")
  }

  test("test graph instant"){
    val emptyInstance = Graph.empty[String]
    emptyInstance.set(source = "Paris",target = "London",weight = 10)
    assert(emptyInstance.toString == "Paris->London weight = 10", "expected new graph to have no vertices")
    // TODO other tests for instance methods of Graph
  }
}