package adventofcode;

public class Puzzle10 {

	public static String lookAndSay(String value, char lastChar, int lengthOfLastChar){
		String ret = null;
		if(value.length() == 0){
			ret = String.valueOf(lengthOfLastChar) + String.valueOf(lastChar); 
		}else if(value.charAt(0) == lastChar){
			ret = lookAndSay(value.length()>1 ? value.substring(1):"", lastChar, ++lengthOfLastChar);
		}else {
			ret = String.valueOf(lengthOfLastChar) + String.valueOf(lastChar);
			ret += lookAndSay(value.substring(0), value.charAt(0), 0);
		}
		
		return ret;
	}
	
	
	public static String lookAndSay(String value){
		
		char lastChar = value.charAt(0);
		int count = 0;
		
		final StringBuilder builder = new StringBuilder();
		for(int pos=0;pos<value.length();pos++){
			if(value.charAt(pos) == lastChar){
				count++;
			}else if(value.charAt(pos) != lastChar){
				builder.append(count).append(String.valueOf(lastChar));
				count = 1;
			}
			
			lastChar = value.charAt(pos);
			//current++;
		}
		
		builder.append(count).append(String.valueOf(lastChar));
		
		
		
		return builder.toString();
	}
	
	public static void main(String...strings) {
		String current = "1113222113";
		System.out.println(current);
		//System.out.println(lookAndSay(current));
		for(int i=0; i<50;i++){
			current = lookAndSay(current);
			System.out.println(current);
		}
		
		System.out.println("-------------------------");
		System.out.println("Longeur : " + current.length());
		
		
	}
}
