package com.mng.fileoperation.controller;

import com.mng.fileoperation.model.Person;
import com.mng.fileoperation.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(value = "Person Api documentation")
public class PersonController {

    private final PersonService personService;

    @ApiOperation(value = "New person adding method")
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public void signUp(@RequestBody Person user) {
        personService.save(user);
    }

}
