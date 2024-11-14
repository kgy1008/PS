import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] num = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            int number = Integer.parseInt(st.nextToken());
            num[i] = num[i-1] + number;
        }

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int sum = num[end] - num[start-1];
            System.out.println(sum);
        }
    }
}
