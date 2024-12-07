import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if (command == 'I') {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    if (!map.isEmpty()) {
                        if (num == -1) {  // 최소값 제거
                            int min = map.firstKey();
                            map.put(min, map.get(min) - 1);
                            if (map.get(min) <= 0) {
                                map.remove(min);
                            }
                        } else {  // 최대값 제거
                            int max = map.lastKey();
                            map.put(max, map.get(max) - 1);
                            if (map.get(max) <= 0) {
                                map.remove(max);
                            }
                        }
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }
        System.out.print(sb);
    }
}
