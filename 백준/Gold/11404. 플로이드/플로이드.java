import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_VALUE = 100_000_00;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] city = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i==j) {
                    city[i][j] = 0;
                    continue;
                }
                city[i][j] = MAX_VALUE;
            }
        }

        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            city[a][b] = Math.min(city[a][b], c);
        }

        for (int k=1; k<n+1; k++) {
            for (int i=1; i<n+1; i++) {
                for (int j=1; j<n+1; j++) {
                    city[i][j] = Math.min(city[i][j], city[i][k] + city[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<n+1; i++) {
            for (int j=1; j<n+1; j++) {
                if (city[i][j] >= MAX_VALUE) {
                    city[i][j] = 0;
                }
                sb.append(city[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
