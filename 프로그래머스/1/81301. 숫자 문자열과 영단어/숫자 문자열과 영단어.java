import java.util.*;

class Solution {
    public int solution(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("zero",0);
        map.put("one",1);
        map.put("two",2);
        map.put("three",3);
        map.put("four",4);
        map.put("five",5);
        map.put("six",6);
        map.put("seven",7);
        map.put("eight",8);
        map.put("nine",9);
        
        int len = s.length();
        
        StringBuilder answer = new StringBuilder();
        int idx = 0;
        while(idx<len) {
            char c = s.charAt(idx);
            if (Character.isDigit(c)) {
                answer.append(c);
                idx++;
            } else {
                StringBuilder sb = new StringBuilder();
                while(idx < len && !Character.isDigit(s.charAt(idx))) {
                    sb.append(s.charAt(idx));
                    if (map.containsKey(sb.toString())) {
                        answer.append(map.get(sb.toString()));
                        sb.setLength(0); // sb 초기화
                    }
                    idx++;
                }
            }
        }
        
        String a = answer.toString();
        return Integer.parseInt(a);
    }
}