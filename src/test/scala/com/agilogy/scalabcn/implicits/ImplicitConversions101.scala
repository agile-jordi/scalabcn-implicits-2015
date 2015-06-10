package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class ImplicitConversions101 extends FlatSpec{

  behavior of "code without implicit conversions"

  implicit val user = new User("agile_jordi")
  val s = new IssueTrackingService
  val issueId = s.createIssue("let's code without implicit conversions")

  they should "be comfortable" in {

    // Imagine you got an issue id as an integer from the user
    val intIssueId:Int = issueId.id

    s.addComment(new IssueId(intIssueId),"ouch! I need to create an IssueId instance from the int...")

    assert(s.getIssue(new IssueId(intIssueId)).get.comments.head.comment.startsWith("ouch!"))
  }

}
