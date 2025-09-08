import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] room;
    static HashMap<String, List<String>> map = new HashMap<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean isLight = true;  // 더 불을 켤 곳이 남아있는지 추적하는 용도

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        room = new int[n + 1][n + 1];
        room[1][1] = 1; // 1: 불 켜져있음,  0: 불 꺼져있음

        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            String key = x1 + " " + y1;
            String value = x2 + " " + y2;
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(value);
            map.put(key, list);
        }

        while (isLight) {
            bfs(n + 1);
        }

        int answer = count(n + 1);
        System.out.println(answer);
    }

    private static int count(int n) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt += room[i][j];
            }
        }
        return cnt;
    }

    private static void bfs(int n) {
        boolean[][] visited = new boolean[n][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 1}); // 처음에는 {1,1}만 불 켜져있음
        visited[1][1] = true;

        isLight = false;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x1 = cur[0];
            int y1 = cur[1];

            lightOnOrOff(x1, y1); // 다른 방 불 켜기

            for (int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if (nx < 1 || nx >= n || ny < 1 || ny >= n) {
                    continue;
                }

                if (room[nx][ny] == 1 && !visited[nx][ny]) { // 불이 켜져있다면
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny}); // 이동
                }
            }
        }
    }

    private static void lightOnOrOff(int x, int y) {
        String key = x + " " + y;

        if (map.containsKey(key)) { // 다른 방의 불을 켤 스위치가 있다면
            List<String> list = map.get(key);
            for (String str : list) {
                String[] split = str.split(" ");
                int x2 = Integer.parseInt(split[0]);
                int y2 = Integer.parseInt(split[1]);

                if (room[x2][y2] == 0) { // 불이 꺼져있다면
                    room[x2][y2] = 1; // 불을 켠다
                    isLight = true;
                }
            }
            map.remove(key); // 불을 모두 켜면 삭제
        }
    }
}
