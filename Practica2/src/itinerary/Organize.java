
package itinerary;

import java.util.*;

import material.Pair;

/**
 *
 * @author mayte
 */
public class Organize {
    private HashMap<String, String> map;
    private String origin;
    
    
    public Organize (List<Pair<String,String>> lista){
        Set<String> origins = new HashSet<>();
        Set<String> destiny = new HashSet<>();
        map = new HashMap<>();
        for(Pair<String, String> pair: lista){
            map.put(pair.getFirst(), pair.getSecond());
            destiny.add(pair.getSecond());
            if(origins.contains(pair.getSecond())){
                origins.remove(pair.getSecond());
            }
            if(!destiny.contains(pair.getFirst())){
               origins.add(pair.getFirst());
            }
        }
        if(origins.size()==1){
            for(String s: origins){
                origin = s;
            }
        }
    }
    
    /**
     * Returns the itinerary to travel or thrown an exception
     * @return 
     */
    public List<String> itineratio(){
        if(origin==null){
            throw new RuntimeException("There is no origin");
        }
        else{
            LinkedList<String> list = new LinkedList<>();
            String act = origin;
            list.addLast(origin);
            while(map.containsKey(act)){
                act = map.get(act);
                list.addLast(act);
            }
            return list;
        }
    }
}
