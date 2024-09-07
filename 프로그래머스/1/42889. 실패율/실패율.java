import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        HashMap<Integer, Integer> count = new HashMap<>();
        
        for (int stage : stages) {
            count.put(stage, count.getOrDefault(stage, 0) + 1);
        }
        
        HashMap<Integer, Double> fail = new HashMap<>();
        double person = stages.length;  
        
        for (int i = 1; i <= N; i++) {
            if (person == 0) {
                fail.put(i, 0.0); 
                continue;
            }
            
            double currentStageCount = count.getOrDefault(i, 0); 
            fail.put(i, currentStageCount / person);  
            person -= currentStageCount; 
        }
        
        return fail.entrySet().stream()
            .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
            .mapToInt(Map.Entry::getKey)
            .toArray();
    }
}
