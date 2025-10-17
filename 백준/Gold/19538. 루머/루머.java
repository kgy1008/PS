import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] adjList;
    static int[] requiredCount;
    static int[] currentInfectedCount;
    static int[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 사람의 수

        adjList = new List[n + 1];
        requiredCount = new int[n + 1];
        currentInfectedCount = new int[n + 1];
        answer = new int[n + 1];
        Arrays.fill(answer, -1);

        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 사람 관계 입력
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> neighbors = new ArrayList<>();

            while (st.hasMoreTokens()) {
                int neighbor = Integer.parseInt(st.nextToken());
                if (neighbor == 0) {
                    break;
                }
                neighbors.add(neighbor);
            }

            adjList[i].addAll(neighbors);
            requiredCount[i] = (neighbors.size() + 1) / 2; // 절반 이상 감염 조건
        }

        int m = Integer.parseInt(br.readLine()); // 최초 전파자 수
        Deque<Integer> deque = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            deque.offer(num);
            answer[num] = 0;
        }

        solve(deque);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static void solve(Deque<Integer> deque) {
        while (!deque.isEmpty()) {
            int currId = deque.poll();
            int currTime = answer[currId];

            for (int neighbor : adjList[currId]) {
                if (answer[neighbor] == -1) {
                    currentInfectedCount[neighbor]++;

                    if (currentInfectedCount[neighbor] >= requiredCount[neighbor]) {
                        answer[neighbor] = currTime + 1;
                        deque.offer(neighbor);
                    }
                }
            }
        }
    }
}
