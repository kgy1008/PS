import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        for (int[] size : sizes) {
            Arrays.sort(size);
        }
        int weight = 0;
        int height = 0;
        for (int[] size : sizes) {
            height = Math.max(height, size[0]);
            weight = Math.max(weight, size[1]); 
        }
        
        return height * weight;
    }
}