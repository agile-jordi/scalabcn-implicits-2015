package com.agilogy.scalabcn.implicits

import scala.collection.mutable

class Database {

  private var issues:Map[IssueId,Issue] = Map.empty

  def save(event: IssueCommentAdded): Unit = {
    val issue = issues(event.issueId)
    val newVersion = issue.applyEvent(event)
    issues += event.issueId -> newVersion
  }

  def save(event: IssueCreated): IssueId = {
    issues += event.issueId -> Issue.applyEvent(event)
    event.issueId
  }

  def retrieve(id: IssueId): Option[Issue] = issues.get(id)


}
