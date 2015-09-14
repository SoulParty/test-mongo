package lt.kitech.controller;

import lt.kitech.model.Person;
import lt.kitech.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> findPersons(
            @RequestParam(required = false) String name,
            @RequestParam String street){
        return personService.findPeople(name, street);
    }

}
