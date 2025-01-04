import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int size = commands.length;
        int[] answer = new int[size];
        
        for (int i=0; i<size; i++) {
            int start = commands[i][0];
            int end = commands[i][1];
            int k = commands[i][2];

            int[] arr = Arrays.copyOfRange(array, start-1, end);
            Arrays.sort(arr);
            
            answer[i] = arr[k-1];
        }
        return answer;
    }
}