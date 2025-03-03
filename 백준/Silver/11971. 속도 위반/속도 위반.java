import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 제한 속도 구간 개수
        int m = Integer.parseInt(st.nextToken()); // 테스트할 차량 개수

        Info[] infos = new Info[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            infos[i] = new Info(dist, speed);
        }

        Info[] car = new Info[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            car[i] = new Info(dist, speed);
        }

        int answer = 0;
        int district = 0, districtDistLeft = infos[0].distance;

        for (int i = 0; i < m; i++) {
            int carDistLeft = car[i].distance;
            int carSpeed = car[i].speed;

            while (carDistLeft > 0) {
                int move = Math.min(carDistLeft, districtDistLeft);
                carDistLeft -= move;
                districtDistLeft -= move;

                // 속도 차이 확인 (최대값 갱신)
                answer = Math.max(answer, carSpeed - infos[district].speed);

                // 다음 구간으로 이동
                if (districtDistLeft == 0 && district < n - 1) {
                    district++;
                    districtDistLeft = infos[district].distance;
                }
            }
        }

        System.out.println(answer);
    }

    static class Info {
        int distance;
        int speed;

        public Info(int distance, int speed) {
            this.distance = distance;
            this.speed = speed;
        }
    }
}
