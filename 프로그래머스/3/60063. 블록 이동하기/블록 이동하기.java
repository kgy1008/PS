import java.util.*;

class Solution {
    static int[][] board;
    public int solution(int[][] board) {
        this.board = board;
        int n = board.length;
        boolean[][][] visited = new boolean[n][n][2]; 
        Deque<Node> queue = new ArrayDeque<>();

        Node start = new Node(0, 0, 0, 1, 0);
        queue.add(start);
        visited[start.x1][start.y1][start.dir] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if ((node.x1 == n-1 && node.y1 == n-1) || (node.x2 == n-1 && node.y2 == n-1)) {
                return node.time;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nx1 = node.x1 + dx[i];
                int ny1 = node.y1 + dy[i];
                int nx2 = node.x2 + dx[i];
                int ny2 = node.y2 + dy[i];

                if (isValid(nx1, ny1, n) && isValid(nx2, ny2, n)) {
                    Node next = new Node(nx1, ny1, nx2, ny2, node.time + 1);
                    if (!visited[next.x1][next.y1][next.dir]) {
                        visited[next.x1][next.y1][next.dir] = true;
                        queue.offer(next);
                    }
                }
            }
            
            // 회전 -> 가로 일 때
            if (node.dir == 0) {
                for (int dir = 0; dir < 2; dir++) {
                    int nx1 = node.x1;
                    int ny1 = node.y1;
                    int nx2 = node.x1 + dx[dir];
                    int ny2 = node.y1;
                    if (isValid(nx2, ny2, n) && isValid(node.x2, node.y2 + dy[dir], n) && board[node.x1 + dx[dir]][node.y2] == 0) {
                        Node next = new Node(nx1, ny1, nx2, ny2, node.time + 1);
                        if (!visited[next.x1][next.y1][next.dir]) {
                            visited[next.x1][next.y1][next.dir] = true;
                            queue.offer(next);
                        }
                    }
                    
                    nx1 = node.x2;
                    ny1 = node.y2;
                    nx2 = node.x2 + dx[dir];
                    ny2 = node.y2;
                    if (isValid(nx2, ny2, n) && isValid(node.x1, node.y1 + dy[dir], n) && board[node.x2 + dx[dir]][node.y1] == 0) {
                        Node next = new Node(nx1, ny1, nx2, ny2, node.time + 1);
                        if (!visited[next.x1][next.y1][next.dir]) {
                            visited[next.x1][next.y1][next.dir] = true;
                            queue.offer(next);
                        }
                    }
                }
            }
            // 회전 -> 세로 일 때
            else { 
                for (int dir = 2; dir < 4; dir++) {
                    int nx1 = node.x1;
                    int ny1 = node.y1;
                    int nx2 = node.x1;
                    int ny2 = node.y1 + dy[dir];
                    if (isValid(nx2, ny2, n) && isValid(node.x2 + dx[dir], node.y2, n) && board[node.x2][node.y1 + dy[dir]] == 0) {
                        Node next = new Node(nx1, ny1, nx2, ny2, node.time + 1);
                        if (!visited[next.x1][next.y1][next.dir]) {
                            visited[next.x1][next.y1][next.dir] = true;
                            queue.offer(next);
                        }
                    }

                    nx1 = node.x2;
                    ny1 = node.y2;
                    nx2 = node.x2;
                    ny2 = node.y2 + dy[dir];
                    if (isValid(nx2, ny2, n) && isValid(node.x1 + dx[dir], node.y1, n) && board[node.x1][node.y2 + dy[dir]] == 0) {
                        Node next = new Node(nx1, ny1, nx2, ny2, node.time + 1);
                        if (!visited[next.x1][next.y1][next.dir]) {
                            visited[next.x1][next.y1][next.dir] = true;
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private boolean isValid(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n && board[x][y] == 0;
    }
    
    class Node {
        int x1, y1, x2, y2, time;
        int dir; 

        public Node(int x1, int y1, int x2, int y2, int time) {
            this.time = time;
            
            if (x1 == x2) { // 가로
                this.dir = 0;
                this.x1 = x1;
                this.y1 = Math.min(y1, y2);
                this.x2 = x2;
                this.y2 = Math.max(y1, y2);
            } else { // 세로
                this.dir = 1;
                this.x1 = Math.min(x1, x2);
                this.y1 = y1;
                this.x2 = Math.max(x1, x2);
                this.y2 = y2;
            }
        }
    }
}