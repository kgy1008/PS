import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] time;
    static int[] indegree;
    static List<Integer>[] adjList;
    static int[] answer;
    static ArrayDeque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 건물의 종류
        time = new int[N + 1];
        answer = new int[N + 1];
        indegree = new int[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()); // 짓는데 걸리는 시간
            time[i] = t;

            while (st.hasMoreTokens()) {
                int a = Integer.parseInt(st.nextToken());  // 선행되어야 하는 건물 번호
                if (a == -1) {
                    break;
                }
                indegree[i]++;
                adjList[a].add(i);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i); // 진입 차수가 0인 건물들부터 추가
            }
        }

        solve();
        for (int i = 1; i <= N; i++) {
            System.out.println(answer[i]);
        }
    }

    private static void solve() {
        while (!queue.isEmpty()) {
            int prev = queue.poll();
            answer[prev] += time[prev];

            for (int next : adjList[prev]) {
                indegree[next]--;
                answer[next] = Math.max(answer[prev], answer[next]);

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }
}
