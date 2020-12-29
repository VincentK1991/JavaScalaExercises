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
class ConcreteVerticesGraph[L] extends Graph[L] {
  final private var verticesList: List[Vertex[L]] = List.empty[Vertex[L]]

  checkRep()

  def checkRep():Unit = {
    verticesList.foreach{
      x:Vertex[L] => x.getIncoming.values.foreach{y:Int => assert(y > 0)}
    }
    verticesList.foreach{
      x:Vertex[L] => x.getOutcoming.values.foreach{y:Int => assert(y > 0)}
    }
  }

  private def findVertex(label:L):Vertex[L] = {
    verticesList.find{ _.getLabel equals label} match {
      case Some(x:Vertex[L]) => x
      case _ => throw new AssertionError("vertex not in graph")
    }
  }

  private def inGraph(label:L):Boolean = {
    val set: mutable.HashSet[L] = new mutable.HashSet[L]()
    verticesList.foreach{x:Vertex[L] => set.add(x.getLabel)}
    set.contains(label)
  }

  override def add(vertex: L): Boolean = {
    if (inGraph(vertex)){
      checkRep()
      false
    }
    else {
      verticesList = new Vertex[L](vertex) :: verticesList
      checkRep()
      true
    }
  }

  override def set(source: L, target: L, weight: Int): Int = {
    if (! inGraph(source)){
      val srcVertex:Vertex[L] = new Vertex[L](source)
      if (weight > 0 ){
        verticesList = srcVertex :: verticesList
      }
    }

    if (! inGraph(target)){
      val trgVertex:Vertex[L] = new Vertex[L](target)
      if (weight > 0 ){
        verticesList = trgVertex :: verticesList
      }
    }

    val srcVertex = findVertex(source)
    val trgVertex = findVertex(target)

    val srcOutEdges: mutable.Map[L, Int] = srcVertex.getOutcoming
    val trgInEdges: mutable.Map[L, Int] = trgVertex.getIncoming
    if (srcOutEdges.keys.exists(_ == target) && trgInEdges.keys.exists(_ == source)){
      val oldWeight:Int = srcOutEdges(target)
      if (weight == 0){
        srcVertex.removeOutcoming(trgVertex)
        return oldWeight
      }
      srcVertex.addOutcoming(trgVertex, weight)
      return oldWeight
    } else {
      if (weight > 0) {
        srcVertex.addOutcoming(trgVertex,weight)
      }
    }
    0
  }

  override def remove(vertex: L): Boolean = {
    if (inGraph(vertex)){
      val vertexToRemove:Vertex[L] = findVertex(vertex)
      verticesList.foreach{
        item:Vertex[L] => item.removeIncoming(vertexToRemove)
                                item.removeOutcoming(vertexToRemove)
      }
      verticesList = verticesList.filter(_.toString !=  vertexToRemove.toString)
      checkRep()
      return true
    }
    false
  }

  override def vertices: mutable.HashSet[L] = {
    val result: mutable.HashSet[L] = new mutable.HashSet[L]()
    verticesList.foreach{x:Vertex[L] => result.add(x.getLabel)}
    result
  }

  override def sources(target: L): mutable.Map[L, Int] = {
    if (inGraph(target)){
      findVertex(target).getIncoming
    } else {
     new mutable.HashMap[L,Int]()
    }

  }

  override def targets(source: L): mutable.Map[L, Int] = {
    if (inGraph(source)){
      findVertex(source).getIncoming
    } else {
      new mutable.HashMap[L,Int]()
    }
  }

  override def toString:String = {
    verticesList.foldLeft("")(_ + _.toString)
  }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex[L](label: L) { // TODO fields
  var weight = 0
  private val inEdges = mutable.Map[L, Int]()
  private val outEdges = mutable.Map[L, Int]()
  checkRep()

  override def toString:String = label + ": inEdges" + getIncoming.toString() +
    "\toutEdge" + getOutcoming.toString() + "\n"

  def checkRep(): Unit = {
    assert(label != null)
    inEdges.values.foreach { weight: Int => assert(weight > 0) }
    outEdges.values.foreach { weight: Int => assert(weight > 0) }
  }

  def getLabel:L = label

  def getIncoming: mutable.Map[L, Int] = {
    val newMap = for {
      (k, v) <- inEdges
    } yield k -> v
    newMap
  }

  def getOutcoming: mutable.Map[L, Int] = {
    val newMap = for {
      (k, v) <- outEdges
    } yield k -> v
    newMap
  }

  def addIncoming(source: Vertex[L], weight: Int): Int = {
    //throw new RuntimeException("not implemented")
    val previous: Int = inEdges.keys.find(_ == source.getLabel) match {
      case Some(item:L) => inEdges(item)
      case _ => 0
    }
    // check if source is in the inEdges
    if (inEdges.keys.exists(_ == source.getLabel)) {
      inEdges.put(source.getLabel, weight)
      source.outEdges.put(this.getLabel, weight)
    } else {
      inEdges.put(source.getLabel,weight)
      source.outEdges.put(this.getLabel,weight)
    }
    previous
  }

  def addOutcoming(target: Vertex[L], weight: Int): Int = {
    // check if source is in the inEdges
    val previous: Int = outEdges.keys.find(_ == target.getLabel) match {
      case Some(item) => outEdges(item)
      case _ => 0
    }
    // check if source is in the inEdges
    if (outEdges.keys.exists(_ == target.getLabel)) {
      outEdges.put(target.getLabel, weight)
      target.inEdges.put(this.getLabel, weight)
    } else {
      outEdges.put(target.getLabel,weight)
      target.inEdges.put(this.getLabel,weight)
    }
    previous
  }

  def removeIncoming(source: Vertex[L]): Boolean = {
    if (inEdges.keys.exists(_ == source.getLabel)) {
      inEdges.remove(source.getLabel)
      source.outEdges.remove(this.getLabel)
      true
    } else {
      inEdges.remove(source.getLabel)
      source.outEdges.remove(this.getLabel)
      false
    }
  }

  def removeOutcoming(target: Vertex[L]): Boolean = {
    if (outEdges.keys.exists(_ == target.getLabel)) {
      outEdges.remove(target.getLabel)
      target.inEdges.remove(this.getLabel)
      true
    } else {
      inEdges.remove(target.getLabel)
      target.inEdges.remove(this.getLabel)
      false
    }
  }

  //def getTarget:String = target
  // Abstraction function:
  //   TODO
  // Representation invariant:
  // Safety from rep exposure:
  // TODO constructor
  // TODO checkRep
  // TODO methods
}