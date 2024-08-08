import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0, x = 0, y = 0;
        int nx = 0, ny = 0;
        Set<String> visited = new HashSet<>();
        
        for (char dir : dirs.toCharArray()) {
            nx = x;
            ny = y;
            
            if (dir == 'U') ny++;
            if (dir == 'D') ny--;
            if (dir == 'R') nx++;
            if (dir == 'L') nx--;
            
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;
            
            String path = new StringBuffer().append(nx).append(ny).toString();
            String previous = new StringBuffer() .append(x).append(y).toString();
            
            if (!visited.contains(path+previous) && !visited.contains(previous+path)) {
                visited.add(previous+path);
                visited.add(path+previous);
                answer++;
            }
            
            x = nx;
            y = ny;
        }
        
        return answer;
    }
}
