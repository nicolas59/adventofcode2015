package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

interface Rule {
	boolean apply(String string);
}


class VowelRule implements Rule {

	@Override
	public boolean apply(String string) {
		List<Character> vowels = new ArrayList<Character>();
		vowels.add(new Character('a'));
		vowels.add(new Character('e'));
		vowels.add(new Character('i'));
		vowels.add(new Character('o'));
		vowels.add(new Character('u'));
		
		string.chars().forEach(c -> vowels.remove(c));
		
		return vowels.size() <=2;
	}
	
}


class TwiceRule implements Rule {

	@Override
	public boolean apply(String string) {
		boolean state = false;
		for(int index = 0; index < string.length()-1 || !state; index++){
			if(string.charAt(index) == string.charAt(index +1)){
				state = true;
			}
		}
		return state;
	}
	
}

class NotContainRule implements Rule {

	
	
	@Override
	public boolean apply(String string) {
		// TODO Auto-generated method stub
		
		Pattern pattern = Pattern.compile("(ab|cd|pq|xy)");
		return !pattern.matcher(string).find();
	}
	
}

public class Puzzle5 {

	static List<Rule> rules = new ArrayList<Rule>() ;
	static {
		rules.add(new Rule(){
			@Override
			public boolean apply(String string) {
				return string.chars().filter(c -> c=='a' || c=='e' || c=='i' || c=='o' || c=='u').count() >= 3;
			}
		});
		
		rules.add(new Rule(){
			@Override
			public boolean apply(String string) {
				boolean state = false;
				for(int index = 0; index < string.length()-1 && !state; index++){
					if(string.charAt(index) == string.charAt(index + 1)){
						state = true;
					}
				}
				return state;
			}
		});
		
		rules.add(new Rule(){
			@Override
			public boolean apply(String string) {
				// TODO Auto-generated method stub
				
				Pattern pattern = Pattern.compile("(ab|cd|pq|xy)");
				return !pattern.matcher(string).find();
			}
		});
	
		
	}
	
	public static boolean match(final String string){
		return rules.stream().filter(r -> r.apply(string)).count() == 3;
	}
	
	
	public static void main(String...strings) throws Exception{
		System.out.println(match("ugknbfddgicrmopn"));
		System.out.println(match("aaa"));
		System.out.println(match("jchzalrnumimnmhp"));
		System.out.println(match("dvszwmarrgswjxmb"));
	
		long start = System.currentTimeMillis();
		System.out.println(Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle5input.txt").toURI()))
	        .stream().filter(line -> match(line)).count());
	   
	}
}
