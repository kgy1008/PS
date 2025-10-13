import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        br.close();

        if (N == 1 || N == 2 || N == 3) {
            System.out.println("0");
        } else if (N == 4 || N == 5) {
            System.out.println("1");
        } else {
            if (N % 4 == 0) {
                long a = N / 4;
                System.out.println(a * a * 2 - (2L * a - 1));
            } else if (N % 4 == 1) {
                long a = N / 4;
                System.out.println(a * a * 2 - (2L * a - 1) + a - 1);
            } else {
                long a = N / 4 + 1;
                long ans = 0;
                boolean flag = true;

                for (int i = 0; i < (a - 1) * 2 - 1; i++) {
                    if (flag) {
                        ans += a;
                    } else {
                        ans += a - 1;
                    }
                    flag = !flag;
                }

                if (N % 4 == 2) {
                    System.out.println(ans);
                } else {
                    System.out.println(ans + a - 1);
                }
            }
        }
    }
}
