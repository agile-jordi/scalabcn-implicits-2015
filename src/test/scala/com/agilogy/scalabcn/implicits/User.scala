package com.agilogy.scalabcn.implicits

case class User(username:String) {
  def canCreateIssues = true

  def canViewIssues = true
}

