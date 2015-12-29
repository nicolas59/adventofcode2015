package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle23 {
	
	static Map<String, Integer> registres = new HashMap<>();
	
	static int getValue(String registre){
		if(!registres.containsKey(registre)){
			if("a".equals(registre)){
				registres.put(registre, new Integer(1));
			}else{
				registres.put(registre, new Integer(0));
			}
			
		}
		return registres.get(registre);
	}
	
	static void applyInstrcution(String[] instructions){
		Pattern pattern = Pattern.compile("(jio|inc|tpl|jmp|jie|hlf) ((a|b)|([\\+|-]+\\d*)*)(, ([\\+|-]{1}\\d+)){0,1}");
		
		
		int currentIndex = 0;
		do{
			String current = instructions[currentIndex];
			System.out.println(currentIndex + " - " + current);
			
			Matcher matcher = pattern.matcher(current);
			String registre;
			String offset;
			int value =0;
			if(matcher.find()){
				String inst = matcher.group(1);
				
				switch(inst) {
				case "jio":
					registre = matcher.group(3);
					offset = matcher.group(6);
					if(getValue(registre) == 1){
						int jump = Integer.parseInt(offset);
						currentIndex += jump;
					}else{
						currentIndex++;
					}
					break;
				case "jie":
					registre = matcher.group(3);
					offset = matcher.group(6);
					if(getValue(registre)%2 == 0){
						int jump = Integer.parseInt(offset);
						currentIndex += jump;
					}else{
						currentIndex++;
					}
					break;
				case "inc":
					registre = matcher.group(3);
					value = getValue(registre);
					registres.put(registre, ++value);
					currentIndex++;
					break;
				case "tpl":
					registre = matcher.group(3);
					value = getValue(registre);
					registres.put(registre, value * 3);
					currentIndex++;
					break;
				case "hlf":
					registre = matcher.group(3);
					value = getValue(registre);
					registres.put(registre, value / 2);
					currentIndex++;
					break;
				case "jmp":
					offset = matcher.group(4);
					int jump = Integer.parseInt(offset);
					currentIndex += jump;
					break;
				default:
					
				}				
			}
			
			
			
		}while(currentIndex>=0 && currentIndex <instructions.length);
		
		
	}
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List<String> lines = Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle23input.txt").toURI()));
		
		applyInstrcution(lines.toArray(new String[lines.size()]));
		
		
		registres.forEach((key, value) -> System.out.println(key + " : " + value));
	}

}
