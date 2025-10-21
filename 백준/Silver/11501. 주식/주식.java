import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] prices = new int[n];
            for (int j = 0; j < n; j++) {
                prices[j] = Integer.parseInt(st.nextToken());
            }

            long profit = 0L;
            int maxPrice = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (prices[j] > maxPrice) {
                    maxPrice = prices[j];
                } else {
                    profit += (maxPrice - prices[j]);
                }
            }

            sb.append(profit).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
