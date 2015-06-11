package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class ImplicitTypeBounds extends FlatSpec{

  // Let's say we want to implement a function to check 2 things for equality...
  // Just like equals, but we want to fail at compile time if the compiler knows they can't be possibly equals

  def eq[T](a:T,b:T):Boolean = a == b

  behavior of "type safe equality"

  it should "correctly compare values of the same type" in {
    assert(eq("hi","hi") === true)
    assert(eq("hey","ho") === false)
  }

  it should "not compile when given two things that can't be equal" in {
    assert(eq("hi", 3) === false)
    // Ouch! How the hell did that compile?
  }

}