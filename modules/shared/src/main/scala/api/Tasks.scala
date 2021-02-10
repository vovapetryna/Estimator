package api

trait Tasks extends API {
  protected val root: Path[Unit] = apiPath / "tasks"
  import Tasks._

  val create: Endpoint[shared.TaskInfo, api.Response[shared.Task]] = postEndpoint(Create, root / "create")
  val start: Endpoint[Long, api.Response[Boolean]]                 = postEndpoint(Start, root / "start")
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
}
