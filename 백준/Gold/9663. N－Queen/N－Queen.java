import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean[] width;
    static boolean[] diagonal1;
    static boolean[] diagonal2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        width = new boolean[n];
        diagonal1 = new boolean[n * 2];
        diagonal2 = new boolean[n * 2];

        int answer = NQueen(0, n);
        System.out.println(answer);
    }

    private static int NQueen(int y, int n) {
        int ans = 0;
        if (y == n) {
            ans++;
        } else {
            for (int i = 0; i < n; i++) {
                if (width[i] || diagonal1[i + y] || diagonal2[i - y + n]) {
                    continue;
                }
                width[i] = diagonal1[i + y] = diagonal2[i - y + n] = true;
                ans += NQueen(y + 1, n);
                width[i] = diagonal1[i + y] = diagonal2[i - y + n] = false;
            }
        }
        return ans;
    }
}
