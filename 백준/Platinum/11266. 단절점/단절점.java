import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 11266 단절점 Platinum 4
public class Main {

    static int V, E;
    static ArrayList<Integer>[] adjList;

    static int searchOrder;
    static int[] order;

    static boolean[] isAp;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        order = new int[V+1];
        isAp = new boolean[V+1];

        // 0. input -> adjacent list
        adjList = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            adjList[i] = new ArrayList<>();
        }

        int n1, n2;
        for(int e=0; e<E; e++){
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            
            // 양방향 간선으로 넣어준다(무향 연결그래프 : 방향이 없는, 이어진)
            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }


        searchOrder = 1;
        // 입력으로 주어지는 그래프는 연결 그래프가 아닐수도 있다.
        // -> 방문한 적이 없는 모든 노드에 대해 dfs 수행
        for(int i=1; i<=V; i++){
            if(order[i] == 0){
                dfs(i, true);
            }
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=V; i++){
            if(isAp[i]){
                count++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(count);
        if(count > 0){
            System.out.println(sb.toString());
        }
    }

    static int dfs(int curr, boolean isRoot){
        order[curr] = searchOrder++;
        int low = order[curr];
        int child = 0;

        for(int next : adjList[curr]){
            if(order[next] == 0){
                // curr 노드의 서브트리 수 카운트
                child++;

                // curr 노드의 서브트리에서 최소 order 반환받음
                int subTreeLow = dfs(next, false);
                if(!isRoot && order[curr] <= subTreeLow){
                    isAp[curr] = true;
                }

                low = Math.min(low, subTreeLow);
            }else{
                low = Math.min(low, order[next]);
            }
        }

        // curr 정점이 root인 경우, 서브트리가 2개 이상이면 단절점이 된다.
        if(isRoot && child >= 2){
            isAp[curr] = true;
        }

        // curr를 루트로 하는 서브트리에서 갈 수 있는 최소 order를 반환한다.
        return low;

    }

}
