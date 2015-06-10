package com.agilogy.scalabcn.implicits

trait Event

case class IssueCreated(issueId:IssueId, description:String, reporter:User)

case class IssueCommentAdded(issueId:IssueId, comment:String, author:User)
