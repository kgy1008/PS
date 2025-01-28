import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        List<Node>[] adj = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] r : road) {
            adj[r[0]].add(new Node(r[1], r[2]));
            adj[r[1]].add(new Node(r[0], r[2]));
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, 987654321); 
        dist[1] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
        queue.offer(new Node(1,0));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            for (Node next : adj[cur.num]) {
                if (dist[next.num] > next.cost + dist[cur.num]) {
                    dist[next.num] = next.cost + dist[cur.num];
                    queue.offer(new Node(next.num, dist[next.num]));
                }
            }
        }
        
        for (int d : dist) {
            if (d <= K) {
                answer++;
            }
        }
        return answer;
    }
    
    static class Node {
        int num;
        int cost;
        
        Node (int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}