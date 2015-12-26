package adventofcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle7 {

	private Map<String, Integer> stack = new HashMap<>();

	int and(int val1, int val2) {
		return val1 & val2;
	}

	int lshift(int val1, int val2) {
		return val1 << val2;
	}

	int rshift(int val1, int val2) {
		return val1 >> val2;
	}

	int not(int val1) {
		return 2* Short.MAX_VALUE - val1 + 1;
	}

	int or(int val1, int val2) {
		return val1 | val2;
	}

	enum Operation {
		AND("AND"), NOT("NOT"), RSHIFT("RSHIFT"), LSHIFT("LSHIFT"), OR("OR");

		String symbole;

		Operation(String symbole) {
			this.symbole = symbole;
		}
	}

	private Pattern assignation = Pattern.compile("^(\\w+) -> ([\\w]+)$");
	private Pattern operation = Pattern.compile("^(.*) (AND|OR|RSHIFT|LSHIFT) (.*) -> (\\w+)$");
	private Pattern notOperation = Pattern.compile("^NOT (.*) -> (\\w+)$");

	int getValue(String string) {
		int val;
		try {
			val = Integer.parseInt(string);
		}catch(NumberFormatException e){
			val = this.stack.get(string);
		}
		
		return val;
	}

	void parse(String line) throws Exception  {
		Matcher matcher = assignation.matcher(line);
		if (matcher.matches()) {
			String val1 = matcher.group(1);
			String result = matcher.group(2);
			try {
				this.stack.put(result, this.getValue(val1));
			}catch(NumberFormatException e){
				if(this.stack.containsKey(val1)){
					this.stack.put(result, this.getValue(val1));
				}else{
					throw new NullPointerException();	
				}
			}
			
			
			//this.stack.put(result, this.getValue(val1));
		}
		Matcher matcher2 = operation.matcher(line);
		if (matcher2.matches()) {
			int val1 = getValue(matcher2.group(1));
			Operation operation = Operation.valueOf(matcher2.group(2));
			
			int val2 = getValue(matcher2.group(3));
			switch (operation) {
			case AND:
				this.stack.put(matcher2.group(4), this.and(val1, val2));
				break;
			case RSHIFT:
				this.stack.put(matcher2.group(4), this.rshift(val1, val2));
				break;
			case LSHIFT:
				this.stack.put(matcher2.group(4), this.lshift(val1, val2));
				break;
			case OR:
				this.stack.put(matcher2.group(4), this.or(val1, val2));
				break;

			}
		}
		Matcher matcher3 = notOperation.matcher(line);
		if (matcher3.matches()) {
			String val1 = matcher3.group(1);
			String result = matcher3.group(2);
			this.stack.put(result, this.not(this.getValue(val1)));
		}
	}

	public String toString() {
		final StringBuilder builder = new StringBuilder();
		this.stack.forEach((item, val) -> builder.append(item).append(" : ").append(val).append("\n"));
		return builder.toString();
	}

	public static void main(String... strings) throws Exception {
		Puzzle7 p = new Puzzle7();
		/*
		 * System.out.println(p.and(5, 3)); System.out.println(p.lshift(5, 1));
		 * System.out.println(p.rshift(5, 2)); System.out.println(p.not(-8));
		 */

		/*p.parse("123 -> x");
		p.parse("456 -> y");
		p.parse("x AND y -> d");

		p.parse("x OR y -> e");
		p.parse("x LSHIFT 2 -> f");
		p.parse("y RSHIFT 2 -> g");
		p.parse("NOT x -> h");
		p.parse("NOT y -> i");*/
		
		List<String> lines = Files.readAllLines(Paths.get(Puzzle2.class.getResource("puzzle7input.txt").toURI()));
		
		while(!lines.isEmpty()){
			int indexToRemoved = 0;
			for(int index=0; index < lines.size(); index++) {
				try{
					p.parse(lines.get(index));
					indexToRemoved = index;
					break;
				}catch(Exception e){
					//break;
				}
			}
			lines.remove(indexToRemoved);
			//System.out.println(indexToRemoved);
		}
		
		
		
		
		System.out.println(p.toString());
		System.out.println("----------------------");
		System.out.println(p.getValue("a"));
		System.out.println(p.getValue("b"));

	}

}
