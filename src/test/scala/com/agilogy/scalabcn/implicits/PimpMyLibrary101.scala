package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class PimpMyLibrary101 extends FlatSpec{

  behavior of "an existing class"

  it should "not suck, but it has what it has" in {

    def nonActuallyEmpty(s:String):Boolean = s.trim.length > 0

    assert(nonActuallyEmpty("") === false)
    assert(nonActuallyEmpty("  ") === false)
    assert(nonActuallyEmpty("  2") === true)

  }

}


