package salud.isa.gsonMedDB;
import java.io.IOException;
import com.google.gson.stream.JsonReader;

public class Medicine extends Cadena {

	private static final String MEDICINE = "medicines";
	private static final String NAME = "name";
		
	public Medicine(Cadena med) throws IOException {
		setSucesor(med);
		}

	private StringBuffer readMedicines(JsonReader JLector) throws IOException {
		StringBuffer medicineData = new StringBuffer();
		JLector.beginArray();
		while (JLector.hasNext()) {
			JLector.beginObject();
			medicineData.append(readMedicineEntry(JLector)).append("\n");
			JLector.endObject();
		}
		medicineData.append("\n");
		JLector.endArray();
		return medicineData;
	}
	
	private String readMedicineEntry(JsonReader JLector) throws IOException {
		String med = null;
		while(JLector.hasNext()){
			String name = JLector.nextName();
			switch (name) {
			case NAME:
				med = JLector.nextString();
				break;
			default:
				JLector.skipValue();
			}
		}
		return med;
	}

	
	
	@Override
	public String analisis(String name, JsonReader JLector) throws IOException {
		StringBuffer readData = new StringBuffer();
		if (name.equals(MEDICINE)) {
		
			readData.append(readMedicines(JLector)).append("\n");
		}else if (sucesor!=null){
			readData.append(sucesor.analisis(name, JLector));
		}else {
			JLector.skipValue();
			readData.append("Category not processed : "+name ).append("\n");
		}
		
		return readData.toString();
	}
}
