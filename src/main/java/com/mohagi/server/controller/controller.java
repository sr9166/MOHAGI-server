package com.mohagi.server.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import org.jooq.impl.DSL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohagi.server.model.User;
import com.mohagi.server.model.location;
import com.mohagi.server.repository.imageRepository;
import com.mohagi.server.util.DatabaseConnection;
import com.mohagi.server.util.Util;

@RestController
@RequestMapping(method = RequestMethod.GET, value = "/mohagi")
public class controller {

	@Autowired
	UserController userController;
	@Autowired
	Big_Category_Controller bigctgController;
	@Autowired
	Small_Category_Controller smallctgController;
	@Autowired
	Filter_Controller filterController;
	@Autowired
	Location_Controller locController;
	@Autowired
	imageRepository imgRepository;
	@Autowired
	BookmarkListController bmController;
	
	@RequestMapping(method = RequestMethod.GET, value = "/str")
	public String abc(@RequestParam String str) {
		return str + "echo";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String mainview() {
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public String userview() throws JSONException {
		return userController.showall();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/big_category")
	public String bigctgview() throws JSONException {
		return bigctgController.showall();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/small_category")
	public String smallctgview() throws JSONException {
		return smallctgController.showall();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/filter")
	public String filterview() throws JSONException {
		return filterController.showall();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/location")
	public String locationview() throws JSONException {
		return locController.showall();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/check_overlap")
	public String check_overlap(String user_id) {
		User user = userController.read(user_id);
		if(user.getUser_id() == null) {
			return "No value present";
		} else {
			return "This value present";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sign_up")
	public String sign_up(User user) {
		System.out.println("Sign up\t" + user.toString());
		try {
			userController.create(user);
		} catch(Exception e) {
			e.printStackTrace();
			return "Sign up Failed";
		}
		return "Sign up success";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/withdrawal")
	public String withdrawal(String user_id) {
		try {
			userController.delete(user_id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
		System.out.println("withdrawal " + user_id);
		return "withdrawal success!";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update_profile")
	public String update_profile(User user) {
		try {
			userController.update(user);
		} catch (Exception e) {
			return e.getMessage();
		}
		System.out.println("update profile : " + user.toString());
		return "update success!";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/sign_in")
	public String sign_in(String user_id, String user_pw) {
		User user = userController.read(user_id);
		if(!user.getUser_pw().equals(user_pw)) {
			System.err.println("Sign in Failed");
			return "Sign in Failed";
		} else {
			System.out.println("Sign in " + user.toString());
			return user.toString();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/loc_add")
	public void loc_add() throws IOException {
		File file = new File("singingroom.txt");
		FileReader filereader = new FileReader(file);
		BufferedReader fr = new BufferedReader(filereader);
		String line = "";
		ArrayList<location> array = new ArrayList<>();
		while ((line = fr.readLine()) != null) {
			String[] temp = line.split("\t");
			Random random = new Random();
			String id = "";
			while(true) {
				id = String.valueOf(random.nextInt((int) Math.pow(10, 10)));
				location overlap = locController.read(id);
				if(overlap == null) {
					break;
				}
			}
			//아이디, 이름, 주소, 전화번호, 시간, 대분류, 소분류, 위도, 경도, 별점
			//			location loc = new location(id, temp[0], temp[1], temp[2], temp[4], "3", "8", temp[6], temp[7], temp[5]);

			//아이디, 이름, 주소, 시간, 대분류, 소분류, 위도, 경도, 별점, 분류, 남자선호도, 여자선호도, 분위기, 인기토픽, 찾는목적, 10대~60대 선호도 
			location loc = new location(id, temp[0], temp[2], temp[4], "1", "1", temp[17], temp[18], temp[16], temp[1], temp[5], temp[6], temp[7], temp[8], temp[9], temp[10], temp[11], temp[12], temp[13], temp[14], temp[15]);
			array.add(loc);
		}
		fr.close();

		for(location loc : array) {
			locController.create(loc);
			//			System.out.println(loc.toString());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/img", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] img(String id) {
		File file = null;
		byte[] fileContent = null;
		try {
			id = URLDecoder.decode(id,"utf-8");
			file = new File(String.format("C:\\Users\\LeeHyunGyu\\eclipse-workspace\\MOHAGI_DataCrawler\\img\\/%s.png", id));
			fileContent = Files.readAllBytes(file.toPath());
		} catch(Exception e) {
			file = new File("C:\\Users\\LeeHyunGyu\\eclipse-workspace\\MOHAGI_DataCrawler\\img\\default.png");
			try {
				fileContent = Files.readAllBytes(file.toPath());
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
			}
		}
		
		return fileContent;
	}

	/*
	@RequestMapping(method = RequestMethod.GET, value = "/imgsave")
	public String imgsave() throws IOException {
		File file = new File("imgName.txt");
		FileReader filereader = new FileReader(file);
		BufferedReader fr = new BufferedReader(filereader);
		String line = "";
		ArrayList<String> array = new ArrayList<>();
		while ((line = fr.readLine()) != null) {
			array.add(line);
		}
		fr.close();
		System.out.println(array.size());
		
		
		for(int i = 0; i < array.size(); i++) {
			File imgfile = new File("C:\\Users\\LeeHyunGyu\\eclipse-workspace\\MOHAGI_DataCrawler\\img\\" + array.get(i) + ".png");
			byte[] fileContent = Files.readAllBytes(imgfile.toPath());
			imgRepository.save(new Image(String.valueOf(new Random().nextInt(10000)), array.get(i), fileContent));
			String inTime = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
			System.out.println(i + " " + inTime);
		}
		
		return "savedIMAGE";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/imgload", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] imgload() throws IOException {
		Optional<Image> opt = imgRepository.findById("1");
		byte[] fileContent = opt.get().getImagebuffer();
		return fileContent;
	}
	*/
	
	/*
	@RequestMapping(method = RequestMethod.POST, value = "query")
	public String query() {
		String retMsg = "";
		try
	    {
	      // create our mysql database connection
	      String myUrl = "jdbc:mysql://localhost/mohagi";
	      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM user";

	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        String user_id = rs.getString("user_id");
	        String user_pw = rs.getString("user_pw");
	        String user_name = rs.getString("user_name");
	        String birthdate = rs.getString("birthdate");
	        String gender = rs.getString("gender");
	        
	        // print the results
	        String str = String.format("id = %s, pw = %s, name = %s, birthdate = %s, gender = %s", user_id, user_pw, user_name, birthdate, gender);
	        System.out.println(str);
	        retMsg = DSL.using(conn).fetch(rs).formatJSON();
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return retMsg;
	}
	*/
	
	@RequestMapping(method = RequestMethod.POST, value = "exeQuery")
	public ResultSet query(String query) {
		ResultSet rs = null;
		try {
	      Statement st = DatabaseConnection.getStatement();
	      
	      // execute the query, and get a java resultset
	      rs = st.executeQuery(query);
	      
	    } catch (Exception e) {
	      System.err.println(e.getMessage());
	    }
		return rs;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "exeQueryToString")
	public String queryToString(String query) {
		String retMsg = "";
		try {
	      Statement st = DatabaseConnection.getStatement();
	      Connection conn = DatabaseConnection.getConnection();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      retMsg = DSL.using(conn).fetch(rs).formatJSON();
	    } catch (Exception e) {
	      System.err.println(e.getMessage());
	    }
		return retMsg;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/recommend")
	public String recommend(@RequestParam String Lat, 
			@RequestParam String Lng, 
			@RequestParam String with_who, 
			@RequestParam String bigtype, 
			@RequestParam String smalltype, 
			@RequestParam String theme1, 
			@RequestParam String theme2) throws JSONException {
		
		String query = "";
		if(theme1.equals(""))
			query = String.format("select * from location l where l.small_ctg = %s and l.star >= 3.5",Util.smalltypeConvertToId(smalltype));
		else
			query = String.format("select * from location l natural join loc_name_filter f where l.small_ctg = %s and l.star >= 3.5 and f.filter1 in ('%s', '%s')",Util.smalltypeConvertToId(smalltype), theme1, theme2);
		
		String result = queryToString(query);
		
		ArrayList<location> locArray = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("records");
			for(int i = 0; i < jsonArray.length(); i++) {
				result = jsonArray.get(i).toString();
				result = result.substring(1,result.length() - 2);
				String[] split = result.replaceAll("\"", "").split(",");
				location loc = null;
				if(theme1.equals(""))
					loc = Util.SplitToLocation(split);
				else
					loc = Util.SplitToLocationNaturalJoinFilter(split);
				locArray.add(loc);
			}
		} catch (JSONException e) {
			System.err.println(e.getMessage());
		}
		
		/*
		 * 거리순 정렬
		 */
		Collections.sort(locArray, new Comparator<location>() {
            @Override
            public int compare(location l1, location l2) {
            	double userlat = Double.parseDouble(Lat);
            	double userlng = Double.parseDouble(Lng);
            	double lat1 = Double.parseDouble(l1.getLatitude());
            	double lng1 = Double.parseDouble(l1.getLongitude());
            	double lat2 = Double.parseDouble(l2.getLatitude());
            	double lng2 = Double.parseDouble(l2.getLongitude());
            	double distance1 = Util.calDistance(userlat, userlng, lat1, lng1);
            	double distance2 = Util.calDistance(userlat, userlng, lat2, lng2);
                if (distance1 < distance2) {
                    return -1;
                } else if (distance1 > distance2) {
                    return 1;
                }
                return 0;
            }
        });
		
		/*
		 * 상위 5개 jsonArray로 만들어서 return
		 */
		JSONObject returnObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for(int i = 0; i < locArray.size(); i++) {
			String temp = locArray.get(i).toJson();
			JSONObject jsonObject = new JSONObject(temp);
			jsonArray.put(jsonObject);
			if(i == 5)
				break;
		}
		returnObject.put("locations", jsonArray);
		System.out.println("recommend\t" + returnObject.toString());
		return returnObject.toString();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/home")
	public String home() throws JSONException, SQLException, IOException {
		File file = new File("imgName.txt");
		FileReader filereader = new FileReader(file);
		BufferedReader fr = new BufferedReader(filereader);
		String line = "";
		ArrayList<String> array = new ArrayList<>();
		while ((line = fr.readLine()) != null) {
			array.add(line.replace("\ufeff", ""));
		}
		fr.close();
		array.remove("default");
		
		Random random = new Random();
		int size = array.size();
		int randomNumber = random.nextInt(size);
		String query = String.format("select l.loc_name, loc_addr, loc_number, star, filter1, filter2, filter3 from location l left outer join loc_name_filter f on l.loc_name = f.loc_name where l.loc_name like '%s'", array.get(randomNumber));
		String result = queryToString(query);
		
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("records");
			result = jsonArray.get(0).toString();
		} catch (JSONException e) {
			System.err.println(e.getMessage());
		}
		
		JSONObject jsonObject = new JSONObject();
		result = result.substring(1,result.length() - 2);
		String[] split = result.replaceAll("\"", "").split(",");
		jsonObject.put("place_name", split[0]);
		jsonObject.put("place_address", split[1]);
		jsonObject.put("phone", split[2]);
		jsonObject.put("star", split[3]);
		
		if(!split[4].contains("n")) {
			jsonObject.put("theme1", split[4]);
			jsonObject.put("theme2", split[5]);
			jsonObject.put("theme3", split[6]);
		} else {
			jsonObject.put("theme1", "존맛");
			jsonObject.put("theme2", "친절");
			jsonObject.put("theme3", "저렴");			
		}
		jsonObject.put("img", String.format("http://163.180.116.251:8080/mohagi/img?id=%s", split[0]));
		
		System.out.println("home\t" + split[0] + "\t" + split[1] + "\t" + split[2] + "\t" + split[3] + "\t" + split[4] + "\t" + split[5] + "\t" + split[6]);
		
		return jsonObject.toString();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/detail")
	public String detail(String loc_id) throws JSONException, SQLException {
		String result = queryToString(String.format("select l.loc_name, l.loc_addr, f.filter1, f.filter2, f.filter3, l.star, l.big_ctg, l.small_ctg from location l left outer join loc_name_filter f on l.loc_name = f.loc_name where l.loc_id like %s", loc_id));
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("records");
			result = jsonArray.get(0).toString();

		} catch (JSONException e) {
			System.err.println(e.getMessage());
		}
		
		JSONObject jsonObject = new JSONObject();
		result = result.substring(1,result.length() - 1);
		
		System.out.println(result);
		
		String[] split = result.replaceAll("\"", "").split(",");
		jsonObject.put("place_name", split[0]);
		jsonObject.put("place_address", split[1]);
		jsonObject.put("theme1", split[2]);
		jsonObject.put("theme2", split[3]);
		jsonObject.put("theme3", split[4]);
		jsonObject.put("place_image", String.format("http://163.180.116.251:8080/mohagi/img?id=%s", split[0]));
		jsonObject.put("star", split[5]);
		jsonObject.put("big_category", Util.bigtypeConvertToString(split[6]));
		jsonObject.put("small_category", Util.smalltypeConvertToString(split[7]));
		
		System.out.println("Detail\t" + jsonObject.toString());
		
		return jsonObject.toString();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "CategoryInBoundary")
	public String ctginboundary(String lat, String lng, String boundary, String smallctg) {
		System.out.println(String.format("LAT : %s LNG : %s BOUND : %s CTG : %s", lat, lng, boundary, smallctg));
		Double dlat = Double.parseDouble(lat);
		Double dlng = Double.parseDouble(lng);
		Double dbound = Double.parseDouble(boundary);
		String result = queryToString(String.format("select * from location where small_ctg=%s", Util.smalltypeConvertToId(smallctg)));
		ArrayList<location> locArray = new ArrayList<>();
		ArrayList<location> resultArray = new ArrayList<>();
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("records");
			for(int i = 0; i < jsonArray.length(); i++) {
				result = jsonArray.get(i).toString();
				result = result.substring(1,result.length() - 2);
				String[] split = result.replaceAll("\"", "").split(",");
				location loc = Util.SplitToLocation(split);
				locArray.add(loc);
			}
		} catch (JSONException e) {
			System.err.println(e.getMessage());
		}
		
		for(int i = 0; i < locArray.size(); i++) {
			location loc = locArray.get(i);
			Double distance = Util.calDistance(dlat, dlng, Double.parseDouble(loc.getLatitude()), Double.parseDouble(loc.getLongitude()));
			if(distance < dbound) {
				resultArray.add(loc);
			}
		}
		
		
		JSONArray jArray = new JSONArray();
		JSONObject returnObject = new JSONObject();
		for(location loc : resultArray) {
			try {
				jArray.put(new JSONObject(loc.toString().substring(9)));
			} catch (JSONException e) {
			}
		}

		try {
			returnObject.put("locations", jArray);
		} catch (JSONException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Category In Boundary\t" + returnObject.toString());
		return returnObject.toString();
	}
	
	/*
	@RequestMapping(method = RequestMethod.GET, value = "/addtheme")
	public String addtheme() throws IOException, InterruptedException {
		File file = new File("review_west.txt");
		FileReader filereader = new FileReader(file);
		BufferedReader fr = new BufferedReader(filereader);
		String line = "";
		ArrayList<String> array = new ArrayList<>();
		while ((line = fr.readLine()) != null) {
			array.add(line);
		}
		fr.close();
		System.out.println(array.size());
		
//		String totalquery ="";
		Statement st = Util.initialize();
		for(int i = 0; i < array.size(); i++) {
			String[] words = array.get(i).split("_");
			if(words.length != 4) {
				System.err.println(words[0] + "\t" + words.length);
				continue;
			}
			String name = words[0];
			String theme1 = words[1];
			String theme2 = words[2];
			String theme3 = words[3];
			String query = String.format("INSERT INTO loc_name_filter (loc_name, filter1, filter2, filter3) VALUES ('%s', '%s', '%s', '%s');", name, theme1, theme2, theme3);
//			System.out.println(query);
//			totalquery += query;
//			query(query);
			
			try {
				st.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			String inTime = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
			System.out.println(i + " " + inTime); 
		}
		
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "add theme";
	}
	*/
	
	@RequestMapping(method = RequestMethod.POST, value = "/getBookmarklist")
	public String getBookmarklist(String user_id) throws JSONException {
		String query = String.format("select * from bookmarklist where user_id like '%s'",user_id);
		
		String result = queryToString(query);
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("records");
			for(int i = 0; i < jsonArray.length(); i++) {
				result = jsonArray.get(i).toString();
				result = result.substring(1,result.length() - 1);
				
				String[] words = result.replaceAll("\"", "").split(",");
				JSONObject json = new JSONObject();
				json.put("id", words[0]);
				json.put("user_id", words[1]);
				json.put("loc_id", words[2]);
				location loc = locController.read(words[2]);
				
				query = String.format("SELECT l.loc_name, l.small_ctg, f.filter1, f.filter2, f.filter3 "
						+ "FROM location l LEFT OUTER JOIN loc_name_filter f "
						+ "ON l.loc_name = f.loc_name WHERE l.loc_name LIKE '%s'", loc.getLoc_name());
				String result2 = queryToString(query);
				
				/*
				 * SELECT l.loc_name, l.small_ctg, f.filter1, f.filter2, f.filter3 FROM location l LEFT OUTER JOIN loc_name_filter f ON l.loc_name = f.loc_name WHERE l.loc_name LIKE
				 */	
				
				JSONObject jo = new JSONObject(result2);
				JSONArray ja = jo.getJSONArray("records");
				result2 = ja.get(0).toString();
				result2 = result2.substring(1,result2.length() - 1);

				words = result2.replaceAll("\"", "").split(",");
				json.put("theme1", words[2]);
				json.put("theme2", words[3]);
				json.put("theme3", words[4]);
				json.put("loc_id", loc.getLoc_id());
				json.put("loc_name", loc.getLoc_name().replaceAll("//ufeff", ""));
				json.put("loc_addr", loc.getLoc_addr());
				json.put("loc_number", loc.getLoc_number());
				json.put("loc_time", loc.getLoc_time());
				json.put("big_ctg", Util.bigtypeConvertToString(loc.getBig_ctg()));
				json.put("small_ctg", Util.smalltypeConvertToString(loc.getSmall_ctg()));
				json.put("latitude", loc.getLatitude());
				json.put("longitude", loc.getLongitude());
				json.put("star", loc.getStar());
				json.put("category", loc.getCategory());
				json.put("fav_man", loc.getFav_man());
				json.put("fav_woman", loc.getFav_woman());
				json.put("atmosphere", loc.getAtmosphere());
				json.put("topic", loc.getTopic());
				json.put("purpose", loc.getPurpose());
				json.put("fav_10", loc.getFav_10());
				json.put("fav_20", loc.getFav_20());
				json.put("fav_30", loc.getFav_30());
				json.put("fav_40", loc.getFav_40());
				json.put("fav_50", loc.getFav_50());
				json.put("fav_60", loc.getFav_60());
				
				jArray.put(json);
			}
		} catch (JSONException e) {
			System.err.println(e.getMessage());
		}
		jObject.put("bookmarks", jArray);
		System.out.println("Get BookmarkList\t" + jObject.toString());
		return jObject.toString();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addBookmark")
	public String addBookmark(String user_id, String loc_id) {

		Random random = new Random();
		String query = String.format("INSERT INTO `bookmarklist` (`id`, `user_id`, `loc_id`) VALUES ('%d', '%s', '%s');", random.nextInt(100000), user_id, loc_id);
		try {
			query(query);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return e.getMessage();
		}
		System.out.println(String.format("Add Bookmark Success!, user_id : %s, loc_id %s", user_id, loc_id));
		return "add Bookmark success";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteBookmark")
	public String deleteBookmark(String user_id, String loc_id) {
		
		String query = String.format("DELETE FROM bookmarklist WHERE user_id LIKE '%s' AND loc_id LIKE '%s';", user_id, loc_id);
		try {
			query(query);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return e.getMessage();
		}
		System.out.println(String.format("Delete Bookmark Success!, user_id : %s, loc_id %s", user_id, loc_id));
		return "delete Bookmark success!";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteAllBookmark")
	public String deleteAllBookmark(String user_id) {
		
		String query = String.format("DELETE FROM bookmarklist WHERE user_id LIKE '%s';", user_id);
		try {
			query(query);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return e.getMessage();
		}
		System.out.println(String.format("Delete All Bookmark Success!, user_id : %s", user_id));
		return "delete All Bookmark success!";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteBookmarks")
	public String deleteBookmarks(String user_id, String loc_ids) {
		String str = loc_ids;
		String[] loc_id = loc_ids.split(",");
		
		for(String id : loc_id) {
			String query = String.format("DELETE FROM bookmarklist WHERE user_id LIKE '%s' and loc_id like '%s';", user_id, id);
			try {
				query(query);
			} catch(Exception e) {
				System.err.println(e.getMessage());
				return e.getMessage();
			}
		}
		System.out.println(String.format("Delete Bookmarks Success!, user_id : %s, loc_ids : ", user_id, str));
		return "delete Bookmarks success!";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/isBookmark")
	public boolean isBookmark(String user_id, String loc_id) {
		boolean flag = false;
		String query = String.format("SELECT * FROM bookmarklist WHERE user_id LIKE '%s' and loc_id like '%s';", user_id, loc_id);
		String result = queryToString(query);

		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("records");
			if(jsonArray.length() > 0)
				flag = true;
		} catch (JSONException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(String.format("Check overlap isBookmark user_id : %s, loc_id : %s flag : %b", user_id, loc_id, flag));
		return flag;
	}
	
	
}