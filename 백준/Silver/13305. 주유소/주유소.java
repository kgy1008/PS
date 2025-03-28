import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] price;
    static int[] road;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());  // 도시의 개수
        road = new int[n - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            road[i] = Integer.parseInt(st.nextToken()); // 도로 길이
        }

        price = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            price[i] = Integer.parseInt(st.nextToken()); // 기름 가격
        }

        int answer = 0;
        int cur = 0;

        while (cur < n) {
            int next = isMin(cur);
            if (next == -1) { // 가장 최소라면
                answer += (price[cur] * distance(cur, n - 1));
                break;
            }
            answer += (price[cur] * (distance(cur, next)));
            cur = next;
        }
        System.out.println(answer);
    }

    private static int distance(int start, int end) {
        int distance = 0;
        for (int i = start; i < end; i++) {
            distance += road[i];
        }
        return distance;
    }

    private static int isMin(int idx) {
        for (int i = idx; i < price.length; i++) {
            if (price[idx] > price[i]) {
                return i;
            }
        }
        return -1;
    }
}
