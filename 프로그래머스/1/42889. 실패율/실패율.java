import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        Map<Integer, Float> failureRates = new HashMap<>();
        
        for (int stage = 1; stage <= N; stage++) {
            int totalPlayers = 0;
            int failedPlayers = 0;
            
            for (int i = 0; i < stages.length; i++) {
                if (stages[i] >= stage) {
                    totalPlayers++;
                }
                if (stages[i] == stage) {
                    failedPlayers++;
                }
            }
            
            float failureRate = (totalPlayers == 0) ? 0 : (float) failedPlayers / totalPlayers;
            failureRates.put(stage, failureRate);
        }
        
        Map<Integer, Float> sortedMap = failureRates.entrySet()
            .stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1, 
                LinkedHashMap::new 
            ));

        int[] sortedKeys = sortedMap.keySet().stream().mapToInt(i -> i).toArray();
   
        return sortedKeys;
    }
}
