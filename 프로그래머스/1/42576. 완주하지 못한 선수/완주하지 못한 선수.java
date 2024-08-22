import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> runner = new HashMap<>();
        
        for (String i : participant) {
            runner.put(i,runner.getOrDefault(i,0) + 1);
        }
        
        for (String winner : completion) {
            runner.put(winner,runner.getOrDefault(winner,0) - 1);
        }
        
        for (String name : runner.keySet()) {
            if (runner.get(name) != 0) {
                return name;
            }
        }
        
        return null;
    }
}