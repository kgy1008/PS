import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] leak = new int[N];

        for (int i = 0; i < N; i++) {
            leak[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(leak);

        double start = leak[0] - 0.5;
        double end = start + L;
        int answer = 1;

        for (int i = 1; i < N; i++) {
            int l = leak[i];
            double temp = l + 0.5;
            if (temp <= end) {
                continue;
            }
            answer++;
            start = leak[i] - 0.5;
            end = start + L;
        }

        System.out.println(answer);
    }
}
