package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class TypeClasses101 extends FlatSpec {

  behavior of "existing types"

  def max(l:List[Int], ifEmpty:Int):Int = if(l.isEmpty) ifEmpty else l.tail.fold(l.head){
    case (acc,elem) => if(acc >= elem) acc else elem
  }

  they should "not suck, but they have what they have" in {
    val integers = List(2,42,23,5)
    assert(max(integers,0) === 42)
    val doubles = List(2.0,42.0,23.0,5.0)
    // Ouch! Won't compile
    // assert(max(doubles, 0.0) === 42.0)
  }

}
