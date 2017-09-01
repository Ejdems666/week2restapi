import org.hygge.mvc.core.router.EndpointReflection;
import org.hygge.mvc.core.router.Route;
import org.hygge.mvc.core.router.Router;
import org.hygge.mvc.core.run.AppRunnable;
import org.hygge.mvc.core.run.servlet.RequestDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 31/08/2017.
 */
public class MyRequestDispatcher extends RequestDispatcher {
    @Override
    protected void routeToController(HttpServletRequest request, HttpServletResponse response, String url) {
        Router router = new Router(
                request.getMethod(),
                new Route("(?<controller>[a-z\\-]+)(?<method>/[a-z\\-]+)?(?<int>/\\d+)?", "Default", "index")
        );
        EndpointReflection endpointReflection = router.getControllerReflection("controller", url);
        AppRunnable appRunnable = new AppRunnable(request, response);
        appRunnable.run(endpointReflection);
    }
}
