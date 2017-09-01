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
        addCorsHeader(response);
        AppRunnable appRunnable = new AppRunnable(request, response);
        appRunnable.run(endpointReflection);
    }

    private void addCorsHeader(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }

}
