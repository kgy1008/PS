import java.util.*;

class Solution {
    static TreeMap<Integer, Integer> map = new TreeMap<>();
    
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        for (String operation : operations) {
            String[] op = operation.split(" ");
            char c = op[0].charAt(0);
            int num = Integer.parseInt(op[1]);
            
            if (c == 'I') { // 삽입
                map.put(num, map.getOrDefault(num,0) + 1);
                
            } else { // 삭제
                if (map.size() == 0) {
                    continue;
                }
                if (num == 1) { // 최댓값 삭제
                    int max = map.lastKey();
                    decrease(max);
                    
                } else { // 최솟값 삭제
                    int min = map.firstKey();
                    decrease(min);
                }
            }
        }
        
        if (map.size() != 0) {
            answer[1] = map.firstKey();
            answer[0] = map.lastKey();
        }
        
        return answer;
    }
    
    private static void decrease(int target) {
        if (map.get(target) == 1) {
            map.remove(target);
        } else {
            map.put(target, map.get(target) - 1);
        }
    }
}