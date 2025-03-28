import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());  // 도시의 개수
        int[] road = new int[n - 1];
        int[] price = new int[n];

        // 도로 길이 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }

        // 주유소 가격 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        long totalCost = 0;
        long minPrice = price[0]; // 첫 번째 도시에서 기름을 사기 시작
        for (int i = 0; i < n - 1; i++) {
            // 더 저렴한 가격을 만나면 그 가격으로 갱신
            if (price[i] < minPrice) {
                minPrice = price[i];
            }
            // 현재까지 가장 저렴한 가격으로 기름 구매
            totalCost += minPrice * road[i];
        }

        System.out.println(totalCost);
    }
}
