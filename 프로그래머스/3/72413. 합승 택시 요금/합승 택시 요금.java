import java.util.*;

class Solution {
    static boolean[] visited;
    static List<Node>[] adj;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        init(n);
        
        for (int[] fare : fares) {
            int dest1 = fare[0];
            int dest2 = fare[1];
            int fee = fare[2];
            
            adj[dest1].add(new Node(dest2, fee));
            adj[dest2].add(new Node(dest1, fee));
        }
        
        // 다익스트라
        int[] initDist = new int[n+1]; // 시작점에서의 거리를 저장하는 배열
        dijkstra(s, initDist);
        answer = initDist[a] + initDist[b];
        
        int[] distA = new int[n+1];
        dijkstra(a, distA);
        int[] distB = new int[n+1];
        dijkstra(b, distB);
        
        for (int i=1; i<=n; i++) {
            answer = Math.min(answer, distA[i] + distB[i] + initDist[i]);
        }
        
        return answer;
    }
    
    private static void dijkstra(int start, int[] dist){
        Arrays.fill(visited, false);
        Arrays.fill(dist, 20000001);
        dist[start] = 0; // 시작 지점 거리 초기화
        
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.fee));
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (visited[cur.dest]) {
                continue;
            }
            
            for (Node next : adj[cur.dest]) {
                if (dist[next.dest] > cur.fee + next.fee) {
                    dist[next.dest] = cur.fee + next.fee;
                    pq.offer(new Node(next.dest, dist[next.dest]));
                }
            }
        }
    }
    
    private static void init(int n) {
        adj = new List[n+1];
        visited = new boolean[n+1];
        
        for (int i=1; i<=n; i++) {
            adj[i] = new ArrayList<>();
        }
    }
    
    static class Node {
        int dest;
        int fee;
        
        Node(int dest, int fee) {
            this.dest = dest;
            this.fee = fee;
        }
    }
}