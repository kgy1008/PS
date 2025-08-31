import java.util.LinkedList;
import java.util.Queue;

class Solution {
    class State {
        int x1, y1, x2, y2, time;
        int orientation; // 0: Horizontal, 1: Vertical

        public State(int x1, int y1, int x2, int y2, int time) {
            this.time = time;
            
            // Normalize coordinates for consistent state representation
            if (x1 == x2) { // Horizontal
                this.orientation = 0;
                this.x1 = x1;
                this.y1 = Math.min(y1, y2);
                this.x2 = x2;
                this.y2 = Math.max(y1, y2);
            } else { // Vertical
                this.orientation = 1;
                this.x1 = Math.min(x1, x2);
                this.y1 = y1;
                this.x2 = Math.max(x1, x2);
                this.y2 = y2;
            }
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        boolean[][][] visited = new boolean[n][n][2]; // [x][y][orientation]
        Queue<State> q = new LinkedList<>();

        State initialState = new State(0, 0, 0, 1, 0);
        q.add(initialState);
        visited[initialState.x1][initialState.y1][initialState.orientation] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            State current = q.poll();

            if ((current.x1 == n - 1 && current.y1 == n - 1) || (current.x2 == n - 1 && current.y2 == n - 1)) {
                return current.time;
            }

            // 1. Translation
            for (int i = 0; i < 4; i++) {
                int nx1 = current.x1 + dx[i];
                int ny1 = current.y1 + dy[i];
                int nx2 = current.x2 + dx[i];
                int ny2 = current.y2 + dy[i];

                if (isValid(nx1, ny1, n, board) && isValid(nx2, ny2, n, board)) {
                    State nextState = new State(nx1, ny1, nx2, ny2, current.time + 1);
                    if (!visited[nextState.x1][nextState.y1][nextState.orientation]) {
                        visited[nextState.x1][nextState.y1][nextState.orientation] = true;
                        q.add(nextState);
                    }
                }
            }
            
            // 2. Rotation
            // Horizontal to Vertical
            if (current.orientation == 0) {
                // Pivot at (x1, y1)
                for (int dir = 0; dir < 2; dir++) {
                    int nx1 = current.x1;
                    int ny1 = current.y1;
                    int nx2 = current.x1 + dx[dir];
                    int ny2 = current.y1;
                    if (isValid(nx2, ny2, n, board) && isValid(current.x2, current.y2 + dy[dir], n, board) && board[current.x1 + dx[dir]][current.y2] == 0) {
                        State nextState = new State(nx1, ny1, nx2, ny2, current.time + 1);
                        if (!visited[nextState.x1][nextState.y1][nextState.orientation]) {
                            visited[nextState.x1][nextState.y1][nextState.orientation] = true;
                            q.add(nextState);
                        }
                    }
                    
                    // Pivot at (x2, y2)
                    nx1 = current.x2;
                    ny1 = current.y2;
                    nx2 = current.x2 + dx[dir];
                    ny2 = current.y2;
                    if (isValid(nx2, ny2, n, board) && isValid(current.x1, current.y1 + dy[dir], n, board) && board[current.x2 + dx[dir]][current.y1] == 0) {
                        State nextState = new State(nx1, ny1, nx2, ny2, current.time + 1);
                        if (!visited[nextState.x1][nextState.y1][nextState.orientation]) {
                            visited[nextState.x1][nextState.y1][nextState.orientation] = true;
                            q.add(nextState);
                        }
                    }
                }
            }
            // Vertical to Horizontal
            else { 
                // Pivot at (x1, y1)
                for (int dir = 2; dir < 4; dir++) {
                    int nx1 = current.x1;
                    int ny1 = current.y1;
                    int nx2 = current.x1;
                    int ny2 = current.y1 + dy[dir];
                    if (isValid(nx2, ny2, n, board) && isValid(current.x2 + dx[dir], current.y2, n, board) && board[current.x2][current.y1 + dy[dir]] == 0) {
                        State nextState = new State(nx1, ny1, nx2, ny2, current.time + 1);
                        if (!visited[nextState.x1][nextState.y1][nextState.orientation]) {
                            visited[nextState.x1][nextState.y1][nextState.orientation] = true;
                            q.add(nextState);
                        }
                    }

                    // Pivot at (x2, y2)
                    nx1 = current.x2;
                    ny1 = current.y2;
                    nx2 = current.x2;
                    ny2 = current.y2 + dy[dir];
                    if (isValid(nx2, ny2, n, board) && isValid(current.x1 + dx[dir], current.y1, n, board) && board[current.x1][current.y2 + dy[dir]] == 0) {
                        State nextState = new State(nx1, ny1, nx2, ny2, current.time + 1);
                        if (!visited[nextState.x1][nextState.y1][nextState.orientation]) {
                            visited[nextState.x1][nextState.y1][nextState.orientation] = true;
                            q.add(nextState);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private boolean isValid(int x, int y, int n, int[][] board) {
        return x >= 0 && x < n && y >= 0 && y < n && board[x][y] == 0;
    }
}