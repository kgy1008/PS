import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            char[] s = br.readLine().toCharArray();
            int n = s.length;

            int i = n - 2;
            while (i >= 0 && s[i] >= s[i + 1]) {
                i--;
            }

            if (i < 0) {
                sb.append(new String(s)).append("\n");
                continue;
            }

            int j = n - 1;
            while (s[i] >= s[j]) {
                j--;
            }

            swap(s, i, j);

            Arrays.sort(s, i + 1, n);

            sb.append(new String(s)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
