package controller;

import org.hygge.mvc.core.controller.Controller;
import org.hygge.mvc.core.router.exceptions.IncorrectRequestMethod;
import org.hygge.mvc.core.run.result.JsonResult;
import org.hygge.mvc.core.run.result.Result;


/**
 * Created by adam on 01/09/2017.
 */
public class ErrorController extends Controller{
    public Result notFound(Exception e) {
        String message = "notFound";
        if (e instanceof IncorrectRequestMethod) {
            message = "IncorrectRequestMethod";
        }
        return new JsonResult("error",message);
    }
}
