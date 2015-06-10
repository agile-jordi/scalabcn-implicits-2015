package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

import scala.language.implicitConversions

class ImplicitConversions101 extends FlatSpec{

  behavior of "implicit conversions"

  implicit val user = new User("agile_jordi")
  val s = new IssueTrackingService
  val issueId = s.createIssue("let's code without implicit conversions")

  implicit def intToIssueId(i:Int):IssueId = IssueId(i)

  they should "convert an expression if it does not conform to the expected type" in {

    // Imagine you got an issue id as an integer from the user
    val intIssueId:Int = issueId.id

    s.addComment(intIssueId,"ouch! I need to create an IssueId instance from the int...")

    assert(s.getIssue(intIssueId).get.comments.head.comment.startsWith("ouch!"))
  }

  they should "convert an expression if it does not have a member" in {
    // We have added a method isPriorTo:
    assert(IssueId(4).isPriorTo(IssueId(5)))
    // We can use the method on Int thanks to the implicit conversion:
    assert(4.isPriorTo(3) === false)
  }

}
