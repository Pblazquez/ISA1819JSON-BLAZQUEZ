package salud.isa.gsonMedDB;
import java.io.IOException;
import com.google.gson.stream.JsonReader;

public abstract class Cadena {
	protected Cadena sucesor;
	protected String respuesta;
	
	public Cadena getSucesor() {
		return this.sucesor;
	}
	
	public void setSucesor(Cadena s) {
		sucesor=s;
	}
	
	abstract public String analisis(String name, JsonReader JLector) throws IOException;	
}
