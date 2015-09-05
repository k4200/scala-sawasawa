package models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._
import org.joda.time.{DateTime}


class ThreadSpec extends Specification {

  "Thread" should {

    val t = Thread.syntax("t")

    "find by primary keys" in new AutoRollback {
      val maybeFound = Thread.find(1L)
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = Thread.findBy(sqls.eq(t.id, 1L))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = Thread.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = Thread.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = Thread.findAllBy(sqls.eq(t.id, 1L))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = Thread.countBy(sqls.eq(t.id, 1L))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = Thread.create(id = 1L, title = "MyString", createdTimestamp = DateTime.now)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = Thread.findAll().head
      // TODO modify something
      val modified = entity
      val updated = Thread.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = Thread.findAll().head
      Thread.destroy(entity)
      val shouldBeNone = Thread.find(1L)
      shouldBeNone.isDefined should beFalse
    }
  }

}
