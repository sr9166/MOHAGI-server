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

import com.mohagi.server.model.filter;
import com.mohagi.server.repository.filter_Repository;

@RestController
@RequestMapping("/filter")
public class Filter_Controller {
	@Autowired
    filter_Repository filterRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody filter filter) {
    	filterRepository.save(filter);
    }

    @RequestMapping(value = "/{id}") 
    public filter read(@PathVariable String id) {
    	Optional<filter> optional = filterRepository.findById(id);
    	filter filter = new filter();
    	try {
    		filter = optional.get();
    	} catch (NoSuchElementException e) {
    		System.err.println("No Such Element Exception: No value present");
    	}
        return filter;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody filter filter) {
    	filterRepository.save(filter);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable String id) {
    	filterRepository.deleteById(id); 
    }
    
    public String showall() throws JSONException {
    	JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(filter filter: filterRepository.findAll()) {
			JSONObject data = new JSONObject();
			data.put("filter_id", filter.getFilter_id());
			data.put("filter_name", filter.getFilter_name());
			jsonArray.put(data);
		}
		jsonObject.put("filters", jsonArray);
		return jsonObject.toString();
    }
}
