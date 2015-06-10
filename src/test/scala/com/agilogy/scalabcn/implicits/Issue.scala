package com.agilogy.scalabcn.implicits

case class IssueId(id:Int) extends AnyVal{
  def isPriorTo(other:IssueId):Boolean = this.id < other.id
}

object IssueId{
  var nextId:Int = 0

  def next:IssueId = {
    val res = new IssueId(nextId)
    nextId = nextId + 1
    res
  }
}

case class Issue(id:IssueId, description:String, reporter:User, comments:Seq[IssueComment] = Seq.empty) {

  def viewAllowed(implicit caller: User): Boolean = true
  def commentAllowed(implicit caller: User) = true

  def applyEvent(event: IssueCommentAdded): Issue = {
    this.copy(comments = this.comments :+ IssueComment(event.comment,event.author))
  }

  def addComment(comment: String)(implicit caller: User) = {
    if(!commentAllowed) throw new ForbiddenException
    IssueCommentAdded(id,comment,caller)
  }

}

case class IssueComment(comment:String, author:User)

object Issue{

  def applyEvent(event: IssueCreated): Issue = Issue(event.issueId,event.description, event.reporter)

  def newIssue(description:String)(implicit caller:User): IssueCreated = {
    if(!caller.canCreateIssues) throw new ForbiddenException
    IssueCreated(IssueId.next,description,caller)
  }
}