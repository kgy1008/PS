import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] x = new long[n + 1];  // 좌표가 커질 수 있으므로 long 타입으로 변경
        long[] y = new long[n + 1];  // 좌표가 커질 수 있으므로 long 타입으로 변경

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());  // long 타입으로 변환
            y[i] = Long.parseLong(st.nextToken());  // long 타입으로 변환
        }

        x[n] = x[0];
        y[n] = y[0];

        long sum1 = 0;  // x[i] * y[i + 1]의 합
        long sum2 = 0;  // y[i] * x[i + 1]의 합

        // 다각형의 면적 계산 (슈호버츠 공식)
        for (int i = 0; i < n; i++) {
            sum1 += x[i] * y[i + 1];
            sum2 += y[i] * x[i + 1];
        }

        double area = Math.abs(sum1 - sum2) / 2.0;
        System.out.printf("%.1f\n", area);
    }
}
