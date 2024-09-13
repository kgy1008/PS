import java.util.*;

class Solution {
    
     private class Node {
        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public int solution(int N, int[][] road, int K) {

        ArrayList<Node>[] nodes = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int[] edge : road) {
            nodes[edge[0]].add(new Node(edge[1], edge[2]));
            nodes[edge[1]].add(new Node(edge[0], edge[2]));
        }

        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cost, o1.cost));
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (dist[now.dest] < now.cost) {
                continue;
            }
            
            for (Node next : nodes[now.dest]) {
                if (dist[next.dest] > now.cost + next.cost) {
                    dist[next.dest] = now.cost + next.cost;
                    pq.add(new Node(next.dest, dist[next.dest]));
                }
            }            
        }

        int answer = 0;
        
        for (int d : dist) {
            if (d <= K) answer++;
        }
        
        return answer;
    }
}