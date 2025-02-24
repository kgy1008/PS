import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        HashMap<Integer, Integer> winning = new HashMap<>();
        winning.put(6,1);
        winning.put(5,2);
        winning.put(4,3);
        winning.put(3,4);
        winning.put(2,5);
        winning.put(1,6);
        winning.put(0,6);
        
        
        int unknown = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                unknown++;
            }
        }
        
        int match = 0;
        for (int lotto : lottos) {
            for (int win : win_nums) {
                if (lotto == win) {
                    match++;
                }
            }
        }
        
        int max = winning.get(match + unknown);
        int min = winning.get(match);
        
        return new int[]{max, min};
    }
}