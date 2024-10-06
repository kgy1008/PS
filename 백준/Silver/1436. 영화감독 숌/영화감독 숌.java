import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String ans = "666";
        int num = 0 , cnt = 0;

        while (cnt < n) {
            if (ans.contains("666")) {
                num = Integer.valueOf(ans);
                cnt++;
                if (cnt == n) {
                    System.out.println(num);
                    break;
                }
            }
            num++;
            ans = num + "";
        }
    }
}
