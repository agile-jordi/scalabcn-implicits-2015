package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

// Typeclass Comparator
trait Comparator[T]{
  def gt(a:T,b:T):Boolean
}

object Comparator{
  
  // int is of type Comparator
  implicit val intComparator = new Comparator[Int] {
    override def gt(a: Int, b: Int): Boolean = a > b
  }

  // double is of type Comparator
  implicit val doubleComparator = new Comparator[Double]{
    override def gt(a: Double, b: Double): Boolean = a > b
  }
}

class TypeClasses101 extends FlatSpec {

  behavior of "type classes"

  // Polymorphism: max operates on things of the comparator type
  def max[T](l:List[T], ifEmpty:T)(implicit comparator:Comparator[T]):T = if(l.isEmpty) ifEmpty else l.tail.fold(l.head){
    case (acc,elem) => if(comparator.gt(acc,elem)) acc else elem
  }

  they should "provide polymorphism" in {
    val integers = List(2,42,23,5)
    assert(max(integers,0) === new Integer(42))
    val doubles = List(2.0,42.0,23.0,5.0)
    assert(max(doubles, 0.0) === 42.0)
  }

}
