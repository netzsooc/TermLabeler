package textProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WPreProc {

	/**
	 * @param args
	 */

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	
	private List<String> infoboxes = null;
	private String cleanText = null;
	private List<String> links = null;
	private List<String> titles = null;
	private List<String> strong = null;
	private List<String> knownTerms = null;

	public WPreProc (String text) {
		if (text == null) return;
		
		int singleChar = 0;
		infoboxes = new ArrayList<String>();
		cleanText = "";
		links = new ArrayList<String>();
		titles = new ArrayList<String>();
		strong = new ArrayList<String>();
		knownTerms = new ArrayList<String>();
		
		//Extract knownTerms from certain patterns
		Pattern patStrong = Pattern.compile("''+([^'\n]+)''+");
		Pattern patTitles = Pattern.compile("==+([^=\n]+)==+");
		
		Matcher matStrong = patStrong.matcher(text);
		text = getRegulAll (matStrong, strong);

		Matcher matTitles = patTitles.matcher(text);
		text = getRegulAll (matTitles, titles);
		
		//Start reading the file char by char
		while (singleChar < text.length()){
			String myChar = text.substring(singleChar, singleChar + 1);
			
			//Get the boxes inside `{}' keys
			if (myChar.equals("{")){
				singleChar += getThisBox(infoboxes, "{", "}", myChar, 
						singleChar, text);
				continue;
			}
			
			if (myChar.equals("[")){
				
				singleChar += getThisBox(links, "[", "]", myChar, 
						singleChar, text);
				String addS = links.get(links.size() - 1);
				addS = addS.substring(addS.lastIndexOf("[") + 1,
						addS.indexOf("]")) + " ";
				if (addS.contains("|"))
					addS = addS.substring(addS.indexOf("|") + 1);
				
				cleanText += addS;
				continue;
			}
			
			cleanText += myChar;
			singleChar ++;
		}
		
		for (int i = 0; i < links.size(); i++ ){
			String current = links.get(i);
			current = current.substring(current.lastIndexOf("[") + 1);
			current = current.substring(0, current.indexOf("]"));
			if (current.contains("|")){
				current = current.substring(0, current.indexOf("|"));
			}
			links.set(i, current);
		}
		knownTerms.addAll(links);
		knownTerms.addAll(strong);
		knownTerms.addAll(titles);
		
	}

	private String getRegulAll(Matcher matcher, List<String> strings) {
		while (matcher.find()){
			strings.add(matcher.group(1));
		}
		return matcher.replaceAll("$1");
	}

	public List<String> getKnownTerms() {
		return knownTerms;
	}
	
	public List<String> getLinks() {
		return links;
	}
	
	public List<String> getTitles() {
		return titles;
	}
	
	public List<String> getSpecial() {
		return strong;
	}
	
	public String getCleanText() {
		return cleanText;
	}

	public List<String> getInfoboxes() {
		return infoboxes;
	}
	public int getThisBox (List<String> modifiable, String triggerIn, 
			String triggerOut, String currentChar, int index,
			String text){
		int flag = 0;
		String box = "";
		int k = 0;
		do { 
			if (currentChar.equals(triggerIn)) flag ++;
//			System.out.println(box);
			box = box.concat(currentChar);
		
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


//	public static void main(String[] args) throws JDOMException, IOException {
		// TODO Auto-generated method stub
//		String wiki =  WkPdTextractor.wikitext("Enrique Peña Nieto");
//		WPreProc text = new WPreProc(wiki);
//		System.out.println(text.getLinks());
//		System.out.println("infoboxes");
////		System.out.println(text.getInfoboxes());
//		System.out.println(text.getInfoboxes().size());
//		System.out.println("links");
////		System.out.println(text.getLinks());
//		System.out.println(text.getLinks().size());
//		System.out.println("titles");
////		System.out.println(text.getTitles());
//		System.out.println(text.getTitles().size());
//		System.out.println("special");
////		System.out.println(text.getSpecial());
//		System.out.println(text.getSpecial().size());
//		System.out.println("Known Terms");
////		System.out.println(text.getKnownTerms());
//		System.out.println(text.getKnownTerms().size());
//		System.out.println("clean");
//		System.out.println(text.getCleanText());
////		System.out.println(text.getCleanText().length());

//	}
//
}


