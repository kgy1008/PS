import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;
    static HashMap<Integer, int[]> map = new HashMap<>();
    static Deque<Direction> changeDirection = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 방향 정의
        map.put(0, new int[]{0, 1}); // 오른쪽으로 이동
        map.put(1, new int[]{1, 0}); // 아래로 이동
        map.put(2, new int[]{0, -1}); // 왼쪽으로 이동
        map.put(3, new int[]{-1, 0}); // 위로 이동

        int n = Integer.parseInt(br.readLine()); // 보드의 크기
        board = new int[n][n];

        int k = Integer.parseInt(br.readLine()); // 사과의 위치
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            board[a - 1][b - 1] = 1; // 사과 위치
        }

        int l = Integer.parseInt(br.readLine()); // 방향 변환 횟수
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            changeDirection.add(new Direction(a, direction));
        }

        int answer = solve(0, 0);
        System.out.println(answer);
    }

    static int solve(int x, int y) {
        int Dcount = 0;
        int Lcount = 4;
        int totalTime = 0;
        Set<List<Integer>> visited = new LinkedHashSet<>();
        visited.add(List.of(x, y)); // 방문처리

        while (true) {
            int[] nextD = map.get((Dcount + Lcount) % 4);
            int nx = x + nextD[0];
            int ny = y + nextD[1];
            totalTime++; // 1초 증가

            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) { // 벽과 부딪힐 경우
                return totalTime;
            }

            if (visited.contains(List.of(nx, ny))) { // 이미 몸이 위치한 곳 -> 자기 자신과 부딪힘
                return totalTime;
            }

            visited.add(List.of(nx, ny));
            if (board[nx][ny] == 0) { // 사과가 존재하지 않는다면
                Iterator<List<Integer>> iterator = visited.iterator();
                if (iterator.hasNext()) {
                    List<Integer> firstElement = iterator.next();
                    visited.remove(firstElement);  // 꼬리 이동
                }
            }
            board[nx][ny] = 0; // 사과를 먹어치운 후 이동
            x = nx;
            y = ny;

            // 방향 변경
            if (!changeDirection.isEmpty() && totalTime == changeDirection.peek().time) {
                char changed = changeDirection.poll().direction;
                if (changed == 'D') {
                    Dcount++;
                    Dcount %= 4;
                } else {
                    Lcount--;
                    if (Lcount == 0) {
                        Lcount = 4;
                    }
                }
            }
        }
    }

    static class Direction {
        int time;
        char direction;

        public Direction(final int time, final char direction) {
            this.time = time;
            this.direction = direction;
        }
    }
}
