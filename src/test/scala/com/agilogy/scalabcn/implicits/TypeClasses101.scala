package com.agilogy.scalabcn.implicits

import org.scalatest.FlatSpec

class TypeClasses101 extends FlatSpec {

  behavior of "type classes"

  // In fact, there is a type class in the standard library
  def max[T:Ordering](l:List[T], ifEmpty:T):T = {
    val comparator = implicitly[Ordering[T]]
    if(l.isEmpty) ifEmpty else l.tail.fold(l.head){
      case (acc,elem) => if(comparator.gt(acc,elem)) acc else elem
    }
  }

  they should "provide polymorphism" in {
    val integers = List(2,42,23,5)
    assert(max(integers,0) === new Integer(42))
    val doubles = List(2.0,42.0,23.0,5.0)
    assert(max(doubles, 0.0) === 42.0)
  }

  they should "provide ad-hoc polymorphism" in {
    val agileJordi = User("agile_jordi")
    val ignasi35 = User("ignasi35")
    val issue0 = new Issue(IssueId(0), "zeroth", agileJordi, Seq.empty)
    val issue1 = new Issue(IssueId(1), "foo", agileJordi, Seq.empty)
    val issue2 = new Issue(IssueId(2), "foo", agileJordi, Seq(IssueComment("first", agileJordi), IssueComment("second", ignasi35)))
    val issue3 = new Issue(IssueId(3), "foo", agileJordi, Seq(IssueComment("one", ignasi35)))
    val issues = List(issue1, issue2, issue3)

    implicit val issueOrdering: Ordering[Issue] = new Ordering[Issue] {
      override def compare(x: Issue, y: Issue): Int = x.comments.size - y.comments.size
    }
    assert(max(issues, issue0) === issue2)

  }

}
