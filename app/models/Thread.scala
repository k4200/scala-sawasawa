package models

import scalikejdbc._
import org.joda.time.{DateTime}

case class Thread(
  id: Long,
  title: String,
  programmerId: Option[Long] = None,
  createdTimestamp: DateTime,
  deletedTimestamp: Option[DateTime] = None) {

  def save()(implicit session: DBSession = Thread.autoSession): Thread = Thread.save(this)(session)

  def destroy()(implicit session: DBSession = Thread.autoSession): Unit = Thread.destroy(this)(session)

}


object Thread extends SQLSyntaxSupport[Thread] {

  override val tableName = "THREAD"

  override val columns = Seq("ID", "TITLE", "PROGRAMMER_ID", "CREATED_TIMESTAMP", "DELETED_TIMESTAMP")

  def apply(t: SyntaxProvider[Thread])(rs: WrappedResultSet): Thread = apply(t.resultName)(rs)
  def apply(t: ResultName[Thread])(rs: WrappedResultSet): Thread = new Thread(
    id = rs.get(t.id),
    title = rs.get(t.title),
    programmerId = rs.get(t.programmerId),
    createdTimestamp = rs.get(t.createdTimestamp),
    deletedTimestamp = rs.get(t.deletedTimestamp)
  )

  val t = Thread.syntax("t")

  override val autoSession = AutoSession

  def find(id: Long)(implicit session: DBSession = autoSession): Option[Thread] = {
    withSQL {
      select.from(Thread as t).where.eq(t.id, id)
    }.map(Thread(t.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[Thread] = {
    withSQL(select.from(Thread as t)).map(Thread(t.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(Thread as t)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[Thread] = {
    withSQL {
      select.from(Thread as t).where.append(where)
    }.map(Thread(t.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Thread] = {
    withSQL {
      select.from(Thread as t).where.append(where)
    }.map(Thread(t.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(Thread as t).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    id: Long,
    title: String,
    programmerId: Option[Long] = None,
    createdTimestamp: DateTime,
    deletedTimestamp: Option[DateTime] = None)(implicit session: DBSession = autoSession): Thread = {
    withSQL {
      insert.into(Thread).columns(
        column.id,
        column.title,
        column.programmerId,
        column.createdTimestamp,
        column.deletedTimestamp
      ).values(
        id,
        title,
        programmerId,
        createdTimestamp,
        deletedTimestamp
      )
    }.update.apply()

    Thread(
      id = id,
      title = title,
      programmerId = programmerId,
      createdTimestamp = createdTimestamp,
      deletedTimestamp = deletedTimestamp)
  }

  def save(entity: Thread)(implicit session: DBSession = autoSession): Thread = {
    withSQL {
      update(Thread).set(
        column.id -> entity.id,
        column.title -> entity.title,
        column.programmerId -> entity.programmerId,
        column.createdTimestamp -> entity.createdTimestamp,
        column.deletedTimestamp -> entity.deletedTimestamp
      ).where.eq(column.id, entity.id)
    }.update.apply()
    entity
  }

  def destroy(entity: Thread)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(Thread).where.eq(column.id, entity.id) }.update.apply()
  }

}
