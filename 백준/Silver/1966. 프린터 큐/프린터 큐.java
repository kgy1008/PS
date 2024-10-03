import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static ArrayDeque<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            queue = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                queue.offer(new int[] {j, Integer.parseInt(st.nextToken())});
            }
            int answer = solution(m);
            System.out.println(answer);
        }
    }

    private static int solution(int m) {
        int answer = 0;
        while (!queue.isEmpty()) {
            int[] num = queue.poll();
            boolean flag = true;

            for (int[] n : queue) {
                if (num[1] < n[1]) {
                    queue.offer(num);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
                if (num[0] == m) return answer;
            }
        }
        return -1;
    }
}
