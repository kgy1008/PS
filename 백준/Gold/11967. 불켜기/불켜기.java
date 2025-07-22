import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static int[][] room; // 방의 상태를 저장하는 배열
    static int[] dx = {0, 0, 1, -1}; // 상하좌우 이동
    static int[] dy = {1, -1, 0, 0}; // 상하좌우 이동
    static boolean flag = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 방의 크기
        int m = Integer.parseInt(st.nextToken());

        room = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int start = x * n + y; // 방의 번호
            int end = a * n + b; // 열쇠가 있는 방의 번호

            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }

            map.get(start).add(end);
        }

        room[1][1] = 1; // 불 켜져있음
        while (flag) {
            bfs(n);
        }

        int count = 0; // 켜진 불의 개수
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                count += room[i][j];
            }
        }

        System.out.println(count);
    }

    private static void bfs(int n) {
        boolean[][] visited = new boolean[n + 1][n + 1];
        Deque<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{1, 1}); // 시작점 (1, 1) 고정
        visited[1][1] = true; // 시작점 방문 처리
        flag = false;

        // 이동
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];

            // 불 켜기
            int key = curX * n + curY;
            if (map.containsKey(key)) {
                List<Integer> lightOn = map.get(key);

                for (int light : lightOn) {
                    int b = light % n == 0 ? n : light % n;
                    int a = (light - b) / n;

                    if (room[a][b] == 0) { // 방이 꺼져있다면
                        room[a][b] = 1; // 불 켜기
                        flag = true;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > n) {
                    continue;
                }

                if (room[nx][ny] == 1 && !visited[nx][ny]) { // 불이 켜져있는 방으로 이동
                    visited[nx][ny] = true; // 방문 처리
                    queue.add(new int[]{nx, ny}); // 큐에 추가
                }
            }
        }
    }
}
