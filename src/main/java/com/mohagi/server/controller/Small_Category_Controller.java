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

import com.mohagi.server.model.small_category;
import com.mohagi.server.repository.small_category_Repository;

@RestController
@RequestMapping("/smallctg")
public class Small_Category_Controller {
	@Autowired
    small_category_Repository smallctgRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody small_category smallctg) {
    	smallctgRepository.save(smallctg);
    }

    @RequestMapping(value = "/{id}") 
    public small_category read(@PathVariable String id) {
    	Optional<small_category> optional = smallctgRepository.findById(id);
    	small_category smallctg = new small_category();
    	try {
    		smallctg = optional.get();
    	} catch (NoSuchElementException e) {
    		System.err.println("No Such Element Exception: No value present");
    	}
        return smallctg;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody small_category smallctg) {
    	smallctgRepository.save(smallctg);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable String id) {
    	smallctgRepository.deleteById(id); 
    }
    
    public String showall() throws JSONException {
    	JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(small_category smallctg : smallctgRepository.findAll()) {
			JSONObject data = new JSONObject();
			data.put("small_ctg_id", smallctg.getSmall_ctg_id());
			data.put("small_ctg_name", smallctg.getSmall_ctg_name());
			data.put("big_ctg_id", smallctg.getBig_ctg_id());
			jsonArray.put(data);
		}
		jsonObject.put("small_categories", jsonArray);
		return jsonObject.toString();
    }
}
