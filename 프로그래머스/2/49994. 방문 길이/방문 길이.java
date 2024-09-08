import java.util.*;

class Solution {
    public int solution(String dirs) {
        int x = 0; 
        int y = 0;
        
        HashSet<String> check = new HashSet<>();
        int answer = 0;
        
        for (Character d : dirs.toCharArray()) {
            int nx = x; 
            int ny = y;
            
            if (d == 'U') {
                ny = y+1;
            }
            else if (d == 'D') {
                ny = y-1;
            }
            
            else if (d == 'R') {
                nx = x+1;
            }
            else {
                nx = x-1;
            }
            
            if (nx >= -5 && nx <= 5 && ny >= -5 && ny <= 5) {
                StringBuffer sb1 = new StringBuffer();
                String reverse = sb1.append(nx).append(ny).append(x).append(y).toString();

                StringBuffer sb2 = new StringBuffer();
                String load = sb2.append(x).append(y).append(nx).append(ny).toString();

                if (!check.contains(load)) {
                    answer++;   
                    check.add(load);
                    check.add(reverse);
                }

                x = nx;
                y = ny;
            }
            
            
        }
        
        return answer;
    }
}