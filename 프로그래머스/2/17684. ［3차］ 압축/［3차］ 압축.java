import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        
        for (int i=0; i<26; i++) {
            char alpha = (char) ('A' + i);
            map.put(String.valueOf(alpha), i+1);
        }
        int lastIdx = 26;
        int idx = 0, end = 1;
        
        List<Integer> answer = new ArrayList<>();
        while (idx < msg.length()) {
            while (end <= msg.length() && map.containsKey(msg.substring(idx,end))) {
                end++;
            }
            if (end <= msg.length()) {
                map.put(msg.substring(idx,end), ++lastIdx);
            }
            answer.add(map.get(msg.substring(idx,end-1)));
            idx = end - 1;
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}