import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_CHANNEL = 1_000_000;
    static final int MIN_CHANNEL = 0;

    static int n;
    static boolean[] broken;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        broken = new boolean[10];
        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int button = Integer.parseInt(st.nextToken());
                broken[button] = true;
            }
        }

        minDiff = Math.abs(n - 100);

        solve();

        System.out.println(minDiff);
    }

    private static void solve() {
        for (int channel = MIN_CHANNEL; channel <= MAX_CHANNEL; channel++) {
            String strChannel = String.valueOf(channel);
            boolean canPress = true;

            for (int i = 0; i < strChannel.length(); i++) {
                int digit = strChannel.charAt(i) - '0';
                if (broken[digit]) {
                    canPress = false; 
                    break;
                }
            }

            if (canPress) {
                int diff = strChannel.length() + Math.abs(n - channel);
                minDiff = Math.min(minDiff, diff);
            }
        }
    }
}
