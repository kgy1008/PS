import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        List<String> list = new ArrayList<>();
        for (int number : numbers) {
            list.add(String.valueOf(number));
        }
        
        list.sort((o1,o2) -> {
            int a = Integer.parseInt(o1 + o2);
            int b = Integer.parseInt(o2 + o1);
            return Integer.compare(b,a);
        });
        
        StringBuilder sb = new StringBuilder();
        
        for (String s : list) {
            sb.append(s);
        }
        
        if (sb.charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }
}