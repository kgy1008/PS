import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(arr[0]);
        
        for (int i=1; i<arr.length; i++) {
            if (stack.peek() != arr[i]) stack.push(arr[i]);
        }
        
        int n = stack.size();
        int[] answer = new int[n];
        for (int i=0; i<n; i++) {
            answer[n-1-i] = stack.pop();
        }
        return answer;
    }
}