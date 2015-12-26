package adventofcode;

import java.security.MessageDigest;

public class Puzzle4 {
	
	static String toHexa(byte[] bytes){
		    StringBuilder sb = new StringBuilder();
		    for (byte b : bytes) {
		        sb.append(String.format("%02X", b));
		    }
		   return sb.toString();
	}

	public static void main(String[] args)  throws Exception{
		// TODO Auto-generated method stub
		
		String st = "iwrupvqb";
		
		MessageDigest md = MessageDigest.getInstance("MD5");

		String result;
		long start = 0;
		while(true){
			String s = st + String.valueOf(start);
			
			//System.out.println(s);
			String hexa = toHexa(md.digest(s.getBytes()));
			if(hexa.startsWith("000000")){
				result = hexa;
				break;
			}
			start++;
		}
		
		System.out.println(start);
		System.out.println(result);
	}

}
