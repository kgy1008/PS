import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] arr = new int[N+2];
        int person = stages.length;
        
        for (int s : stages) {
            arr[s]++;
        }
        
        
        Map<Integer, Double> detail = new HashMap<>();
        for (int stage=1; stage<=N; stage++) {
            if (person == 0) {
                detail.put(stage, 0.0);
                continue;
            }
            int arrived = arr[stage];
            double failure = (double) arrived / person;
            detail.put(stage, failure);
            person -= arrived;
        }
        
        return detail.entrySet().stream()
            .sorted((o1,o2) -> Double.compare(o2.getValue(), o1.getValue()))
            .mapToInt(i-> i.getKey())
            .toArray();
    }
}