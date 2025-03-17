import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = A[i];
        }
        br.close();

        Arrays.sort(B);

        StringBuilder sb = new StringBuilder();
        for (int a : A) {
            for (int i = 0; i < N; i++) {
                if (a == B[i]) {
                    sb.append(i).append(" ");
                    B[i] = -1;
                    break;
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
