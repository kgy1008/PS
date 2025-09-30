import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static boolean[] visited = new boolean[51];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        bfs(s, "", 0);
    }

    static void bfs(String s, String result, int max) {
        if (s.isEmpty()) {
            if (isValid(max)) {
                System.out.println(result.trim());
                System.exit(0);
            }
        }

        for (int i = 1; i <= 2; i++) {
            if (s.length() >= i) {
                String sub = s.substring(0, i);
                int num = Integer.parseInt(sub);
                if (num >= 1 && num <= 50 && !visited[num]) {
                    visited[num] = true;
                    bfs(s.substring(i), result + num + " ", Math.max(max, num));
                    visited[num] = false;
                }
            }
        }
    }

    static boolean isValid(int max) {
        for (int i = 1; i <= max; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
