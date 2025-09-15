import java.util.*;

class Solution {
    static int[][][] cost;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int n = board.length;
        cost = new int[n][n][2]; // 0 : 수평 1: 수직

        Deque<Road> queue = new ArrayDeque<>();
        queue.offer(new Road(0, 0, -1));
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                cost[i][j][0] = Integer.MAX_VALUE;
                cost[i][j][1] = Integer.MAX_VALUE;
            }
        }
        cost[0][0][0] = cost[0][0][1] = 0;

        while (!queue.isEmpty()) {
            Road cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) {
                    continue;
                }

                if (cur.isVertical == -1) { // 시작 지점에서 출발한 거라면
                    if (i % 2 == 0) { // 위 아래로 이동 (수직)
                        if (cost[nx][ny][1] > cost[cur.x][cur.y][1] + 100) {
                            cost[nx][ny][1] = cost[cur.x][cur.y][1] + 100;
                            queue.offer(new Road(nx, ny, 1));
                        }
                    } else { // 좌우로 이동 (수평)
                        if (cost[nx][ny][0] > cost[cur.x][cur.y][0] + 100) {
                            cost[nx][ny][0] = cost[cur.x][cur.y][0] + 100;
                            queue.offer(new Road(nx, ny, 0));
                        }
                    }
                } else { // 직전의 위치가 시작 지점이 아니라면
                    if (i % 2 == 0) { // 위 아래로 이동 (수직)
                        if (cur.isVertical == 1) { // 방향 전환 X 
                            if (cost[nx][ny][1] > cost[cur.x][cur.y][1] + 100) {
                                cost[nx][ny][1] = cost[cur.x][cur.y][1] + 100;
                                queue.offer(new Road(nx, ny, 1));
                            }
                        } else { // 방향 전환 (수평 -> 수직)
                            if (cost[nx][ny][1] > cost[cur.x][cur.y][0] + 600) {
                                cost[nx][ny][1] = cost[cur.x][cur.y][0] + 600;
                                queue.offer(new Road(nx, ny, 1));
                            }
                        }
                    } else { // 좌우 이동 (수평)
                        if (cur.isVertical == 0) { // 방향 전환X (수평 -> 수평)
                            if (cost[nx][ny][0] > cost[cur.x][cur.y][0] + 100) {
                                cost[nx][ny][0] = cost[cur.x][cur.y][0] + 100;
                                queue.offer(new Road(nx, ny, 0));
                            }
                        } else { // 방향 전환 (수직 -> 수평)
                            if (cost[nx][ny][0] > cost[cur.x][cur.y][1] + 600) {
                                cost[nx][ny][0] = cost[cur.x][cur.y][1] + 600;
                                queue.offer(new Road(nx, ny, 0));
                            }
                        }
                    }
                }
            }
        }
        return Math.min(cost[n-1][n-1][0], cost[n-1][n-1][1]);
    }

    static class Road {
        int x;
        int y;
        int isVertical;

        Road(int x, int y, int isVertical) {
            this.x = x;
            this.y = y;
            this.isVertical = isVertical;
        }
    }
}