import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        Set<Integer> set = new HashSet<>(List.of(0,1,2,3,4,5,6,7,8,9));
        
        for (int number : numbers) {
            set.remove((Integer) number);
        }
        
        return set.stream().mapToInt(Integer::intValue).sum();
    }
}