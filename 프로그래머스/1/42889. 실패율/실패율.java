import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] player = new int[N + 2];
        
        for (int i = 0; i < stages.length; i++) {
            if (stages[i] <= N) {
                player[stages[i]]++;
            }
        }
        
        Map<Integer, Double> answer = new HashMap<>();
        double totalPlayers = stages.length;
        
        for (int i = 1; i <= N; i++) {
            if (totalPlayers == 0) {
                answer.put(i,0.0);
            } else{
                answer.put(i, player[i] / totalPlayers);
                totalPlayers -= player[i];   
            }
        }
        
        return answer.entrySet().stream()
            .sorted((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()))
            .mapToInt(Map.Entry::getKey)
            .toArray();
    }
}
