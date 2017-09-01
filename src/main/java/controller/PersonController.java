package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.PeopleRepository;
import entitites.Person;
import org.hygge.mvc.core.controller.Controller;
import org.hygge.mvc.core.controller.annotation.Post;
import org.hygge.mvc.core.run.result.JsonResult;
import org.hygge.mvc.core.run.result.Result;
import org.hygge.mvc.core.utilities.parameter.ParameterFilter;
import org.hygge.mvc.core.utilities.parameter.ParsedParameters;
import org.hygge.mvc.core.utilities.parameter.exception.ParameterParserException;

/**
 * Created by adam on 31/08/2017.
 */
public class PersonController extends Controller {
    public Result index() {
        return new JsonResult(PeopleRepository.getAll());
    }

    public Result index(Integer id) {
        return new JsonResult(PeopleRepository.getById(id));
    }

    @Post
    public Result add() throws JsonProcessingException {
        ParameterFilter filter = createParameterFilter();
        try {
            ParsedParameters parameters = filter.parseParameters(request);
            Person person = new Person(
                    parameters.getString("firstName"),
                    parameters.getString("lastName"),
                    parameters.getInteger("age")
            );
            PeopleRepository.addPerson(person);
            ObjectNode objectNode = createJsonNodes(person);
            return new JsonResult(objectNode);
        } catch (ParameterParserException e) {
            return new JsonResult("error",e.getMessage());
        }
    }

    private ObjectNode createJsonNodes(Person person) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("success","Person was added");
        objectNode.put("data",objectMapper.writeValueAsString(person));
        return objectNode;
    }

    private ParameterFilter createParameterFilter() {
        ParameterFilter filter = new ParameterFilter();
        filter.addString("firstName").setRequired();
        filter.addString("lastName").setRequired();
        filter.addInteger("age").setRequired();
        return filter;
    }
}
