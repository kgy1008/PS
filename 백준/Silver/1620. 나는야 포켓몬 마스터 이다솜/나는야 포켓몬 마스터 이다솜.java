import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> dogam1 = new HashMap<>();
        Map<Integer, String> dogam2 = new HashMap<>();
        int idx = 1;
        while (n --> 0) {
            String name = br.readLine();
            dogam1.put(name, idx);
            dogam2.put(idx, name);
            idx++;
        }
        while (m --> 0) {
            String input = br.readLine();
            if (dogam1.containsKey(input)) {
                System.out.println(dogam1.get(input));
            } else {
                System.out.println(dogam2.get(Integer.parseInt(input)));
            }
        }
    }
}
