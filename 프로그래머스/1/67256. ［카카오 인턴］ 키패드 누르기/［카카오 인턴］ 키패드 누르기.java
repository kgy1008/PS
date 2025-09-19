import java.util.*;

class Solution {
    static int rp = -1; // '*'
    static int lp = -2; // '#'
    static Map<Integer, int[]> map = new HashMap<>();
    
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        init();
        
        
        for (int number : numbers) {
            if (number == 3 || number == 6 || number == 9) { // 오른손 사용
                sb.append('R');
                rp = number;
            } else if (number == 1 || number == 4 || number == 7) { // 왼손 사용
                sb.append('L');
                lp = number;
            } else {
                int[] np = map.get(number);
                int[] l = map.get(lp);
                int[] r = map.get(rp);
                
                int ld = Math.abs(l[0] - np[0]) + Math.abs(l[1] - np[1]);
                int rd = Math.abs(r[1] - np[1]) + Math.abs(r[0] - np[0]);
                
                if (ld > rd) { // 오른손 이동
                    sb.append('R');
                    rp = number;
                } else if (rd > ld) { // 왼손 이동
                    sb.append('L');
                    lp = number;
                } else { // 같을 경우
                    if (hand.equals("left")) {
                        sb.append('L');
                        lp = number;
                    } else {
                        sb.append('R');
                        rp = number;
                    }
                }   
            }
        }
        
        return sb.toString();
    }
    
    private static void init() {
        for (int i=1; i<=9; i++) {
            int y = i%3 - 1;
            if (y == -1) {
                y = 2;
            }
            map.put(i, new int[]{(i-1)/3, y});
        }
        
        map.put(0, new int[]{3, 1});
        map.put(-1, new int[]{3, 0});
        map.put(-2, new int[]{3, 2});
    }
}