import java.util.*;

class Solution {
    static List<String> answer;
    static Map<String, PriorityQueue<String>> map = new HashMap<>();
    
    public String[] solution(String[][] tickets) {
        // 초기화
        for (String[] ticket : tickets) {
            map.putIfAbsent(ticket[0], new PriorityQueue<>());
        }
        
        // 설정
        for (String[] ticket : tickets) {
            map.get(ticket[0]).offer(ticket[1]);
        }
        
        answer = new ArrayList<>();
        dfs("ICN");
        // 역순으로 탐색이 완료된 경로를 반환
        Collections.reverse(answer);
        return answer.toArray(new String[0]);
    }
    
    private static void dfs(String start) {
        PriorityQueue<String> queue = map.get(start);
        
        // 더 이상 갈 곳이 없을 때까지 탐색
        while (queue != null && !queue.isEmpty()) {
            String next = queue.poll();
            dfs(next);
        }
        // 모든 경로가 탐색되었을 때 현재 출발지를 정답에 추가
        answer.add(start);
    }
}
