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

import com.mohagi.server.model.big_category;
import com.mohagi.server.repository.big_category_Repository;

@RestController
@RequestMapping("/bigctg")
public class Big_Category_Controller {
	@Autowired
    big_category_Repository bigctg_repository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody big_category bigctg) {
    	bigctg_repository.save(bigctg);
    }

    @RequestMapping(value = "/{id}") 
    public big_category read(@PathVariable String id) {
    	Optional<big_category> optional = bigctg_repository.findById(id);
    	big_category bigctg = new big_category();
    	try {
    		bigctg = optional.get();
    	} catch (NoSuchElementException e) {
    		System.err.println("No Such Element Exception: No value present");
    	}
        return bigctg;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody big_category bigctg) {
    	bigctg_repository.save(bigctg);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable String id) {
    	bigctg_repository.deleteById(id); 
    }
    
    public String showall() throws JSONException {
    	JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(big_category bigctg : bigctg_repository.findAll()) {
			JSONObject data = new JSONObject();
			data.put("big_ctg_id", bigctg.getBig_ctg_id());
			data.put("big_ctg_name", bigctg.getBig_ctg_name());
			jsonArray.put(data);
		}
		jsonObject.put("big_categories", jsonArray);
		return jsonObject.toString();
    }
}
