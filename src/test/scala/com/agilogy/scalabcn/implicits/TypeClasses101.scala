package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

trait Comparator[T]{
  def gt(a:T,b:T):Boolean
}

class TypeClasses101 extends FlatSpec {

  behavior of "existing types"

  // Polymorphism: max operates on comparable things:
  def max[T](l:List[T], ifEmpty:T)(comparator:Comparator[T]):T = if(l.isEmpty) ifEmpty else l.tail.fold(l.head){
    case (acc,elem) => if(comparator.gt(acc,elem)) acc else elem
  }

  they should "not suck, but they have what they have" in {
    val integers = List(2,42,23,5)
    val intComparator = new Comparator[Int] {
      override def gt(a: Int, b: Int): Boolean = a > b
    }
    assert(max(integers,0)(intComparator) === new Integer(42))
    val doubles = List(2.0,42.0,23.0,5.0)
    val doubleComparator = new Comparator[Double]{
      override def gt(a: Double, b: Double): Boolean = a > b
    }
    assert(max(doubles, 0.0)(doubleComparator) === 42.0)
  }

}
