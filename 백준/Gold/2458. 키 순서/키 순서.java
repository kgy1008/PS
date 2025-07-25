import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int m = Integer.parseInt(st.nextToken()); // 비교 횟수

        int[][] distance = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    distance[i][j] = 0; // 자기 자신과의 거리는 0
                } else {
                    distance[i][j] = INF; // 초기값은 무한대로 설정
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 학생 a
            int b = Integer.parseInt(st.nextToken()); // 학생 b

            distance[a][b] = 1;
        }

        // 플로이드-워셜 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (distance[i][k] < INF && distance[k][j] < INF) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            boolean fixed = true;
            for (int j = 1; j <= n; j++) {
                if (distance[i][j] == INF && distance[j][i] == INF) {
                    fixed = false; // i와 j 사이의 관계가 확정되지 않음
                }
            }
            if (fixed) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
