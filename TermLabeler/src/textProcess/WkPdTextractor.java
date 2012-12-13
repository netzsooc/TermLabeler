package textProcess;

import java.io.IOException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;


public class WkPdTextractor {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	
	static String wikitext(String article, String lang) 
			 throws JDOMException, IOException {
		// TODO Auto-generated method stub
		Element out = wt(article, lang);
		
		if (out == null) return null;
		String output = out.getText();
		if (output == ""){
			String redir = out.getAttributeValue("title");
			return wt(redir, lang).getText();
		}
		return output;
	}

	
	static String wikitext(String article) 
			 throws JDOMException, IOException {
		return wikitext(article, "es");
	}

	
	private static Element wt(String article, String lang) 
			 throws JDOMException, IOException {
		// TODO Auto-generated method stub
		article = article.replaceAll(" ", "_");
		
		URL wp = null;
		
		if (lang == "es") {
			wp = new URL("http://es.wikipedia.org/wiki/Especial:Exportar/" + 
							  article);
		}
		
		if (lang == "en") {
			wp = new URL("http://en.wikipedia.org/wiki/Special:Export/" + 
					  article);
		}
		
		Document doc = new SAXBuilder().build(wp);
		Element root = doc.getRootElement();
		Namespace ns = root.getNamespace();
		
		Element page = root.getChild("page", ns);
		if (!root.getChildren().contains(page)) return null;
		
		Element redi = page.getChild("redirect", ns);
		if (page.getChildren().contains(redi)) return redi;
		
		return page.getChild("revision", ns).getChild("text", ns);
	}

}
