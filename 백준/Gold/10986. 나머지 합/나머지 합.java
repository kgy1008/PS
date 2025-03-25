import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long result = 0L;
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1);

        int prefixMod = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            prefixMod = (prefixMod + (num % m)) % m;

            result += remainderCount.getOrDefault(prefixMod, 0);

            remainderCount.put(prefixMod, remainderCount.getOrDefault(prefixMod, 0) + 1);
        }

        System.out.println(result);
    }
}
