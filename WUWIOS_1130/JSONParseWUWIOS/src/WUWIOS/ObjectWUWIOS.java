package WUWIOS;

import java.io.File;

import org.json.*;  

public class ObjectWUWIOS {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		
		obj.put("Név", "BLászló");
		obj.put("Fizetes", "100000.0");
		obj.put("Kor", "21");
		
		System.out.println(obj);	
		
		
	}

}
