import java.util.*;

class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> index = new HashMap<>();
        
        // A-Z 알파벳 사전 초기화
        int max = 1;
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('A' + i);  // 'A'부터 'Z'까지 문자
            index.put(String.valueOf(letter), max);
            max++;
        }
        
        int start = 0;
        int end = start + 1;
        ArrayList<Integer> answer = new ArrayList<>();

        while (end <= msg.length()) {
            String key = msg.substring(start, end);
            if (!index.containsKey(key)) {
                String prevKey = msg.substring(start, end - 1);
                answer.add(index.get(prevKey));
                index.put(key, max);
                max++;
                start = end - 1;
            }
                        
            if (end >= msg.length() && index.containsKey(msg.substring(start,end))) {
                answer.add(index.get(msg.substring(start,end)));
                break;
            }
            end++;
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
