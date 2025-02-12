import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Platinum 5
public class Main {
    static class Node implements Comparable<Node>{
        int dest;
        int cost;

        public Node(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }

        // asc
        @Override
        public int compareTo(Node o) {
            // return this.cost - o.cost;
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, M, S, D;
    static ArrayList<Node>[] adjList;
    static int[] dist;
    static boolean[] visited;

    static ArrayList<Integer>[] shortestPathList;
    // [from][to]
    static boolean[][] isShortestPath;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0){
                break;
            }

            adjList = new ArrayList[N];
            shortestPathList = new ArrayList[N];
            // 각 정점별 인접리스트 init
            for(int n=0; n<N; n++){
                adjList[n] = new ArrayList<>();
                shortestPathList[n] = new ArrayList<>();
            }
            dist = new int[N];
            visited = new boolean[N];
            isShortestPath = new boolean[N][N];

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            int U, V, P;
            for(int m=0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                U = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());

                adjList[U].add(new Node(V, P));
            }

            dijkstra(S);
            // 첫번째 다익스트라를 다 돌고 나면 최단거리에 포함되는 경로가 확정됨
            // 최단거리에 포함되는 경로를 두번째 다익스트라에서 사용하지 못하도록 마킹
            reverse(S, D);
            dijkstra(S);

            sb.append(dist[D] == Integer.MAX_VALUE ? -1 : dist[D]).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        dist[start] = 0;

        // 시작점: 0번 노드
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            // 목적지 노드가 이미 방문했던 정점이면 다시 방문하지 않는다
            // 첫번째 방문에서 최단거리가 결정되기 때문
            if(visited[current.dest]){
                continue;
            }
            // 방문처리
            visited[current.dest] = true;

            for(Node next : adjList[current.dest]){
                // (@두번쨰 다익스트라)최단거리에 포함되는 간선이 아닐 경우에만 아래 수행
                if(isShortestPath[current.dest][next.dest]){
                    continue;
                }

                // 최단거리에 포함되는 간선이 될 후보인 경우는 두 가지
                if(dist[next.dest] == next.cost + current.cost){
                    // 역방향 간선으로 추가
                    shortestPathList[next.dest].add(current.dest);
                }else if(dist[next.dest] > next.cost + current.cost){
                    dist[next.dest] = next.cost + current.cost;

                    // 갱신
                    shortestPathList[next.dest].clear();
                    shortestPathList[next.dest].add(current.dest);

                    pq.offer(new Node(next.dest, dist[next.dest]));
                }
            }
        }
    }

    // end 지점(D)에서 시작하여 start 지점(S) 까지 탐색
    static void reverse(int startPoint, int current){
        if(startPoint == current){
            return;
        }

        for (int next : shortestPathList[current]) {
            if(!isShortestPath[next][current]){
                isShortestPath[next][current] = true;
                reverse(startPoint, next);
            }
        }
    }
    
}
