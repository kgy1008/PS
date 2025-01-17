class Solution {
    public int solution(int[][] sizes) {
        int row = 0;  
        int col = 0; 
        for (int[] size : sizes) {
            row = Math.max(size[0], row);
            col = Math.max(col, size[1]);
        }  
        
        int max = Math.max(row, col);
        int find = 0;
        
        for (int[] size : sizes) {
            int weight = size[0];
            int height = size[1];
            int tmp = Math.min(weight, height);
            find = Math.max(tmp, find);
        }  
        return max*find;
    }
}