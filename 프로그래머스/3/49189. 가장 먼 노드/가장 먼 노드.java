import java.util.*;

class Solution {
    static ArrayList<Integer>[] adj;
    static int[] cost;
    
    public int solution(int n, int[][] edge) {
        adj = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        cost = new int[n+1];
        Arrays.fill(cost, 987654321);
        cost[0] = cost[1] = 0;
        dij();
        
        int max = Arrays.stream(cost).max().getAsInt();
        int answer=0;
        for (int i=2; i<=n; i++) {
            if (cost[i] == max) {
                answer++;
            }
        }
        return answer;
    }
    
    static void dij() {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        queue.offer(new Node(1, 0));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            if (cost[cur.num] < cur.cost) {
                continue;
            }
            
            for (int next : adj[cur.num]) {
                if (cost[next] > cur.cost + 1) {
                    cost[next] = cur.cost+1;
                    queue.offer(new Node(next, cost[next]));
                }
            }
        }
    }
    
    static class Node {
        int num;
        int cost;
        
        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}