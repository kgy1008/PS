import java.util.*;

class Solution {
    public int solution(int[] nums) {
        
        HashSet<Integer> kinds = new HashSet<>();
        
        for (int n : nums) {
            kinds.add(n);
        }
        
        int choice = nums.length / 2;
        
        return Math.min(choice, kinds.size());
    }
}