package salud.isa.gsonMedDB;
import java.io.IOException;
import com.google.gson.stream.JsonReader;

public class RescueMedicine extends Cadena {

	private static final String RESCUEPRESENTATION = "rescueMedicinePresentations";
	private static final String MEDICINEREF = "medicineRef";
	private static final String ACTIVEINGREF = "activeIngRef";
	private static final String INHALERREF = "inhalerRef";
	private static final String DOSE = "dose";
	private static final String ESPACIADOR = ";";
	
	public RescueMedicine(Cadena rescue) throws IOException {
		setSucesor(rescue);
	}

	private StringBuffer readRescueMedicinePresentations(JsonReader JLector) throws IOException {
		StringBuffer rescueMedicinePresentationData = new StringBuffer();
		JLector.beginArray();
		while (JLector.hasNext()) {
			JLector.beginObject();
			rescueMedicinePresentationData.append(readRescueMedicinePresentationEntry(JLector)).append("\n");
			JLector.endObject();
		}
		rescueMedicinePresentationData.append("\n");
		JLector.endArray();
		return rescueMedicinePresentationData;
	}
	
	private String readRescueMedicinePresentationEntry(JsonReader JLector) throws IOException {
		String med = null;
		String ai=null;
		String inh=null;
		String dose=null;
		while(JLector.hasNext()){
			String name = JLector.nextName();
			switch (name) {
			case MEDICINEREF:
				 med = JLector.nextString();
				break;
			case ACTIVEINGREF:
				 ai = JLector.nextString();
				break;
			case INHALERREF:
				 inh = JLector.nextString();
				break;
			case DOSE:
				 dose = JLector.nextString();
				break;
			default:
				JLector.skipValue();
			}

		}
		return med + ESPACIADOR + ai + ESPACIADOR +
				inh + ESPACIADOR + dose;
	}

	@Override
	public String analisis(String name, JsonReader JLector) throws IOException {
		StringBuffer readData = new StringBuffer();
		if (name.equals(RESCUEPRESENTATION)) {
			readData.append(readRescueMedicinePresentations(JLector)).append("\n");
		}else  if (sucesor!=null){
			readData.append(sucesor.analisis(name, JLector));
		}else {
			JLector.skipValue();
			readData.append("Category not processed : "+name);
			readData.append("\n");
		}
		return readData.toString();
	}
}
