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

import com.mohagi.server.model.bookmarklist;
import com.mohagi.server.repository.bookmarklistRepository;

@RestController
@RequestMapping("/bookmarklist")
public class BookmarkListController {
	@Autowired
    bookmarklistRepository bmRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody bookmarklist bmlist) {
    	bmRepository.save(bmlist);
    }

    @RequestMapping(value = "/{id}") 
    public bookmarklist read(@PathVariable String id) {
    	Optional<bookmarklist> optional = bmRepository.findById(id);
    	bookmarklist bmlist = new bookmarklist();
    	try {
    		bmlist = optional.get();
    	} catch (NoSuchElementException e) {
    		System.err.println("No Such Element Exception: No value present");
    	}
        return bmlist;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody bookmarklist bmlist) {
    	bmRepository.save(bmlist);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable String id) {
    	bmRepository.deleteById(id); 
    }
    
    public String showall() throws JSONException {
    	JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(bookmarklist bmlist: bmRepository.findAll()) {
			JSONObject data = new JSONObject();
			data.put("id", bmlist.getId());
			data.put("user_id", bmlist.getUser_id());
			data.put("bookmark", bmlist.getBookmark());
			jsonArray.put(data);
		}
		jsonObject.put("bookmarklists", jsonArray);
		return jsonObject.toString();
    }
}
