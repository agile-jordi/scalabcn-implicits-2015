package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class ImplicitParameters101 extends FlatSpec{
  
  behavior of "non implicit parameters"

  val db = new Database
  
  // Let's say you have a codebase where you want to track who is making a request
  // throughout the call stack

  // But it makes sense to treat caller as a crosscutting parameter:

  def createIssue(description:String)(caller:User):IssueId = {
    db.save(Issue.newIssue(description)(caller))
  }

  def getIssue(id:IssueId)(caller:User): Option[Issue] = {
    if(!caller.canViewIssues) throw new ForbiddenException
    val res = db.retrieve(id)
    if(res.exists(!_.viewAllowed(caller))) throw new ForbiddenException
    res
  }
  
  def addComment(id: IssueId, comment:String)(caller:User):Unit = {
    val issue = getIssue(id)(caller).getOrElse(throw new NotFoundException)
    db.save(issue.addComment(comment)(caller))
  }

  they should "work as expected" in {
    val user = User("agile_jordi")
    val desc = "no implicits here"
    val comment = "why are there no implicits in an implicit talk?"
    val issueId = createIssue(desc)(user)
    addComment(issueId,comment)(user)
    val issue = getIssue(issueId)(user).get
    assert(issue.description === desc)
    assert(issue.reporter === user)
    assert(issue.comments.size === 1)
    assert(issue.comments.head.comment === comment)
    assert(issue.comments.head.author === user)
  }

}
