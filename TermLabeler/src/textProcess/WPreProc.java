package textProcess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;

public class WPreProc {

	/**
	 * @param args
	 */

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	
	private List<String> infoboxes;
	private String cleanText;
	private List<String> knownTerms;

	public WPreProc (String text) {
		// TODO Auto-generated method stub
		if (text == null) {
			infoboxes = null;
			cleanText = null;
			knownTerms = null;
		}
		
		int singleChar = 0;
		infoboxes = new ArrayList<String>();
		cleanText = "";
		knownTerms = new ArrayList<String>();

		//Start reading the file char by char
		while (singleChar < text.length()){
			String myChar = text.substring(singleChar, singleChar + 1);
			
			if (myChar.equals("{")){
				singleChar += getThisBox(infoboxes, "{", "}", myChar, 
						singleChar, text);
				continue;
			}
//			
			if (myChar.equals("[")){
				singleChar += getThisBox(knownTerms, "[", "]", myChar, 
						singleChar, text, true, cleanText);
				continue;
			}
			
			cleanText += myChar;
			singleChar ++;
		}
		
		knownTerms = getKnownTerms();
		
	}

	public List<String> getKnownTerms() {
		return knownTerms;
	}
	
	public List<String> getInfoboxes() {
		return infoboxes;
	}
	
	public String getCleanText() {
		return cleanText;
	}

	public int getThisBox (List<String> modifiable, String triggerIn, 
			String triggerOut, String currentChar, int index,
			String text){
		return getThisBox (modifiable, triggerIn, triggerOut, currentChar,
				index, text, false, "");
	}
	
	public int getThisBox (List<String> modifiable, String triggerIn, 
			String triggerOut, String currentChar, int index,
			String text, boolean record, String clean){
		int flag = 0;
		String box = "";
		int k = 0;
		do { 
			if (currentChar.equals(triggerIn)) flag ++;
//			System.out.println(box);
			box = box.concat(currentChar);
			if (record){
				clean = clean.concat(currentChar);
			}
			if (currentChar.equals(triggerOut)) flag--;
			if (flag == 0){
				modifiable.add(box);
				box = "";
			} 
			index ++;
			if (index == text.length()) break;
			currentChar = text.substring(index, index + 1);
			k ++;
		} while (flag != 0);
		return k + 1;
	}


	public static void main(String[] args) throws JDOMException, IOException {
		// TODO Auto-generated method stub
		String wiki =  WkPdTextractor.wikitext("Enrique Peña Nieto", "en");
		WPreProc text = new WPreProc(wiki);
		System.out.println("infoboxes");
//		System.out.println(text.getInfoboxes());
		System.out.println(text.getInfoboxes().size());
		System.out.println("links");
//		System.out.println(text.getKnownTerms());
		System.out.println(text.getKnownTerms().size());
		System.out.println("clean");
		System.out.println(text.getCleanText());
		System.out.println(text.getCleanText().length());

	}

}


