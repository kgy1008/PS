import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(br.readLine());
            map.put(num[i], map.getOrDefault(num[i], 0) + 1);
            sum += num[i];
        }

        Arrays.sort(num);
        int max = num[n-1];
        int min = num[0];
        int mid = num[n/2];
        int diff = max - min;
        int frequent = findMax(map.keySet());

        long average = Math.round(sum/n);

        StringBuffer sb = new StringBuffer();
        sb.append(average).append("\n").append(mid).append("\n").append(frequent).append("\n").append(diff);

        System.out.println(sb);
    }

    private static int findMax(Set<Integer> num) {
        int max = 0;
        int ans = 0;
        List<Integer> tmp = new ArrayList<>();
        for (int i : num) {
            if (max < map.get(i)) {
                ans = i;
                max = map.get(i);
                tmp.clear();
                tmp.add(ans);
            }
            else if (max == map.get(i)) {
                tmp.add(i);
            }
        }

        Collections.sort(tmp);
        if (tmp.size() >= 2) return tmp.get(1);
        else return ans;
    }
}
