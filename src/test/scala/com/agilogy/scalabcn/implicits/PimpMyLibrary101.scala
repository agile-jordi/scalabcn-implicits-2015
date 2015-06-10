package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

object StringUtils{
  // pimp my library!
  // syntax sugar!
  // no runtime impact!
  implicit class StringHelper(val self:String) extends AnyVal{
    def nonActuallyEmpty:Boolean = self.trim.length > 0
  }
}

class PimpMyLibrary101 extends FlatSpec{

  behavior of "pimp my library"

  it should "extend an existing class with new members" in {
    import StringUtils._
    assert("".nonActuallyEmpty === false)
    assert("  ".nonActuallyEmpty === false)
    assert("  2".nonActuallyEmpty === true)
  }

}


