package textProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextPrePro {

	/**
	 * @param args
	 */
	private List<String> words = null;
	private List<String> punctuation = null;
	private List<String> tokens = null;
		
	public TextPrePro (String text){
		if (text == null) return;
		words = new ArrayList<String>();
		tokens = new ArrayList<String>();
		punctuation = new ArrayList<String>();
		Pattern pattern = Pattern.compile("(\\(*)(\\w+'?\\w*)(\\S*)",
				Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(text);
				
		while (matcher.find()){
			if (! matcher.group(1).equals("")){
				punctuation.add(matcher.group(1));
				tokens.add(matcher.group(1));
			}

			words.add(matcher.group(2));
			tokens.add(matcher.group(2));
			if (! matcher.group(3).equals("")){
				punctuation.add(matcher.group(3));
				tokens.add(matcher.group(3));
			}
		}
		
	}
	

	public List<String> getWords() {
		return words;
	}
	
	
	public List<String> getPunct() {
		return punctuation;
	}
	
	
	public List<String> getToks() {
		return tokens;
	}

}
