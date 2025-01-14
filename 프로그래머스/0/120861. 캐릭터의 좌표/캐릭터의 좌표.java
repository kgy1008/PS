import java.util.*;

class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        HashMap<String, int[]> command = new HashMap<>();
        command.put("up", new int[]{0,1});
        command.put("down", new int[]{0,-1});
        command.put("left", new int[]{-1,0});
        command.put("right", new int[]{1,0});
        
        int maxRow = board[0] / 2; // 가로
        int maxCol = board[1] / 2;  // 세로
        
        int x = 0, y = 0;
        for (String key : keyinput) {
            int[] cmd = command.get(key);
            if (x + cmd[0] < maxRow * -1 || x + cmd[0] > maxRow || y + cmd[1] < maxCol * -1 || y + cmd[1] > maxCol) {
                continue;
            }
            x += cmd[0];
            y += cmd[1];
        }
        
        return new int[]{x, y};
    }
}