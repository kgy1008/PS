import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        boolean[] visited = new boolean[n];

        int cur = 0;
        visited[cur] = true;
        StringBuilder sb = new StringBuilder();
        sb.append(1).append(" ");

        while (check(visited)) {
            int move = nums[cur];

            if (move > 0) { // 오른쪽으로 이동
                while (move != 0) {
                    while (visited[(cur + 1) % n]) {
                        cur = (cur + 1) % n;
                    }
                    move--;
                    cur++;
                    if (cur == n) {
                        cur = 0;
                    }
                }
            } else { // 왼쪽으로 이동
                while (move != 0) {
                    while (visited[(cur - 1 + n) % n]) {
                        cur = (cur - 1 + n) % n;
                    }
                    move++;
                    cur--;
                    if (cur < 0) {
                        cur = n - 1;
                    }
                }
            }
            visited[cur] = true;
            sb.append(cur + 1).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean check(boolean[] visited) {
        for (boolean b : visited) {
            if (!b) { // 방문하지 않은 곳이 있음
                return true;
            }
        }
        return false;
    }
}
