package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class ImplicitTypeBounds extends FlatSpec{

  // We want an evidence (ev) that T1 and T2 are indeed the same type
  // not only that they have a supertype in common
  def eq[T1,T2](a:T1,b:T2)(implicit ev: T1 =:= T2):Boolean = a == b

  behavior of "type safe equality"

  it should "correctly compare values of the same type" in {
    assert(eq("hi","hi") === true)
    assert(eq("hey","ho") === false)
    // This no longer compiles
    // assert(eq("hi", 3) === false)
  }

}