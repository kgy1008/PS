import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] clear = new int[N+2];
        int player = stages.length;
        
        for (int s : stages) {
            clear[s]++;
        }
        
        HashMap<Integer, Double> fail = new HashMap<>();
        double notClear = 0.0;
        
        for (int i=1; i<N+1; i++) {
            if (player == 0) {
                fail.put(i,0.0);
                continue;
            }
            notClear = clear[i];
            fail.put(i, notClear/player);
            player -= notClear;
        }
        
        return fail.entrySet().stream().sorted((o1,o2) -> Double.compare(o2.getValue(), o1.getValue())).mapToInt(HashMap.Entry::getKey).toArray();
    }
}