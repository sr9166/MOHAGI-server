package com.mohagi.server.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mohagi.server.model.User;
import com.mohagi.server.repository.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping(value = "/{id}") 
    public User read(@PathVariable String id) {
    	Optional<User> optional = userRepository.findById(id);
    	User user = new User();
    	try {
    	user = optional.get();
    	} catch (NoSuchElementException e) {
    		System.err.println("No Such Element Exception: No value present");
    	}
        return user;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable String id) {
        userRepository.deleteById(id); 
    }
    
    public String showall() throws JSONException {
    	JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(User user : userRepository.findAll()) {
			JSONObject data = new JSONObject();
			data.put("user_id", user.getUser_id());
			data.put("user_pw", user.getUser_pw());
			data.put("user_name", user.getUser_name());
			data.put("birthdate", user.getBirthdate());
			data.put("gender", user.getGender());
			data.put("latest_filter", user.getLatest_filter());
			jsonArray.put(data);
		}
		jsonObject.put("users", jsonArray);
		return jsonObject.toString();
    }
}
