import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int n = topping.length;
        
        Set<Integer> leftSet = new HashSet<>();
        Set<Integer> rightSet = new HashSet<>();
        
        int[] leftCount = new int[n];
        int[] rightCount = new int[n];
        
        for (int i = 0; i < n; i++) {
            leftSet.add(topping[i]);
            leftCount[i] = leftSet.size();
        }
        
        for (int i = n - 1; i >= 0; i--) {
            rightSet.add(topping[i]);
            rightCount[i] = rightSet.size();
        }
        
        for (int i = 0; i < n - 1; i++) {
            if (leftCount[i] == rightCount[i + 1]) {
                answer++;
            }
        }
        
        return answer;
    }
}
