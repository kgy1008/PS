import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken()); // 테스트 케이스 수

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 팀 수
            List<Integer>[] adj = new List[n + 1];
            int[] indegree = new int[n + 1];
            init(adj);

            st = new StringTokenizer(br.readLine()); // 팀 순위
            Set<Integer> set = new HashSet<>();
            for (int order = 1; order <= n; order++) {
                int team = Integer.parseInt(st.nextToken());
                set.add(team);
                rank(order, n, adj, indegree, set, team);
            }

            st = new StringTokenizer(br.readLine()); // 변경된 순위 쌍의 수
            int m = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int teamA = Integer.parseInt(st.nextToken());
                int teamB = Integer.parseInt(st.nextToken());

                changeRank(teamA, teamB, adj, indegree);
            }

            solve(adj, indegree, n);
        }
    }

    private static void solve(List<Integer>[] adj, int[] indegree, int n) {
        List<Integer> result = new ArrayList<>();
        boolean certain = true; // 확실한 순위인지 여부
        boolean cycle = false; // 사이클이 존재하는지 여부

        for (int i = 0; i < n; i++) {
            int zeroIndegreeCount = 0; // 진입차수가 0인 노드의 개수
            int node = 0; // 진입차수가 0인 노드

            for (int j = 1; j <= n; j++) {
                if (indegree[j] == 0) {
                    zeroIndegreeCount++;
                    node = j;
                }
            }

            if (zeroIndegreeCount == 0) { // 사이클이 존재
                cycle = true;
                break;
            }

            if (zeroIndegreeCount > 1) { // 확실한 순위가 아님
                certain = false;
                break;
            }

            result.add(node);
            indegree[node] = -1; // 해당 노드를 처리했음을 표시

            for (int neighbor : adj[node]) {
                indegree[neighbor]--;
            }
        }

        if (cycle) {
            System.out.println("IMPOSSIBLE");
        } else if (!certain) {
            System.out.println("?");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int team : result) {
                sb.append(team).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    private static void changeRank(int teamA, int teamB, List<Integer>[] adj, int[] indegree) {
        if (adj[teamA].contains(teamB)) {
            adj[teamA].remove(Integer.valueOf(teamB));
            adj[teamB].add(teamA);
            indegree[teamA]++;
            indegree[teamB]--;
        } else {
            adj[teamB].remove(Integer.valueOf(teamA));
            adj[teamA].add(teamB);
            indegree[teamB]++;
            indegree[teamA]--;
        }
    }

    private static void rank(int order, int n, List<Integer>[] adj, int[] indegree, Set<Integer> set, int team) {
        for (int i = 1; i <= n; i++) {
            indegree[team] = order - 1;
            if (set.contains(i)) {
                continue;
            }
            adj[team].add(i);
        }
    }

    private static void init(List<Integer>[] adj) {
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
    }
}
