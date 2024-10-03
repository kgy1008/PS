import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {        
        Set<Integer> win = new HashSet<>();
        
        for (int w : win_nums) {
            win.add(w);
        }
        
        
        int zero = 0;
        int correct = 0;
        for (int i : lottos) {
            if (i == 0) zero++;
            if (win.contains(i)) correct++;
        }
        
        return new int[] {award(correct+zero), award(correct)};
    }
    
    private int award(int correct) {
        if (correct == 6) return 1;
        else if (correct == 5) return 2;
        else if (correct == 4) return 3;
        else if (correct == 3) return 4;
        else if (correct == 2) return 5;
        else return 6;
    }
}