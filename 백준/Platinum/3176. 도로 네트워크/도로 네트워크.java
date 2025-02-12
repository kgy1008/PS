import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Platinum 4
public class Main {
    static class Road{
        int to;
        int dist;

        public Road(int to, int dist){
            this.to = to;
            this.dist = dist;
        }
    }

    static final int LOG = 17;
    
    static int N, A, B, C, K, D, E;
    static boolean[] visited;
    static ArrayList<Road>[] adjList;
    static int[] depth;
    static int[][] parent;

    static int[][] minDist;
    static int[][] maxDist;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        depth = new int[N+1];
        parent = new int[LOG+1][N+1];
        minDist = new int[LOG+1][N+1];
        maxDist = new int[LOG+1][N+1];

        // 0. input -> adjacent list
        adjList = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int n=0; n<N-1; n++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            // 양방향 간선으로 넣어준다(방향이 없는, 이어진)
            adjList[A].add(new Road(B, C));
            adjList[B].add(new Road(A, C));
        }

        // 1. bfs -> update parent[0][V] for all V
        bfs(1);

        // 2. findAncestors -> update parent[k][V]
        // parent[k][V] = parent[k-1][parent[k-1][V]]
        findAncestors();

        // 3. find lca
        K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int[] result;
        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            result = lca(D, E);
            sb.append(result[0]);
            sb.append(" ");
            sb.append(result[1]);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    static void bfs(int root){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        // root: 1번 노드로 정함
        queue.add(root);
        depth[root] = 0;
        visited[root] = true;

        while(!queue.isEmpty()) {
            int currentNode = queue.poll();
            int nextNode;

            for (Road road : adjList[currentNode]) {
                nextNode = road.to;

                if(!visited[nextNode]){
                    visited[nextNode] = true;

                    parent[0][nextNode] = currentNode;
                    depth[nextNode] = depth[currentNode] + 1;

                    // min/maxDist[k][V]: V노드의 2^k번째 조상까지의 min/max 구간 길이
                    // [currentNode(=parent[0][nextNode])]---(road.dist)--->[nextNode]
                    minDist[0][nextNode] = road.dist;
                    maxDist[0][nextNode] = road.dist;

                    queue.add(nextNode);
                }
            }
        }
    }

    static void findAncestors(){
        for(int k=1; k<=LOG; k++){
            for(int v=1; v<=N; v++){
                parent[k][v] = parent[k-1][parent[k-1][v]];

                // minDist[1][v] = min(minDist[0][v], minDist[0][parent[0][v]])
                // min{(v)-(v의 2^0번째 부모) 간의 거리, (v의 2^0번째 부모)-(v의 2^0번째 부모의 2^0번째 부모) 간의 거리}
                minDist[k][v] = Math.min(minDist[k-1][v], minDist[k-1][parent[k-1][v]]);
                maxDist[k][v] = Math.max(maxDist[k-1][v], maxDist[k-1][parent[k-1][v]]);
            }
        }
    }

    /**
     * a, b 정점 사이에 존재하는 인접한 두 정점 사이의 구간 중 min, max 인 구간의 길이를 구한다
     * @param a
     * @param b
     * @return return[0] = min, return[1] = max
     */
    static int[] lca(int a, int b){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // 0. 항상 b가 a보다 depth가 큰 노드(root로부터 먼)가 되도록 swap
        if(depth[a] > depth[b]){
            int temp = b;
            b = a;
            a = temp;
        }

        // 1. a, b의 depth 맞추기(0.에 의해 항상 b를 끌어올리게 된다)
        for(int k=LOG; k>=0; k--){
            // (1 << k) : 2^k
            // IF [(depth 차이) >= 2^16 ... 2^0] -> b 끌어올림
            // for loop의 마지막 수행 시 (depth 차이 = 1) > b = parent[0][b] 로 1 depth 끌어올려지므로 a, b의 depth가 동일해진다
            if(depth[b]-depth[a] >= (1 << k)){
                // b를 a와 같은 depth로 끌어올리면서 min, max값 갱신
                min = Math.min(min, minDist[k][b]);
                max = Math.max(max, maxDist[k][b]);

                b = parent[k][b];
            }
        }

        // 2. 1.에서 depth를 맞추었는데 a와 b가 같다면 lca를 찾은 것
        if(a == b){
            return new int[]{ min, max };
        }

        // 3. a와 b를 같이 끌어올리면서 처음으로 조상이 같지 않은 지점(parent[0][a] != parent[0][b]) 까지 이동
        // a와 b의 조상이 같으면 k--
        // a와 b의 조상이 같지 않으면 a와 b를 2^k 만큼 끌어올림
        for(int k=LOG; k>=0; k--){
            if(parent[k][a] != parent[k][b]){
                min = Math.min(min, Math.min(minDist[k][a], minDist[k][b]));
                max = Math.max(max, Math.max(maxDist[k][a], maxDist[k][b]));

                a = parent[k][a];
                b = parent[k][b];
            }
        }

        // 4. 3.이 2^0=1 에서 끝났으므로 a, b의 바로 위 조상이 lca가 된다
        // return parent[0][a];
        min = Math.min(min, Math.min(minDist[0][a], minDist[0][b]));
        max = Math.max(max, Math.max(maxDist[0][a], maxDist[0][b]));
        return new int[]{ min, max };
    }
}
