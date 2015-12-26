package adventofcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Puzzle9 {
	
	
	static class Town {
		String name;
		
		Map<Town, Integer> links = new HashMap<Town, Integer>();
		
		Town(String name){
			this.name = name;
		}
		
		void add(Town town, int distance){
			this.links.put(town, distance);
		}
		
		
		Integer[] calculateDistance(Town end){
			List<Integer> distances = new ArrayList<Integer>();
			Optional<Town> found = this.links.keySet().stream().filter(t -> t.equals(end)).findFirst();
			
			if(found.isPresent()){
				distances.add(this.links.get(found).intValue());
			}else{
				for(Map.Entry<Town, Integer> entry:this.links.entrySet()){
					Integer[] temp =  entry.getKey().calculateDistance(end);
					if(temp.length == 0){
						
					}else{
						for(Integer p:temp){
							distances.add(entry.getValue() + p);
						}
					}
				}
			}
			return distances.toArray(new Integer[distances.size()]);
		}
		
	}

	public static void main(String[] args) {
		Town london = new Town("London");
		Town dublin = new Town("dublin");
		Town belfast = new Town("Belfast");
		
		london.add(dublin, 464);
		london.add(belfast, 518);
		dublin.add(belfast, 141);
		
		
		List<Town> towns = new ArrayList<Town>() ;
		//{london, dublin, belfast};
		
		
		

	}

}
