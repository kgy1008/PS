import java.util.*;

class Solution {
    private static HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    private static List<String> answer = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        // pq 초기화
        for (String[] ticket : tickets) {
            String start = ticket[0];
            String end = ticket[1];
            
            if (!map.containsKey(start)) {
                map.put(start, new PriorityQueue<>());
            }
            map.get(start).offer(end);
        }  
        
        // 시작
        String start = "ICN";
        travel(start);
        
        String[] result = new String[answer.size()];
        for (int i=0; i<answer.size(); i++) {
            result[answer.size()-i-1] = answer.get(i);
        }
        return result;
    }
    
    private static void travel(String start) {
        PriorityQueue<String> pq = map.getOrDefault(start, new PriorityQueue<>());
        
        while (!pq.isEmpty()) {
            String next = pq.poll();
            travel(next);
        }
        answer.add(start);
    }
}