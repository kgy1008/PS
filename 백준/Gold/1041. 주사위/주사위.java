import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Integer.parseInt(br.readLine());

        long[] dice = new long[6];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            long sum = 0;
            long max = 0;
            for (int i = 0; i < 6; i++) {
                sum += dice[i];
                max = Math.max(max, dice[i]);
            }
            System.out.println(sum - max);
            return;
        }

        long min1 = dice[0];
        for (int i = 1; i < 6; i++) {
            min1 = Math.min(min1, dice[i]);
        }

        long min2_a = Math.min(dice[0], dice[5]) + Math.min(dice[1], dice[4]);
        long min2_b = Math.min(dice[0], dice[5]) + Math.min(dice[2], dice[3]);
        long min2_c = Math.min(dice[1], dice[4]) + Math.min(dice[2], dice[3]);

        long min2 = Math.min(min2_a, Math.min(min2_b, min2_c));

        long min3 = Math.min(dice[0], dice[5]) + Math.min(dice[1], dice[4]) + Math.min(dice[2], dice[3]);

        long result = 0;

        result += min3 * 4L;

        result += min2 * (8L * n - 12L);

        result += min1 * (5L * n * n - 16L * n + 12L);

        System.out.println(result);
    }
}
