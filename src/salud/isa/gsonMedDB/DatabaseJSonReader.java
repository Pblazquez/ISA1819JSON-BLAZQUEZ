package salud.isa.gsonMedDB;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

/**
 * Created by José Blázquez Sánchez on 11/04/2019.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {
	
	private Cadena cadena;
	private JsonReader json;
	public DatabaseJSonReader(JsonReader JLector){
		json=JLector;
	}

	public void setCadenaDeMando(Cadena d) {
		cadena=d;
	}
	public String parse() throws IOException {
					
		json.beginObject();
		StringBuffer readData = new StringBuffer();
		
		while (json.hasNext()) {
			String name = json.nextName();
			readData.append(cadena.analisis(name,json));
		}

		json.endObject();
		json.close();
		json.close();

		return readData.toString();
	}

}
