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

        int ans = 1;

        while (a != b) {
            if (b % 2 == 0) {
                b /= 2;
            }
            else {
                String bt = b + "";
                int idx = bt.length() - 1;
                if (idx > 0 && bt.charAt(bt.length() - 1) == '1') {
                    b =  Integer.parseInt(bt.substring(0,idx));
                }
                else {
                    System.out.println(-1);
                    return;
                }
            }
            ans ++;
        }
        System.out.println(ans);
    }
}
