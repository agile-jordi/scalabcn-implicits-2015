package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class ImplicitParameters101 extends FlatSpec{
  
  behavior of "implicit parameters"

  val s = new IssueTrackingService

  they should "be used when no explicit arguments are provided" in {
    // Declare the val as implicit, so it can be used wherever it is needed
    implicit val user = User("agile_jordi")
    val desc = "no implicits here"
    val comment = "why are there no implicits in an implicit talk?"
    val issueId = s.createIssue(desc)
    s.addComment(issueId,comment)
    val issue = s.getIssue(issueId).get
    assert(issue.description === desc)
    assert(issue.reporter === user)
    assert(issue.comments.size === 1)
    assert(issue.comments.head.comment === comment)
    assert(issue.comments.head.author === user)
  }

}
