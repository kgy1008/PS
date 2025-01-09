import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int[] dist = new int[N+1];
        boolean[] visited = new boolean[N+1];
        
        List<Node>[] adj = new ArrayList[N+1];
        for (int i=1; i<N+1; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = 987654321;
        }
        
        dist[1] = 0;

        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            adj[a].add(new Node(b,c));
            adj[b].add(new Node(a,c));
        }
        
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1.cost));
        queue.add(new Node(1, 0));
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (visited[current.num]) {
                continue;
            }
            visited[current.num] = true;
            
            for (Node next : adj[current.num]) {
                if (dist[next.num] > current.cost + next.cost) {
                    dist[next.num] = current.cost + next.cost;
                    queue.offer(new Node(next.num, dist[next.num]));
                }
            }
        }
        
        int answer = 0;
        for (int i=1; i<N+1; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
    
    private static class Node {
        int num;
        int cost;
        
        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}