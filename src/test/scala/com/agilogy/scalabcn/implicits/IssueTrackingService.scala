package com.agilogy.scalabcn.implicits

class IssueTrackingService {

  val db = new Database

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


}
