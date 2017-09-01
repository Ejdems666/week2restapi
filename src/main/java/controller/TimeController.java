package controller;

import org.hygge.mvc.core.controller.Controller;
import org.hygge.mvc.core.run.result.JsonResult;
import org.hygge.mvc.core.run.result.Result;

import java.time.Instant;

/**
 * Created by adam on 01/09/2017.
 */
public class TimeController extends Controller{
    public Result index() {
        return new JsonResult("time",Instant.now());
    }
}
