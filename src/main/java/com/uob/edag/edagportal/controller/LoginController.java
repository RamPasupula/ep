package com.uob.edag.edagportal.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uob.edag.services.User;
import com.uob.edag.services.UserService;
import com.uob.edag.services.impl.UserServiceimpl;
import com.uob.edag.util.ErrorMessage;

 
@RestController
@RequestMapping("/")
public class LoginController {
 
	
	
	UserService userService;
  // @RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage() {
        return "home";
    }
    
   //@RequestMapping("/userLogin")
   @RequestMapping(value="/userLogin",method = RequestMethod.POST)
	public String login(@RequestBody String userStr, HttpServletRequest request) {
		ErrorMessage errorMessage;
  
		JSONObject jsonObject = new JSONObject(userStr);

		String domain = jsonObject.getString("domain");
	
		User user = new User(jsonObject.getString("userid"), jsonObject.getString("passwd"),request.getRemoteAddr(), domain);
		userService = new UserServiceimpl();
		 errorMessage = userService.getUserById(user);
		
			
		 ObjectMapper mapper = new ObjectMapper();
		 String jsonString = null;
		 try {
			 jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
		
			e.getCause();
		}
		
        return jsonString;
      
	}
}
