/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 *//* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph

import scala.collection.mutable
import org.scalatest.FunSuite

/**
 * Tests for ConcreteEdgesGraph.
 *
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 *
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
class ConcreteEdgesGraphTest extends FunSuite {
  /*
      * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
      */
  def emptyInstance = new ConcreteEdgesGraph[String]

  /*
       * Testing ConcreteEdgesGraph...
       */
  // Testing strategy for ConcreteEdgesGraph.toString()
  //   TODO
  // TODO tests for ConcreteEdgesGraph.toString()
    test(testName = "test concrete edge graph toString"){
      emptyInstance.set(source = "Paris",target = "London",weight = 10)
      assert(emptyInstance.toString ==  "Paris->London weight = 10" ,"expected new graph to have no vertices")
  }
       /* Testing Edge...
       */
  // Testing strategy for Edge
  // TODO tests for operations of Edge
  test(testName = "test concrete edge graph edge implementation"){
    assert(emptyInstance.targets(source ="Paris") ==  mutable.Map("London"-> 10) ,"expected graph to return map from target to weight")
    emptyInstance.set(source = "Tokyo",target = "London",weight = 5)
    assert(emptyInstance.targets(source ="Tokeo") ==  mutable.Map("London"-> 5) ,"expected graph to set new edge to a known target")
  }

}