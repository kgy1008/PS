import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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

    static int N, M, K;
    static ArrayList<Node>[] adjList;
    static PriorityQueue<Integer>[] dist;
   

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        // 각 정점별 인접리스트 init
        for(int n=1; n<=N; n++){
            adjList[n] = new ArrayList<>();
        }
        // 각 정점별 도착 cost pq
        dist = new PriorityQueue[N+1];
        for(int n=1; n<=N; n++){
            // cost 제일 큰 경로가 먼저 나옴(reverseOrder)
            dist[n] = new PriorityQueue<Integer>(K, Collections.reverseOrder());
        }
        
        // visited = new boolean[N+1];

        int from, to, cost;
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, cost));
        }

        dijkstra(1);

        for(int n=1; n<=N; n++){
            if(dist[n].size() < K){
                System.out.println(-1);
            }else{
                System.out.println(dist[n].peek());
            }
        }
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        dist[start].offer(0);

        // 시작점: 1번 노드
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            // K번째 최단경로를 찾아야하므로 아래 방문처리 하지 않음
            /*
            if(visited[current.dest]){
                continue;
            }
            // 방문처리
            visited[current.dest] = true;
            */

            for(Node next : adjList[current.dest]){
                // 해당 정점의 dist pq의 사이즈가 K보다 작을 경우
                if(dist[next.dest].size() < K){
                    dist[next.dest].offer(next.cost + current.cost);
                    pq.offer(new Node(next.dest, next.cost + current.cost));
                }
                // 해당 정점의 dist pq의 사이즈가 K보다 크거나 같을 경우
                // dist pq내에서 cost 제일 큰 것 보다 현재 cost가 작으면(갱신필요)
                else if(dist[next.dest].peek() > next.cost + current.cost){
                    // dist pq내에서 cost 제일 큰 것 삭제
                    dist[next.dest].poll();
                    // 현재 cost dist pq에 넣기
                    dist[next.dest].offer(next.cost + current.cost);
                    pq.offer(new Node(next.dest, next.cost + current.cost));
                }
            }
        }
    }
}
