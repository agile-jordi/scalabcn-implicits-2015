package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

import scala.language.implicitConversions

class ImplicitConversions101 extends FlatSpec{

  behavior of "implicit conversions"

  implicit val user = new User("agile_jordi")
  val s = new IssueTrackingService
  val issueId = s.createIssue("let's code without implicit conversions")

  implicit def intToIssueId(i:Int):IssueId = new IssueId(i)

  they should "be powerful" in {

    // Imagine you got an issue id as an integer from the user
    val intIssueId:Int = issueId.id

    s.addComment(intIssueId,"ouch! I need to create an IssueId instance from the int...")

    assert(s.getIssue(intIssueId).get.comments.head.comment.startsWith("ouch!"))
  }

}
