package api

import upickle.default._

trait Tasks extends API {
  protected val root: Path[Unit] = apiPath / "tasks"
  import Tasks._

  val allListing: Endpoint[shared.task.FetchData, api.Response[Seq[shared.task.WithSecondary]]] = postEndpoint(Listing, root / "listing")
  val create: Endpoint[shared.task.Info, api.Response[shared.task.Short]]                       = postEndpoint(Create, root / "create")
  val addSecondary: Endpoint[shared.task.Info, api.Response[shared.task.Short]]                 = postEndpoint(AddSecondary, root / "addSecondary")
  val edit: Endpoint[shared.task.Short, api.Response[Boolean]]                                  = postEndpoint(Edit, root / "edit")
  val deleteTask: Endpoint[shared.task.Short, api.Response[Boolean]]                            = postEndpoint(DeleteTask, root / "delete")
  val start: Endpoint[Long, api.Response[Boolean]]                                              = postEndpoint(Start, root / "start")
}

object Tasks {
  object Create extends TypedEndpoint {
    type Request  = shared.task.Info
    type Response = shared.task.Short
  }

  object AddSecondary extends TypedEndpoint {
    type Request  = shared.task.Info
    type Response = shared.task.Short
  }

  object Edit extends TypedEndpoint {
    type Request  = shared.task.Short
    type Response = Boolean
  }

  object DeleteTask extends TypedEndpoint {
    type Request  = shared.task.Short
    type Response = Boolean
  }

  object Start extends TypedEndpoint {
    type Request  = Long
    type Response = Boolean
  }

  object Listing extends TypedEndpoint {
    type Request  = shared.task.FetchData
    type Response = Seq[shared.task.WithSecondary]
  }
}
