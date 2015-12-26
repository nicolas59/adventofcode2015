package adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Puzzle17 {

	static Set<Integer> numberOfContainers = new HashSet<>();
	
	public static int numberOfSolutions(int rest, Integer[] containers, List<Integer> items){
		int ret = 0;
		if(rest<0){
			return 0;
		}
		
		if(containers.length == 0){
			//pas de solution
		}else{
			for(int index=0;index<containers.length;index++){
				if(rest - containers[index] == 0){
					numberOfContainers.add(items.size() +1);
					ret++;
				}
					Integer[] newContainers = new Integer[containers.length-index-1];
					//System.out.println(index);
					System.arraycopy(containers, index+1, newContainers,0, containers.length - index-1);
					List<Integer> copy = new ArrayList<>(5);
					if(items!=null){
						copy.addAll(items);
					}
					
					copy.add(containers[index]);
					
					
					ret += numberOfSolutions(rest-containers[index], newContainers, copy);
				
			}
		}
		return ret;
	}
	
	//150	
	public static void main(String[] args) {
		Integer[] containers = {11,
				30,
				47,
				31,
				32,
				36,
				3,
				1,
				5,
				3,
				32,
				36,
				15,
				11,
				46,
				26,
				28,
				1,
				19,
				3};
		//int liters = 150;
		int liters = 150;
		Arrays.sort(containers, Comparator.<Integer>comparingInt(item -> (Integer)item).reversed());
		
		System.out.println(numberOfSolutions(liters, containers, null));
		//System.out.println(stack.size());
	
		numberOfContainers.stream().forEach(System.out::println);
	}
	
	

}
