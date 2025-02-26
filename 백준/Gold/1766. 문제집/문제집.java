import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final StringBuilder sb = new StringBuilder();
    static int[] indegree;
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 문제 수
        int m = Integer.parseInt(st.nextToken()); // 먼저 풀면 좋은 문제에 대한 정보 수

        indegree = new int[n + 1];
        adjList = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int last = Integer.parseInt(st.nextToken());
            indegree[last]++;
            adjList[prev].add(last);
        }

        topologicalSort();

        bw.write(sb.toString());
        bw.flush();
    }

    static void topologicalSort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compare); // 난이도가 낮은 것부터 우선 순위를 갖도록

        // 시작점:차수가 0인 노드 queue에 넣기
        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int c = pq.poll();
            sb.append(c).append(" ");

            for (int next : adjList[c]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
    }
}
