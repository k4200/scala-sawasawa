package models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._


class ProgrammerSkillSpec extends Specification {

  "ProgrammerSkill" should {

    val ps = ProgrammerSkill.syntax("ps")

    "find by primary keys" in new AutoRollback {
      val maybeFound = ProgrammerSkill.find(1L, 1L)
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = ProgrammerSkill.findBy(sqls.eq(ps.programmerId, 1L))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = ProgrammerSkill.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = ProgrammerSkill.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = ProgrammerSkill.findAllBy(sqls.eq(ps.programmerId, 1L))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = ProgrammerSkill.countBy(sqls.eq(ps.programmerId, 1L))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = ProgrammerSkill.create(programmerId = 1L, skillId = 1L)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = ProgrammerSkill.findAll().head
      // TODO modify something
      val modified = entity
      val updated = ProgrammerSkill.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = ProgrammerSkill.findAll().head
      ProgrammerSkill.destroy(entity)
      val shouldBeNone = ProgrammerSkill.find(1L, 1L)
      shouldBeNone.isDefined should beFalse
    }
  }

}
