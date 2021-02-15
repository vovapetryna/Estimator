package api

import upickle.default._

trait Tasks extends API {
  protected val root: Path[Unit] = apiPath / "tasks"
  import Tasks._

  val allListing: Endpoint[shared.task.FetchData, api.Response[Seq[shared.task.WithSteps]]] = postEndpoint(Listing, root / "listing")
  val create: Endpoint[shared.task.Info, api.Response[shared.task.Short]]                   = postEndpoint(Create, root / "create")
  val start: Endpoint[Long, api.Response[Boolean]]                                          = postEndpoint(Start, root / "start")
}

object Tasks {
  object Create extends TypedEndpoint {
    type Request  = shared.task.Info
    type Response = shared.task.Short
  }

  object Start extends TypedEndpoint {
    type Request  = Long
    type Response = Boolean
  }

  object Listing extends TypedEndpoint {
    type Request  = shared.task.FetchData
    type Response = Seq[shared.task.WithSteps]
  }
}
