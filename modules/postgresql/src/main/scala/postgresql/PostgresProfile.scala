package postgresql

import com.github.tminglei.slickpg._

trait PostgresProfile extends ExPostgresProfile with PgDate2Support {

  override val api: API2 = new API2

  class API2 extends super.API with DateTimeImplicits {}
}

object PostgresProfile extends PostgresProfile
