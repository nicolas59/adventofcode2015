package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle13 {

	static final Map<String, Map<String, Integer>> relations = new HashMap<String, Map<String, Integer>>();
	
	public static int calcul(List<String> chaises){
		System.out.println(chaises);
		int score = 0;
		for(int index = 0; index <chaises.size(); index++){
			String person = chaises.get(index);
			Map<String, Integer> rels = relations.get(person);
			if(index == chaises.size()-1){
				score += rels.get(chaises.get(0));
			}else{
				score += rels.get(chaises.get(index+1));
			}
			
			if(index == 0){
				score += rels.get(chaises.get(chaises.size()-1));	
			}else {
				score += rels.get(chaises.get(index-1));	
			}
		}
		return score;
	}
	
	public static int displayMax(Map<String, Map<String, Integer>> relations, int max, List<String> chaises){
		if(relations.isEmpty()){
			//
			return calcul(chaises);
		}
		
		for(Map.Entry<String, Map<String, Integer>> entry : relations.entrySet()){
			List<String> chaisesTmp;
			if(chaises == null){
				chaisesTmp = new ArrayList<>();
			}else{
				chaisesTmp = new ArrayList<>(chaises);
			}
			
			chaisesTmp.add(entry.getKey());
			
			Map<String, Map<String, Integer>> relationsCopy = new HashMap<String, Map<String, Integer>>(relations);
			relationsCopy.remove(entry.getKey());
			
			int maximun = displayMax(relationsCopy, max, chaisesTmp);
			if(maximun > max ){
				max = maximun;
			}
			
		}
		
		return max;
	}
	
	
	public static void main(String ...strings) throws Exception {
		
		
		
		Pattern pattern = Pattern.compile("(.+) would (gain|lose) (\\d+) happiness units by sitting next to (.+)\\.");
		
		Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle13input.txt").toURI()))
        .stream().forEach(line -> 
        	{
        		Matcher m = pattern.matcher(line);
        		if(m.find()){
        			String name1 = m.group(1);
        			String sens = m.group(2);
        			Integer value = Integer.parseInt(m.group(3));
        			String name2 = m.group(4);
        			
        			Map<String, Integer> assos = relations.get(name1);
        			if(assos == null){
        				assos = new HashMap<>();
        				if(!name1.equals("Nicolas")){
        					assos.put("Nicolas", Integer.parseInt("0"));
        				}
        				relations.put(name1, assos);
        			}
        			
        			assos.put(name2, "lose".equals(sens)? -value:value);
        			
        			
        		}
        	}
        );

		relations.forEach((name, assos) -> {
			System.out.println(name + " : ");
			assos.forEach((name2, value) -> System.out.println("\t" + name2 + ":" + value)); 
		});
		
		
		System.out.println(displayMax(relations, 0, null));
		
		
		
	}
	
}
