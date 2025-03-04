import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            int len = s.length() - 1;

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                map.put(c, (int) (map.getOrDefault(c, 0) + Math.pow(10, len--)));
            }
        }

        List<Integer> list = new ArrayList<>(map.values());
        list.sort(Collections.reverseOrder());

        int num = 9;
        int answer = 0;

        for (int value : list) {
            answer += value * num;
            num--;
        }

        System.out.println(answer);
    }
}
