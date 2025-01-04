import java.util.*;

class Solution {
    public long solution(long n) {
        String s = n + "";
        char[] arr = s.toCharArray();
        
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (char a : arr) {
            sb.append(a);
        }
        sb.reverse();
        long answer = Long.parseLong(sb.toString());
        return answer;
    }
}