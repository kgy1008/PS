import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        int[] sortedNum = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = sortedNum[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sortedNum);
        Map<Integer, Integer> cash = new HashMap<>();
        cash.put(sortedNum[0], 0);

        for (int i = 1; i < n; i++) {
            if (sortedNum[i] > sortedNum[i - 1]) {
                cash.put(sortedNum[i], cash.get(sortedNum[i-1]) + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            int answer = cash.get(num[i]);
            sb.append(answer).append(" ");
        }
        System.out.println(sb);
    }
}
