import components.{ManagerComponents, RepositoryComponents}
import controllers.HomeController
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import play.api.routing.Router._
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}


class RestApplicationLoader extends ApplicationLoader {
  def load(context: ApplicationLoader.Context): Application = {
    val components = new RestApiComponents(context)

    components.application
  }
}

class RestApiComponents(context: ApplicationLoader.Context)
  extends BuiltInComponentsFromContext(context)
    with HttpFiltersComponents
    with ManagerComponents
    with RepositoryComponents { // mix traits here

  lazy val homeController: HomeController = new HomeController(carManager, controllerComponents)
  lazy val router: Router = new Routes(httpErrorHandler, homeController, "/")

  override def httpFilters: Seq[EssentialFilter] = super.httpFilters
}