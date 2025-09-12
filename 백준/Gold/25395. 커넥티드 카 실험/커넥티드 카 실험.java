import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] positions = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        int[] fuels = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            fuels[i] = Integer.parseInt(st.nextToken());
        }

        List<Car> cars = new ArrayList<>();
        int sIndex = -1; // 시작 차량의 정렬된 인덱스를 저장

        for (int i = 1; i <= n; i++) {
            Car car = new Car(i, positions[i], fuels[i]);
            cars.add(car);
            if (i == s) {
                sIndex = cars.size() - 1;
            }
        }

        cars.sort(Comparator.comparingInt(o -> o.position));

        // 정렬 후의 인덱스 업데이트
        for (int i = 0; i < n; i++) {
            cars.get(i).index = i;
            if (cars.get(i).num == s) {
                sIndex = i;
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        TreeSet<Integer> result = new TreeSet<>();

        // 시작 차량의 정렬된 인덱스를 큐에 추가
        queue.offer(sIndex);
        visited[s] = true;
        result.add(s);

        // 투 포인터
        int left = sIndex;
        int right = sIndex;

        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            Car curCar = cars.get(curIdx);

            long startRange = (long) curCar.position - curCar.fuel;
            long endRange = (long) curCar.position + curCar.fuel;

            // 왼쪽 포인터 이동
            while (left >= 0 && cars.get(left).position >= startRange) {
                Car nextCar = cars.get(left);
                if (!visited[nextCar.num]) {
                    visited[nextCar.num] = true;
                    queue.offer(left);
                    result.add(nextCar.num);
                }
                left--;
            }

            // 오른쪽 포인터 이동
            while (right < n && cars.get(right).position <= endRange) {
                Car nextCar = cars.get(right);
                if (!visited[nextCar.num]) {
                    visited[nextCar.num] = true;
                    queue.offer(right);
                    result.add(nextCar.num);
                }
                right++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int carNum : result) {
            sb.append(carNum).append(" ");
        }
        System.out.print(sb.toString().trim());
    }

    static class Car {
        int num;
        int position;
        int fuel;
        int index; // 정렬 후의 인덱스

        Car(int num, int position, int fuel) {
            this.num = num;
            this.position = position;
            this.fuel = fuel;
        }
    }
}
