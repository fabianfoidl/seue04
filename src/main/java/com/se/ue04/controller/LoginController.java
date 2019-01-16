package com.se.ue04.controller;

import com.se.ue04.model.Account;
import com.se.ue04.service.LoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/login/")
@Api(value = "/api/login", tags = "/login", description = "manage logins and registration")
public class LoginController {

    LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    // create new user
    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new user", notes = "Post to save a new user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Error in request")})
    public void saveUser(@ApiParam(value = "User model in JSON format. All fields must be provided. Email = primary key. Password should be encoded with BCryptPasswordEncoder. Role can be 'ROLE_USER' or 'ROLE_ADMIN'. AN EXISTING USER WILL BE OVERWRITTEN!", required = true) @RequestBody Account userToSave) {
        loginService.saveUser(userToSave);
    }

    @RequestMapping(path = "{email}", method = RequestMethod.GET)
    @ApiOperation(value = "Get user by email", notes = "Will return the user, if exists. If not, no body will be returned.", response = Account.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Account.class), @ApiResponse(code = 400, message = "Error in request")})
    public Account getUserByEmail(@ApiParam(value = "Email as simple string.", required = true) @PathVariable(name = "email") String email) {
        return loginService.getUserByEmail(email);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get user by email and password for validation.", notes = "Will also return the user object as JSON.", response = Account.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Account.class), @ApiResponse(code = 400, message = "Error in request")})
    public Account getUserByEmailAndPassword(@ApiParam(value = "User as user model (in JSON format). Data can be gotten via 'Get user by email'. Password should be encoded. If user is not found (only email and password are checked), no result body is provided", required = true) @RequestBody Account userToValidate) {
        return loginService.getUserByEmailAndPassword(userToValidate.getEmail(), userToValidate.getPassword());
    }

}
