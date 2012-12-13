package textProcess;

import java.util.ArrayList;
import java.util.List;

public class WTextPreProcess {

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

	public WTextPreProcess (String text) {
		// TODO Auto-generated method stub
		if (text == null) {
			infoboxes = null;
			cleanText = null;
			knownTerms = null;
		}
		
		int singleChar = 0;
		String infobox = "";
		String link = "";
		int flag = 0;
		infoboxes = new ArrayList<String>();
		cleanText = "";

		//Start reading the file char by char
		while (singleChar < text.length()){
			String myChar = text.substring(singleChar, singleChar + 1);
			
			//With this loop it gets all the infoboxes and store them in an
			//array of infoboxes.
			if (myChar.equals("{")) flag ++;
			if (flag != 0){ 
				infobox = infobox.concat(myChar);
				if (myChar.equals("}")) flag--;
				if (flag == 0){
					infoboxes.add(infobox);
					infobox = "";
				}
				singleChar ++;
				continue;
			}
			
			if (myChar.equals("[")) flag ++;
			if (flag != 0){
				link = link.concat(myChar);
				if (myChar.equals("|")){
					knownTerms.add(link);
					link = "";
					flag = 0;
				}
			}
			singleChar ++;
		}
		
		knownTerms = getKnownTerms();
		if (knownTerms != null){
			for (String term : knownTerms){
				System.out.println(term);
			}
		}
		
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




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

//private static List<String> getInfoboxes(String text) 
//throws IOException {
//// TODO Auto-generated method stub
//if (text == null) return null;
//
//int singleChar = 0;
//String infobox = "";
//int flag = 0;
//List <String> infoboxes = new ArrayList<String>();
//
//while (singleChar < text.length()){
//String myChar = text.substring(singleChar, singleChar + 1);
//
//
////With this loop it gets all the infoboxes and store them in an
////array of infoboxes.
//if (myChar.equals("{")) flag ++;
//if (flag != 0){ 
//	infobox = infobox.concat(myChar);
//	singleChar ++;
//	if (myChar.equals("}")) flag--;
//	if (flag == 0){
//		infoboxes.add(infobox);
//		infobox = "";
//	}
//	continue;
//}
//
//singleChar ++;
//}
//
//if (infoboxes.isEmpty()) return null;
//
//return infoboxes;
//}
