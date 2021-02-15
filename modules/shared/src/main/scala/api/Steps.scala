package api

trait Steps extends API {
  protected val root: Path[Unit] = apiPath / "steps"
  import Steps._

  val create: Endpoint[shared.step.Info, api.Response[shared.step.Short]] = postEndpoint(Create, root / "create")
}

object Steps {
  object Create extends TypedEndpoint {
    type Request  = shared.step.Info
    type Response = shared.step.Short
  }
}
