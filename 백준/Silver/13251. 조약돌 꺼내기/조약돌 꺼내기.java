import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int[] colors = new int[m];
        int total = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            colors[i] = Integer.parseInt(st.nextToken());
            total += colors[i];
        }

        int k = Integer.parseInt(br.readLine());

        double result = 0.0;

        for (int c : colors) {
            if (c < k) {
                continue;
            }

            double tmp = 1.0;
            for (int i = 0; i < k; i++) {
                tmp *= (double) (c - i) / (double) (total - i);
            }
            result += tmp;
        }

        System.out.println(result);
    }
}
