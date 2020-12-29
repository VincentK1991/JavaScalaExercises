/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 *//* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph
import scala.collection.mutable

/**
 * An implementation of Graph.
 *
 * <p>PS2 instructions: you MUST use the provided rep.
 */
class ConcreteEdgesGraph[L] extends Graph[L] {
  final private val verticesSet: mutable.HashSet[L] = new mutable.HashSet()
  final private var edgesList: List[Edge[L]] = List.empty[Edge[L]]

  checkRep()

  override def add(vertex: L): Boolean = {
    val result = verticesSet.add(vertex)
    checkRep()
    result
  }

  /**
   * set
   * */
  override def set(source:L, target:L, weight: Int): Int = {

    //remove or modify existing edge

    val previous: Int = edgesList.find(_.toString == source + "." + target) match {
      case Some(item: Edge[L]) => item.getWeight
      case _ => 0
    }
    // if list contains element
    if (edgesList.exists{x:Edge[L] => x.getSource.equals(source) && x.getTarget.equals(target)}) {
      if (weight == 0){
        edgesList = edgesList.filter{ x:Edge[L] => !(x.getSource.equals(source) && x.getTarget.equals(target))}
      } // remove from edge list
      else if (weight > 0 ){
        edgesList.filter{ x:Edge[L] => !(x.getSource.equals(source) && x.getTarget.equals(target))}.head.setWeight(weight)
      } // set new weight
    }
    // if list does not contains element
    else {
      if (! verticesSet.contains(source)){
        verticesSet.add(source)
      }
      if (! verticesSet.contains(target)) {
        verticesSet.add(target)
      }
      edgesList = new Edge[L](source,target,weight) :: edgesList
      checkRep()
    }
    previous
  }


  override def remove(vertex: L): Boolean = {
    val inSet = verticesSet.contains(vertex)
    verticesSet.remove(vertex)
    edgesList = edgesList.filter {
      x: Edge[L] => x.getSource.equals(vertex) || x.getTarget.equals(vertex)
    }
    inSet
  }

  override def vertices: mutable.HashSet[L] = {
    val returnHash: mutable.HashSet[L] = new mutable.HashSet()
    verticesSet.map(returnHash.add)
    returnHash
  }

  override def sources(target: L): mutable.Map[L, Int] = {
    val itemList = for {
      edgeToTarget <- edgesList.filter {
        (x: Edge[L]) => x.getTarget == target
      }
      source = edgeToTarget.getSource
      weight = edgeToTarget.getWeight
    } yield source -> weight
    itemList.to(mutable.Map)
  }

  override def targets(source: L): mutable.Map[L, Int] = {
    val itemList = for {
      edgeToTarget <- edgesList.filter {
        (x: Edge[L]) => x.getSource == source
      }
      target = edgeToTarget.getTarget
      weight = edgeToTarget.getWeight
    } yield target -> weight
    itemList.to(mutable.Map)
  }

  def checkRep():Unit = {
    assert(verticesSet != null)
    edgesList.foreach{x:Edge[L] => assert(x.getWeight > 0 )}
  }

  override def toString: String = {
    edgesList.foldLeft("")(_ + _.toString)
  }
}
/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge[L](source: L, target: L, var weight:Int = 0) { // TODO fields
  // Abstraction function:
  //   TODO
  // Representation invariant:
  // Safety from rep exposure:
  // TODO constructor
  // TODO checkRep
  // TODO methods
  checkRep()

  def getSource: L = source

  def getTarget: L = target


  def getWeight: Int = weight

  def setWeight(n: Int): Unit = weight = n

  def checkRep():Unit = {
    assert(source != null)
    assert(target != null)
    assert(weight != null)
  }

  override def toString: String = source + "->" + target + " weight = " + weight + " "

}