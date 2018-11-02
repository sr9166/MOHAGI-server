package com.mohagi.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mohagi.server.model.location;

public class Util {
	public static double calDistance(double lat1, double lon1, double lat2, double lon2){

        double theta, dist;
        theta = lon1 - lon2;
        dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);

        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;    // 단위 mile 에서 km 변환.
        dist = dist * 1000.0;      // 단위  km 에서 m 로 변환

        return dist;
    }
	
	// 주어진 도(degree) 값을 라디언으로 변환
    static double deg2rad(double deg){
        return (double)(deg * Math.PI / (double)180d);
    }

    // 주어진 라디언(radian) 값을 도(degree) 값으로 변환
    static double rad2deg(double rad){
        return (double)(rad * (double)180d / Math.PI);
    }
    
    public static location SplitToLocation(String[] split) {
		String loc_id = split[0];
		String loc_name = split[1];
		String loc_addr = split[2];
//		String loc_number = split[3];
		String loc_time = split[4];
		String big_ctg = split[5];
		String small_ctg = split[6];
		String latitude = split[7];
		String longitude = split[8];
		String star = split[9];
		String category = split[10];
		String fav_man = split[11];
		String fav_woman = split[12];
		String atmosphere = split[13];
		String topic = split[14];
		String purpose = split[15];
		String fav_10 = split[16];
		String fav_20 = split[17];
		String fav_30 = split[18];
		String fav_40 = split[19];
		String fav_50 = split[20];
		String fav_60 = split[21];
		
		location loc = new location(loc_id, loc_name, loc_addr, loc_time, big_ctg, small_ctg, latitude, longitude, star, category, fav_man, fav_woman, atmosphere, topic, purpose, fav_10, fav_20, fav_30, fav_40 ,fav_50, fav_60);
		return loc;
	}
    
    public static String smalltypeConvertToId(String smalltype) {
		if(smalltype.equals("한식"))
			return "1";
		else if(smalltype.equals("일식"))
			return "2";
		else if(smalltype.equals("중식"))
			return "3";
		else if(smalltype.equals("양식"))
			return "4";
		else if(smalltype.equals("분식"))
			return "5";
		else if(smalltype.equals("카페"))
			return "6";
		else if(smalltype.equals("당구장"))
			return "7";
		else if(smalltype.equals("피시방"))
			return "8";
		else if(smalltype.equals("노래방"))
			return "9";
		else if(smalltype.equals("방탈출"))
			return "10";
		else if(smalltype.equals("영화"))
			return "11";
		else if(smalltype.equals("연극"))
			return "12";
		else if(smalltype.equals("전시회"))
			return "13";
		else if(smalltype.equals("쇼핑"))
			return "14";
		else if(smalltype.equals("공원"))
			return "15";
		else if(smalltype.equals("볼링장"))
			return "16";
		return "";
	}
    
    public static String smalltypeConvertToString(String id) {
    	if(id.equals("1"))
			return "한식";
		else if(id.equals("2"))
			return "일식";
		else if(id.equals("3"))
			return "중식";
		else if(id.equals("4"))
			return "양식";
		else if(id.equals("5"))
			return "분식";
		else if(id.equals("6"))
			return "카페";
		else if(id.equals("7"))
			return "당구장";
		else if(id.equals("8"))
			return "피시방";
		else if(id.equals("9"))
			return "노래방";
		else if(id.equals("10"))
			return "방탈출";
		else if(id.equals("11"))
			return "영화";
		else if(id.equals("12"))
			return "연극";
		else if(id.equals("13"))
			return "전시회";
		else if(id.equals("14"))
			return "쇼핑";
		else if(id.equals("15"))
			return "공원";
		else if(id.equals("16"))
			return "볼링장";
		return "";
    }
    
    public static Statement initialize() {
    	Statement st = null;
    	try {
  	      // create our mysql database connection
  	      String myUrl = "jdbc:mysql://localhost/mohagi";
  	      Connection conn = DriverManager.getConnection(myUrl, "root", "root");

  	      // create the java statement
  	      st = conn.createStatement();
  	      
  	      // execute the query, and get a java resultset
//  	      rs = st.executeQuery(query);
  	      
//  	      st.close();
  	    } catch (Exception e) {
  	      System.err.println(e.getMessage());
  	    }
    	return st;
    }
}
