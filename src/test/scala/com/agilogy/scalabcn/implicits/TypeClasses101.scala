package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class TypeClasses101 extends FlatSpec {

  behavior of "existing types"

  // Polymorphism: max operates on comparable things:
  def max[T <: Comparable[T]](l:List[T], ifEmpty:T):T = if(l.isEmpty) ifEmpty else l.tail.fold(l.head){
    case (acc,elem) => if(acc.compareTo(elem) > 0) acc else elem
  }

  they should "not suck, but they have what they have" in {
    val integers = List(2,42,23,5)
    // Ouch! Int is not Comparable!
    val javaInts = integers.map(i => new Integer(i))
    assert(max(javaInts,new Integer(0)) === new Integer(42))
    val doubles = List(2.0,42.0,23.0,5.0)
    // Ouch! Won't compile
    // assert(max(doubles, 0.0) === 42.0)
  }

}
