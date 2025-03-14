import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] stations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 현재 휴게소의 개수
        int M = Integer.parseInt(st.nextToken());  // 더 짓고자 하는 휴게소의 개수
        int L = Integer.parseInt(st.nextToken());  // 고속도로의 길이

        stations = new int[N + 2];
        stations[0] = 0; // 고속도로 시작 지점
        stations[N + 1] = L; // 고속도로 끝 지점

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            stations[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(stations);

        int start = 1;  // 휴게소 간 최소 간격은 1
        int end = L;    // 최대 간격은 고속도로의 길이
        int answer = L;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (check(mid, M)) {
                answer = mid;
                end = mid - 1; // 더 작은 간격이 가능한지 탐색
            } else {
                start = mid + 1; // 간격이 너무 작으면 더 크게 탐색
            }
        }

        System.out.println(answer);
    }

    private static boolean check(int target, int m) {
        int buildCount = 0;

        for (int i = 1; i < stations.length; i++) {
            int distance = stations[i] - stations[i - 1];
            buildCount += (distance - 1) / target; // 간격을 target으로 나누어 필요한 휴게소 수 계산
        }

        return buildCount <= m; // M개 이하로 휴게소를 지을 수 있으면 true
    }
}
