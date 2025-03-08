import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        Stream<Entry<String, Integer>> entry = map.entrySet().stream().sorted((o1, o2) -> {
            int result = Integer.compare(o2.getValue(), o1.getValue()); // 값을 기준으로 내림차순 정렬
            if (result == 0) {
                String key1 = o1.getKey();
                String key2 = o2.getKey();
                return key1.compareTo(key2); // 사전 순 정렬
            }
            return result;
        });

        System.out.println(entry.findFirst().get().getKey());
    }
}
