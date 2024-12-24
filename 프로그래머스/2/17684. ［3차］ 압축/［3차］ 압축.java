import java.util.*;

class Solution {
    public int[] solution(String msg) {        
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<26; i++) {
            char tmp = (char) ('A' + i);
            map.put(String.valueOf(tmp), i+1);
        }
        
        int start = 0, end = 1, last = 26;
        int idx = 0;
        
        List<Integer> answer = new ArrayList<>();
        while (start < msg.length()) {
            String tmp = msg.substring(start, end);
            while (end <= msg.length() && map.containsKey(tmp)) {
                idx = map.get(tmp);
                end++;
                if (end <= msg.length()) {
                    tmp = msg.substring(start, end);
                }
            }
            map.put(tmp, ++last);
            answer.add(idx);
            start = end - 1;
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}