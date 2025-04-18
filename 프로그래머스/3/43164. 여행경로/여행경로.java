import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1])); // 오름차순 정렬

        visited = new boolean[tickets.length];
        dfs("ICN", "ICN", tickets, 0);

        return answer.get(0).split(" ");
    }

    private void dfs(String current, String path, String[][] tickets, int count) {
        if (count == tickets.length) {
            answer.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], tickets, count + 1);
                visited[i] = false; // 백트래킹
            }
        }
    }
}
