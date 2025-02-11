import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static final int LOG = 16;
    
    static int N;
    static boolean[] visited;
    static ArrayList<Integer>[] adjList;
    static int[] depth;  // 루트 노드로부터의 정점까지의 깊이 저장 배열
    static int[][] parent;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());  // 노드의 개수
        visited = new boolean[N+1];
        depth = new int[N+1];
        parent = new int[LOG+1][N+1];

        
        adjList = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            adjList[i] = new ArrayList<>();
        }

        // 트리 생성
        for(int n=0; n<N-1; n++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            
            // 양방향 간선으로 넣어준다
            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }

        // 모든 정점에 대해 자신 바로 위의 부모 노드 저장 -> parent[0][v]
        bfs(1);  // 1이 루트 번호

        // 2. 자신의 모든 조상 찾기 -> parent[k][V]
        // parent[k][V] = parent[k-1][parent[k-1][V]]
        findAncestors();

        
        int M = Integer.parseInt(br.readLine());  // 공통 조상을 알고 싶은 쌍의 개수

        StringBuilder sb = new StringBuilder();
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        
        bw.close();
        br.close();
    }

    static void bfs(int root){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        depth[root] = 0;
        visited[root] = true;

        while(!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int nextNode : adjList[currentNode]) {
                if(!visited[nextNode]){
                    visited[nextNode] = true;

                    parent[0][nextNode] = currentNode;
                    depth[nextNode] = depth[currentNode] + 1;
                    queue.add(nextNode);
                }
            }
        }
    }

    static void findAncestors(){
        for(int k=1; k<=LOG; k++){
            for(int v=1; v<=N; v++){
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }
    }

    static int lca(int a, int b){
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
                b = parent[k][b];
            }
        }

        // 2. 1.에서 depth를 맞추었는데 a와 b가 같다면 lca를 찾은 것
        if(a == b){
            return a;
        }

        // 3. a와 b를 같이 끌어올리면서 처음으로 조상이 같지 않은 지점(parent[0][a] != parent[0][b]) 까지 이동
        // a와 b의 조상이 같으면 k--
        // a와 b의 조상이 같지 않으면 a와 b를 2^k 만큼 끌어올림
        for(int k=LOG; k>=0; k--){
            if(parent[k][a] != parent[k][b]){
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        // 4. 3.이 2^0=1 에서 끝났으므로 a, b의 바로 위 조상이 lca가 된다
        return parent[0][a];
    }
}
