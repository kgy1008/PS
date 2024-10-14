import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> num = new ArrayList<>();
        
        for (int n : arr) {
            if (n % divisor == 0) num.add(n);
        }
        
        if (num.size() == 0) return new int[]{-1};
        int[] answer = num.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(answer);
        return answer;
    }
}