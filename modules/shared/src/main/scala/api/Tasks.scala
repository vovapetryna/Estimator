package api

import upickle.default._

trait Tasks extends API {
  protected val root: Path[Unit] = apiPath / "tasks"
  import Tasks._

  val allListing: Endpoint[shared.TaskFetchData, api.Response[Listing.Response]] = postEndpoint(Listing, root / "tasks" / "allListing")
  val create: Endpoint[shared.TaskInfo, api.Response[shared.Task]]               = postEndpoint(Create, root / "tasks" / "create")
  val start: Endpoint[Long, api.Response[Boolean]]                               = postEndpoint(Start, root / "tasks" / "start")
}

object Tasks {
  object Create extends TypedEndpoint {
    type Request  = shared.TaskInfo
    type Response = shared.Task
  }

  object Start extends TypedEndpoint {
    type Request  = Long
    type Response = Boolean
  }

  object Listing extends TypedEndpoint {
    case class RespEntity(entities: List[shared.Task])
    object RespEntity {
      implicit val rw: ReadWriter[RespEntity] = macroRW
    }
    type Request  = shared.TaskFetchData
    type Response = RespEntity
  }
}
