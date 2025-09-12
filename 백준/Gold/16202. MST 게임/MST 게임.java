import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Line> lines = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수
        int k = Integer.parseInt(st.nextToken()); // 턴의 수

        for (int w = 1; w <= m; w++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Line line = new Line(start, end, w);
            lines.add(line);
        }
        lines.sort(Comparator.comparingInt(o -> o.cost)); // 가중치를 기준으로 오름차순 정렬

        int[] answer = new int[k];
        for (int turn = 0; turn < k; turn++) {
            if (turn > 0) {
                if (answer[turn - 1] == 0) { // 이전 턴에서 MST를 만들 수 없다면
                    break;
                }
            }
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            int sum = 0;
            int connect = 0;

            // 크루스칼 알고리즘
            for (Line line : lines) {
                if (find(line.start) != find(line.end)) {
                    sum += line.cost;
                    union(line.start, line.end);
                    connect++;
                }
            }

            if (connect == n - 1) {
                answer[turn] = sum;
            }

            lines.remove(0); // 가중치가 가장 적은 간선 제거
        }

        for (int i = 0; i < k; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Line {
        int start;
        int end;
        int cost;

        Line(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
