package com.se.ue04.controller;

import com.se.ue04.model.BookedDrive;
import com.se.ue04.model.User;
import com.se.ue04.model.Vehicle;
import com.se.ue04.service.BookedDrivesService;
import com.se.ue04.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/login/")
@Api(value = "LoginAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    // create new user
    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create a new user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    public void saveUser(@RequestBody User userToSave) {
        loginService.saveUser(userToSave);
    }

    @RequestMapping(path = "{email}", method = RequestMethod.GET)
    @ApiOperation("Get user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = User.class)})
    public User getUserByEmail(@PathVariable(name = "email") String email) {
        return loginService.getUserByEmail(email);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get user by email and password")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = User.class)})
    public User getUserByEmailAndPassword(@RequestBody User userToValidate) {
        return loginService.getUserByEmailAndPassword(userToValidate.getEmail(), userToValidate.getPassword());
    }

}
