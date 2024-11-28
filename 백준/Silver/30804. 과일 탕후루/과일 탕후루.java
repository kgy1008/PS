import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] tang = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            tang[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int answer = 0;

        HashMap<Integer, Integer> fruitCount = new HashMap<>();

        for (int end = 0; end < n; end++) {
            fruitCount.put(tang[end], fruitCount.getOrDefault(tang[end], 0) + 1);

            while (fruitCount.size() > 2) {
                fruitCount.put(tang[start], fruitCount.get(tang[start]) - 1);
                if (fruitCount.get(tang[start]) == 0) {
                    fruitCount.remove(tang[start]);
                }
                start++;
            }

            answer = Math.max(answer, end - start + 1);
        }

        System.out.println(answer);
    }
}
