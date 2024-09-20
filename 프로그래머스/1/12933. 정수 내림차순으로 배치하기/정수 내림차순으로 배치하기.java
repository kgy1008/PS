import java.util.Arrays;

class Solution {
    public long solution(long n) {
        String num = n+"";
        char[] arr = num.toCharArray(); 
        Arrays.sort(arr);
        
        StringBuilder sb = new StringBuilder(new String(arr)).reverse();
        return Long.parseLong(sb.toString());
    }
}
