class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int maxX = (board[0]-1)/2;
        int maxY = (board[1]-1)/2;
        
        int[] answer = new int[2];
        
        for (String cmd : keyinput) {
            int nx = answer[0];
            int ny = answer[1];
            
            if (cmd.equals("up")) ny = answer[1] + 1; 
            else if (cmd.equals("down")) ny = answer[1] -1;
            else if (cmd.equals("left")) nx = answer[0] - 1;
            else nx = answer[0] + 1;
            
            if (Math.abs(nx) > maxX || Math.abs(ny) > maxY) continue;
            
            answer[0] = nx;
            answer[1] = ny;
        }
        
        return answer;
    }
}