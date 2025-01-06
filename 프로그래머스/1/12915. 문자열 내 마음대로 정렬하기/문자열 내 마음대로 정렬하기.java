import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (o1,o2) -> {
            int result = Character.compare(o1.charAt(n), o2.charAt(n));
            if (result == 0) {
                return o1.compareTo(o2);
            }
            return result;
            });
        
        return strings;
    }
}