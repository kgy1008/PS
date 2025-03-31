import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int sharkX, sharkY, sharkSize = 2, eatSize = 0;
    static int totalTime = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            Position fish = bfs();
            if (fish == null) { // 먹을 수 있는 물고기가 없으면 종료
                break;
            }

            sharkX = fish.x;
            sharkY = fish.y;
            totalTime += fish.distance;
            map[sharkX][sharkY] = 0;
            eatSize++;

            // 자신의 크기와 같은 수의 물고기를 먹으면 크기 증가
            if (eatSize == sharkSize) {
                sharkSize++;
                eatSize = 0;
            }
        }

        System.out.println(totalTime);
    }

    static Position bfs() {
        Deque<Position> queue = new ArrayDeque<>();
        visited = new boolean[N][N];
        queue.add(new Position(sharkX, sharkY, 0));
        visited[sharkX][sharkY] = true;

        PriorityQueue<Position> fishes = new PriorityQueue<>(
                Comparator.comparingInt((Position p) -> p.distance) // 거리 순
                        .thenComparingInt(p -> p.x)              // 가장 위쪽
                        .thenComparingInt(p -> p.y));            // 가장 왼쪽

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    visited[nx][ny] = true;

                    // 상어가 이동할 수 있는 경우 (자신의 크기보다 작거나 같은 칸)
                    if (map[nx][ny] <= sharkSize) {
                        queue.add(new Position(nx, ny, pos.distance + 1));

                        // 먹을 수 있는 물고기라면
                        if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                            fishes.add(new Position(nx, ny, pos.distance + 1));
                        }
                    }
                }
            }
        }

        return fishes.isEmpty() ? null : fishes.poll(); // 가장 가까운 물고기 반환
    }

    static class Position {
        int x;
        int y;
        int distance;

        public Position(final int x, final int y, final int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
