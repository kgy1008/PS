import java.util.*;

class Solution {
    public int solution(String dirs) {
        int x=0, y=0;
        int nx = x, ny = y;
        HashSet<String> set = new HashSet<>();
        StringBuffer sb;
        
        for (char command : dirs.toCharArray()) {
            if (command == 'U') {
                ny = y+1; 
            }
            else if (command == 'D') {
                ny = y-1;
            }
            else if (command == 'R') {
                nx = x+1;
            }
            else {
                nx = x-1;
            }
            
            if (nx<-5 || nx>5 || ny<-5 || ny>5) {
                nx = x;
                ny = y;
                continue;
            }
            
            sb = new StringBuffer();
            String start = sb.append(x).append(y).append(nx).append(ny).toString();
            set.add(start);
            sb = new StringBuffer();
            String reverse = sb.append(nx).append(ny).append(x).append(y).toString();
            set.add(reverse);
            
            x = nx; 
            y = ny;
        }
        return set.size()/2;
    }
}