import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int answ = backTracking(a,b,1);
        System.out.println(answ);
    }

    private static int backTracking(int a, int b, int cnt) {
        if (b == a) return cnt;
        if (b < a) return -1;

        if (b % 2 == 0) {
            return backTracking(a , b/2, cnt + 1);
        }
        else {
            String bt = b + "";
            int idx = bt.length() - 1;
            if (bt.charAt(bt.length() - 1) == '1') {
                return backTracking(a , Integer.parseInt(bt.substring(0,idx)), cnt + 1);
            }
        }
        return -1;
    }
}
