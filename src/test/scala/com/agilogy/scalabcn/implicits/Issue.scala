package com.agilogy.scalabcn.implicits

class IssueId(val id:Int) extends AnyVal

object IssueId{
  var nextId:Int = 0

  def next:IssueId = {
    val res = new IssueId(nextId)
    nextId = nextId + 1
    res
  }
}

case class Issue(id:IssueId, description:String, reporter:User, comments:Seq[IssueComment] = Seq.empty) {

  def viewAllowed(caller: User): Boolean = true
  def commentAllowed(caller: User) = true

  def applyEvent(event: IssueCommentAdded): Issue = {
    this.copy(comments = this.comments :+ IssueComment(event.comment,event.author))
  }

  def addComment(comment: String)(caller: User) = {
    if(!commentAllowed(caller)) throw new ForbiddenException
    IssueCommentAdded(id,comment,caller)
  }

}

case class IssueComment(comment:String, author:User)

object Issue{

  def applyEvent(event: IssueCreated): Issue = Issue(event.issueId,event.description, event.reporter)

  def newIssue(description:String)(caller:User): IssueCreated = {
    if(!caller.canCreateIssues) throw new ForbiddenException
    IssueCreated(IssueId.next,description,caller)
  }
}