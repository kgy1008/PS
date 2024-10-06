import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] person = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            person[i][0] = weight;
            person[i][1] = height;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < n; i++) {
            int rank = 0;
            for (int j = 0; j < n; j++) {
                if (person[i][0] < person[j][0] && person[i][1] < person[j][1]) {
                    rank++;
                }
            }
            sb.append(rank+1).append(" ");
        }

        System.out.println(sb.toString());
    }
}
