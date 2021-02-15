package api

trait Authentication extends API {
  protected val root: Path[Unit] = apiPath
  import Authentication._

  val login: Endpoint[shared.account.Credentials, api.Response[Boolean]] = postEndpoint(Login, root / "login")
  val logout: Endpoint[Unit, api.Response[Boolean]]                      = postEndpoint(Logout, root / "logout")
  val registration: Endpoint[shared.account.Info, api.Response[Boolean]] = postEndpoint(Registration, root / "registration")
}

object Authentication {
  object Login extends TypedEndpoint {
    type Request  = shared.account.Credentials
    type Response = Boolean
  }

  object Logout extends TypedEndpoint {
    type Request  = Unit
    type Response = Boolean
  }

  object Registration extends TypedEndpoint {
    type Request  = shared.account.Info
    type Response = Boolean
  }
}
