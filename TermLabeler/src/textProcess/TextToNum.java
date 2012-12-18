package textProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextToNum {

	/**
	 * @param args
	 */
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testing = "Esta es una super cadena de super prueba.";
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		HashMap<String, Integer> vk = new HashMap<String, Integer>();
		TextPrePro text = new TextPrePro(testing);
		List<String> tokens = text.getToks();
		List<Integer> nums = new ArrayList<Integer>();
		int k = 0;
		for (String i: tokens){
			if (hm.containsValue(i)) continue;
			hm.put(k, i);
			k ++;
		}
		for (Integer name: hm.keySet()){
			Integer key = name;
			String value = hm.get(name);
			vk.put(value, key);
			System.out.println(value + " : " + key);
		}
		
		for (int i = 0; i < tokens.size(); i++){
			nums.add(vk.get(tokens.get(i)));
		}
		System.out.println(nums.toString());

	}

}
