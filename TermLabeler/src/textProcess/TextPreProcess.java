package textProcess;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;

public class TextPreProcess {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static void main(String[] args) 
			throws JDOMException, IOException {
		// TODO Auto-generated method stub
		String text = WkPdTextractor.wikitext("Enrique Peña Nieto");
		String out = wiklean(text);
		System.out.println(out);

	}

	private static String wiklean(String text) {
		// TODO Auto-generated method stub
		String infobox = getInfobox(text);
		List<String> knownTerms = getKnownTerms(text);
		
		return null;
	}

	private static List<String> getKnownTerms(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getInfobox(String text) {
		// TODO Auto-generated method stub
		return null;
	}

}
