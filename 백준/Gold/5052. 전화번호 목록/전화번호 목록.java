import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine()); // 전화번호의 개수
            String[] phoneNumbers = new String[n];
            for (int i = 0; i < n; i++) {
                phoneNumbers[i] = br.readLine();
            }
            Arrays.sort(phoneNumbers);

            boolean isConsistent = true;
            for (int i = 0; i < n - 1; i++) {
                if (phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
                    isConsistent = false;
                    break;
                }
            }

            String result = isConsistent ? "YES" : "NO";
            sb.append(result).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
