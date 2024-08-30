import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int choice = nums.length / 2;
        HashSet<Integer> kinds = new HashSet<>();
        
        for (int n : nums) {
            kinds.add(n);
        }
        
        int total = kinds.size();
        
        if (choice <=  total) {
            return choice;
        } else {
            return total;
        }
    }
}