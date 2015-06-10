package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class PimpMyLibrary101 extends FlatSpec{

  behavior of "pimp my library"

  // pimp my library!
  class StringHelper(self:String){
    def nonActuallyEmpty:Boolean = self.trim.length > 0
  }
  implicit def stringToStringHelper(s:String):StringHelper = new StringHelper(s)


  it should "extend an existing class with new members" in {
    assert("".nonActuallyEmpty === false)
    assert("  ".nonActuallyEmpty === false)
    assert("  2".nonActuallyEmpty === true)

  }

}


