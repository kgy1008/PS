import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        Integer[] nums = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        
        Arrays.sort(nums, (o1, o2) -> (o2 + "" + o1).compareTo(o1 + "" + o2));

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }

        return sb.charAt(0) == '0' ? "0" : sb.toString();
    }
}
