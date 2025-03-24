import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] visit = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visit[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int answer = 1;
        for (int i = 0; i < X; i++) {
            max += visit[i];
        }

        int sum = max;
        int end = X;
        for (int start = 0; start < N - X; start++) {
            sum -= visit[start];
            sum += visit[end++];
            if (sum > max) {
                max = sum;
                answer = 1;
            } else if (sum == max) {
                answer++;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(answer);
        }
    }
}
