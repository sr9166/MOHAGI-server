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

import com.mohagi.server.model.location;
import com.mohagi.server.repository.location_Repository;

@RestController
@RequestMapping("/location")
public class Location_Controller {
	@Autowired
    location_Repository locRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody location loc) {
    	locRepository.save(loc);
    }

    @RequestMapping(value = "/{id}") 
    public location read(@PathVariable String id) {
    	Optional<location> optional = locRepository.findById(id);
    	location loc = null;
    	try {
    		loc = optional.get();
    	} catch (NoSuchElementException e) {
    		System.err.println("No Such Element Exception: No value present");
    	}
        return loc;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody location loc) {
    	locRepository.save(loc);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable String id) {
    	locRepository.deleteById(id); 
    }
    
    public String showall() throws JSONException {
    	JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(location loc: locRepository.findAll()) {
			JSONObject data = new JSONObject();
			data.put("loc_id", loc.getLoc_id());
			data.put("loc_name", loc.getLoc_name());
			data.put("loc_addr", loc.getLoc_addr());
			data.put("loc_number", loc.getLoc_number());
			data.put("loc_time", loc.getLoc_time());
			data.put("big_ctg", loc.getBig_ctg());
			data.put("small_ctg", loc.getSmall_ctg());
			data.put("latitude", loc.getLatitude());
			data.put("longitude", loc.getLongitude());
			data.put("star", loc.getStar());
			jsonArray.put(data);
		}
		jsonObject.put("filters", jsonArray);
		return jsonObject.toString();
    }
}
