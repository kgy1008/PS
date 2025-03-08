import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            map.put(s, map.getOrDefault(s, 0) + 1);
            max = Math.max(max, map.get(s));
        }

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        List<String> candidate = new ArrayList<>();
        for (Entry<String, Integer> entry : entrySet) {
            int value = entry.getValue();

            if (value == max) {
                candidate.add(entry.getKey());
            }
        }

        Collections.sort(candidate);
        System.out.println(candidate.get(0));
    }
}
