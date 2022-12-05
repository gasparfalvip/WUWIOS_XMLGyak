package WUWIOS;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.*;
import org.json.simple.JSONObject;

public class JsonWriteWUWIOS {

	public static void main(String[] args) {

		JSONObject vizsgak = new JSONObject();
		
		JSONObject kurzus_1 = new JSONObject();

		kurzus_1.put("kurzus", "ELektronika");
		kurzus_1.put("helyszin", "XXXVII.");
		kurzus_1.put("oktato", "Szabó Norbert");
		kurzus_1.put("jegy", "2");
		
		JSONObject idopont = new JSONObject();
		
		idopont.put("nap", "Hétfo");
		idopont.put("tol", "12:00");
		idopont.put("ig", "14:00");
		
		kurzus_1.put("idopont", idopont);
		
		JSONObject kurzus_2 = new JSONObject();
		
		kurzus_2.put("kurzus", "Számítógépes hálózatok II.");
		kurzus_2.put("helyszin", "A1/105");
		kurzus_2.put("oktato", "Dr. Kovács Szilveszter");
		kurzus_2.put("jegy", "5");
		
		JSONObject idopont_2 = new JSONObject();
		
		idopont_2.put("nap", "Szerda");
		idopont_2.put("tol", "8:00");
		idopont_2.put("ig", "10:00");
		
		kurzus_2.put("idopont", idopont_2);
		
		JSONObject json = new JSONObject();
		
		json.put("vizsga", List.of(kurzus_1, kurzus_2));
		
		vizsgak.put("_id", "WUWIOS");
		vizsgak.put("vizsgak", json);

		try (FileWriter file = new FileWriter("vizsgak1WUWIOS.json")) {
			file.write(vizsgak.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
