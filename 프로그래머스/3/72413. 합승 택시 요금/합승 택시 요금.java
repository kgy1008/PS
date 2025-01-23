import java.util.*;

class Solution {
    static ArrayList<Node>[] adj;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[] costA = new int[n+1];
        int[] costB = new int[n+1];
        int[] cost = new int[n+1];
        Arrays.fill(costA, 87654321);
        Arrays.fill(costB, 87654321);
        Arrays.fill(cost, 87654321);
        costA[a] = 0;
        costB[b] = 0;
        cost[s] = 0;
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] fare : fares) {
            adj[fare[0]].add(new Node(fare[1], fare[2]));
            adj[fare[1]].add(new Node(fare[0], fare[2]));
        }
        
        cost = di(s, cost);
        costA = di(a, costA);
        costB = di(b, costB);
        
        int min = Integer.MAX_VALUE;
        for (int i=1; i<=n; i++) {
            min = Math.min(min, cost[i] + costA[i] + costB[i]);
        }
        return min;
    }
    
    private static int[] di(int k, int[] cost) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Node(k, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cost[cur.dest] < cur.cost) {
                continue;
            }

            for (Node node : adj[cur.dest]) {
                if (cost[node.dest] > node.cost + cur.cost) {
                    cost[node.dest] = node.cost + cur.cost;
                    pq.add(new Node(node.dest, cost[node.dest]));
                }
            }
        }
        return cost;
    }
    
    private static class Node {
        int dest;
        int cost;

        public Node(final int dest, final int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}