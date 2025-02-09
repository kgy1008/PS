import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] prevMax = new int[3];
        int[] prevMin = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            int num = Integer.parseInt(st.nextToken());
            prevMax[i] = prevMin[i] = num;
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int tempMin0 = Math.min(prevMin[0], prevMin[1]) + a;
            int tempMin1 = Math.min(prevMin[2], Math.min(prevMin[0], prevMin[1])) + b;
            int tempMin2 = Math.min(prevMin[2], prevMin[1]) + c;

            int tempMax0 = Math.max(prevMax[0], prevMax[1]) + a;
            int tempMax1 = Math.max(prevMax[2], Math.max(prevMax[0], prevMax[1])) + b;
            int tempMax2 = Math.max(prevMax[2], prevMax[1]) + c;

            prevMin[0] = tempMin0;
            prevMin[1] = tempMin1;
            prevMin[2] = tempMin2;

            prevMax[0] = tempMax0;
            prevMax[1] = tempMax1;
            prevMax[2] = tempMax2;
        }

        int maxAns = Math.max(prevMax[0], Math.max(prevMax[1], prevMax[2]));
        int minAns = Math.min(prevMin[0], Math.min(prevMin[1], prevMin[2]));

        System.out.println(maxAns + " " + minAns);
    }

}
