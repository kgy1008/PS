import java.util.*;

class Solution {

    static int[] answer;
    static int flag;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public int[] solution(String[][] places) {
        answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];
            flag = 1;

            solve(place);

            answer[i] = flag;
        }

        return answer;
    }

    private static void solve(String[] place) {
        for (int i = 0; i < 5; i++) {
            String p = place[i];
            for (int j = 0; j < 5; j++) {
                if (p.charAt(j) == 'P') {
                    bfs(place, i, j);
                    if (flag == 0) {
                        return; 
                    }
                }
            }
        }
    }

    private static void bfs(String[] place, int x, int y) {
        boolean[][] visited = new boolean[5][5];
        Deque<Seat> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.offer(new Seat(x, y, 0));

        while (!queue.isEmpty()) {
            Seat cur = queue.poll();

            if (place[cur.x].charAt(cur.y) == 'P') {
                if (cur.dist > 0 && cur.dist <= 2) {
                    flag = 0;
                    return; 
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                    continue;
                }
                if (visited[nx][ny] || place[nx].charAt(ny) == 'X') {
                    continue;
                }

                visited[nx][ny] = true; 
                if (cur.dist + 1 <= 2) {
                    queue.offer(new Seat(nx, ny, cur.dist + 1));
                }
            }
        }
    }

    static class Seat {
        int x;
        int y;
        int dist;

        Seat(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
