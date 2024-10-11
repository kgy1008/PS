import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> ans = new HashSet<>();
        
        Arrays.sort(numbers);
        
        for (int i=0; i<numbers.length-1; i++) {
            for (int j=i+1; j<numbers.length; j++) {
                ans.add(numbers[i] + numbers[j]);
            }
        }
        
        int[] answer = ans.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(answer);
        return answer;
    }
}