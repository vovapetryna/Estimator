package postgresql

import com.github.tminglei.slickpg._
import slick.ast.BaseTypedType
import slick.jdbc.JdbcType

trait PostgresProfile extends ExPostgresProfile with PgDate2Support {

  override val api: API2 = new API2

  class API2 extends super.API with DateTimeImplicits {
    implicit def actionToJsonMapper: JdbcType[shared.task.Action] with BaseTypedType[shared.task.Action] =
      MappedColumnType.base[shared.task.Action, String](a => upickle.default.write(a), s => upickle.default.read[shared.task.Action](s))
  }
}

object PostgresProfile extends PostgresProfile
