import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            sb.append(bfs(start, target)).append("\n");
        }
        System.out.print(sb);
    }

    static String bfs(int start, int target) {
        boolean[] visited = new boolean[10_000];
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, ""));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.pollFirst();
            if (cur.value == target) {
                return cur.path;
            }

            int d = (cur.value * 2) % 10_000;
            if (!visited[d]) {
                visited[d] = true;
                queue.add(new Node(d, cur.path + "D"));
            }

            int s = (cur.value == 0) ? 9999 : cur.value - 1;
            if (!visited[s]) {
                visited[s] = true;
                queue.add(new Node(s, cur.path + "S"));
            }

            int l = (cur.value % 1000) * 10 + (cur.value / 1000);
            if (!visited[l]) {
                visited[l] = true;
                queue.add(new Node(l, cur.path + "L"));
            }

            int r = (cur.value / 10) + (cur.value % 10) * 1000;
            if (!visited[r]) {
                visited[r] = true;
                queue.add(new Node(r, cur.path + "R"));
            }
        }
        return "";
    }

    static class Node {
        int value;
        String path;

        Node(int value, String path) {
            this.value = value;
            this.path = path;
        }
    }
}
