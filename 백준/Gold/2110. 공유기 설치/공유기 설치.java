import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] home;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        home = new int[n];
        for (int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);

        int start = 1;  // 최소 거리
        int end = home[n - 1] - home[0]; // 최대 거리
        int answer = 0;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (canInstallRouter(m, mid)) {  // mid 거리가 가능하면
                answer = mid;  // 이 거리로 배치할 수 있다면, 가능한 거리 중 가장 큰 값을 갱신
                start = mid + 1;  // 더 큰 거리로 탐색
            } else {
                end = mid - 1;  // 거리가 너무 크면, 작은 거리로 탐색
            }
        }

        System.out.println(answer);
    }

    private static boolean canInstallRouter(int c, int dist) {
        int count = 1;  // 첫 번째 공유기는 첫 번째 집에 설치
        int lastPosition = home[0];  // 첫 번째 집에 공유기 설치

        for (int i = 1; i < home.length; i++) {
            // 현재 집과 마지막으로 설치한 공유기 사이의 거리가 dist 이상이면 공유기 설치
            if (home[i] - lastPosition >= dist) {
                count++;
                lastPosition = home[i];  // 현재 집에 공유기 설치

                if (count == c) {  // c개 공유기를 모두 설치했다면
                    return true;
                }
            }
        }
        return false;
    }
}
