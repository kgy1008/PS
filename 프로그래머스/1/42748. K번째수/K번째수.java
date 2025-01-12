import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int idx = 0;
        
        for (int[] command : commands) {
            int start = command[0];
            int end = command[1];
            int target = command[2];
            
            int[] subList = Arrays.copyOfRange(array, start-1, end);
            Arrays.sort(subList);
            answer[idx++] = subList[target - 1];
        }
        
        return answer;
    }
}