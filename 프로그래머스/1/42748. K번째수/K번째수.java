import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int idx = 0; idx < commands.length; idx++) {
            int start = commands[idx][0] - 1; 
            int end = commands[idx][1];
            int k = commands[idx][2] - 1; 
            
            int[] subArray = Arrays.copyOfRange(array, start, end);
            Arrays.sort(subArray); 
            
            answer[idx] = subArray[k]; 
        }
        
        return answer;
    }
}
