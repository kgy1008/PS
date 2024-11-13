import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, String> passwords = new HashMap<>();
        while (N > 0) {
            String ps = br.readLine();
            String[] info = ps.split(" ");
            passwords.put(info[0], info[1]);
            N --;
        }
        while (M > 0) {
            String uri = br.readLine();
            String k = passwords.get(uri);
            System.out.println(k);
            M --;
        }
    }
}
