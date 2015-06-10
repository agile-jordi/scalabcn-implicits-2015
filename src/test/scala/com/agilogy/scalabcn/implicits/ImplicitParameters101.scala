package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class ImplicitParameters101 extends FlatSpec{
  
  behavior of "implicit parameters"

  val db = new Database
  
  // Let's say you have a codebase where you want to track who is making a request
  // throughout the call stack

  // And now... implicits!
  
  def createIssue(description:String)(implicit caller:User):IssueId = {
    db.save(Issue.newIssue(description))
  }

  def getIssue(id:IssueId)(implicit caller:User): Option[Issue] = {
    if(!caller.canViewIssues) throw new ForbiddenException
    val res = db.retrieve(id)
    if(res.exists(!_.viewAllowed)) throw new ForbiddenException
    res
  }
  
  def addComment(id: IssueId, comment:String)(implicit caller:User):Unit = {
    val issue = getIssue(id).getOrElse(throw new NotFoundException)
    db.save(issue.addComment(comment))
  }

  they should "be used when no explicit arguments are provided" in {
    // Declare the val as implicit, so it can be used wherever it is needed
    implicit val user = User("agile_jordi")
    val desc = "no implicits here"
    val comment = "why are there no implicits in an implicit talk?"
    val issueId = createIssue(desc)
    addComment(issueId,comment)
    val issue = getIssue(issueId).get
    assert(issue.description === desc)
    assert(issue.reporter === user)
    assert(issue.comments.size === 1)
    assert(issue.comments.head.comment === comment)
    assert(issue.comments.head.author === user)
  }

}
