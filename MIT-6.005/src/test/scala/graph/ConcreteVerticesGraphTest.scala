/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 *//* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph

import scala.collection.mutable
import org.scalatest.FunSuite

/**
 * Tests for ConcreteVerticesGraph.
 *
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 *
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
class ConcreteVerticesGraphTest extends FunSuite {
  /*
      * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
      */
  def emptyInstance = new ConcreteVerticesGraph[String]
  emptyInstance.add("Milan")
  /*
       * Testing ConcreteVerticesGraph...
       */
  test(testName = "test concrete vertices graph toString"){

    assert(emptyInstance.toString ==  "Milan" ,"expected new graph to have no vertices")
  }
  // Testing strategy for ConcreteVerticesGraph.toString()
  //   TODO
  // TODO tests for ConcreteVerticesGraph.toString()
  /*
       * Testing Vertex...
       */
  // Testing strategy for Vertex
  // TODO tests for operations of Vertex
  test(testName = "test concrete vertices graph vertex implementation"){
    emptyInstance.set("Milan","Zurich",2)
    assert(emptyInstance.targets(source ="Milan") ==  mutable.Map("Zurich"-> 2) ,"expected graph to return map from target to weight")
  }
}