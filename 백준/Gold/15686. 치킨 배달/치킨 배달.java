import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Node> chickens = new ArrayList<>();
    static List<Node> homes = new ArrayList<>();
    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 크기 N * N
        int m = Integer.parseInt(st.nextToken()); // 치킨 집의 개수

        int[][] city = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 2) {
                    chickens.add(new Node(i, j));
                } else if (city[i][j] == 1) {
                    homes.add(new Node(i, j));
                }
            }
        }

        solve(m, 0, new ArrayList<>());
        System.out.println(minDistance);
    }

    static void solve(int m, int idx, List<Node> combi) {
        if (m == 0) {
            calculateDistance(new ArrayList<>(combi));
            return;
        }

        for (int i = idx; i < chickens.size(); i++) {
            combi.add(chickens.get(i));
            solve(m - 1, i + 1, combi);
            combi.remove(combi.size() - 1);
        }
    }

    static void calculateDistance(List<Node> chickens) {
        int total = 0;
        for (int i = 0; i < homes.size(); i++) {
            Node cur = homes.get(i);
            int distance = Integer.MAX_VALUE;

            for (Node chicken : chickens) {
                distance = Math.min(distance, Math.abs(cur.x - chicken.x) + Math.abs(cur.y - chicken.y));
            }
            total += distance;
        }
        minDistance = Math.min(minDistance, total);
    }

    static class Node {
        int x;
        int y;

        public Node(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
