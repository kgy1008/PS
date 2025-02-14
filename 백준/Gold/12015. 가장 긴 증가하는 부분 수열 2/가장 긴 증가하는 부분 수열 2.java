import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static int[] lis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        lis = new int[n];
        lis[0] = num[0];
        int lastIndex = 0;

        for (int i = 1; i < n; i++) {
            if (lis[lastIndex] < num[i]) {
                lis[++lastIndex] = num[i];
            } else {
                int idx = lowerBound(num[i], lastIndex);
                lis[idx] = num[i];
            }
        }

        bw.write(String.valueOf(lastIndex + 1));
        bw.flush();

        bw.close();
        br.close();
    }

    static int lowerBound(int target, int right) {
        int left = 0;

        while (left < right) {
            int mid = (left + right) / 2;

            if (lis[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
