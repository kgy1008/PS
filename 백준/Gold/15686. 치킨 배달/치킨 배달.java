import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static List<Node> chickens = new ArrayList<>();
    static int[][] map;
    static int n, m;
    static Set<Node> home = new HashSet<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chickens.add(new Node(i, j));
                } else if (map[i][j] == 1) {
                    home.add(new Node(i, j));
                }
            }
        }

        combination(0, 0, new HashSet<>());

        System.out.println(answer);
    }

    static void combination(int idx, int count, Set<Node> selected) {
        if (count == m) {
            answer = Math.min(answer, getMinChickenDist(selected));
            return;
        }

        for (int i = idx; i < chickens.size(); i++) {
            selected.add(chickens.get(i));
            combination(i + 1, count + 1, selected);
            selected.remove(chickens.get(i));
        }
    }

    static int getMinChickenDist(Set<Node> selected) {
        int totalDist = 0;

        for (Node h : home) {
            int minDist = Integer.MAX_VALUE;
            for (Node c : selected) {
                minDist = Math.min(minDist, Math.abs(h.x - c.x) + Math.abs(h.y - c.y));
            }
            totalDist += minDist;
        }

        return totalDist;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
