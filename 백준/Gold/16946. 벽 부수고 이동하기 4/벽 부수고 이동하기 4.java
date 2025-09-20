import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N, M;
    static int[][] grid;
    static int[][] groupId;
    static int groupCount = 1;
    static int[] groupSize;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        groupId = new int[N][M];
        groupSize = new int[N * M + 1];

        // 1. 빈 공간 그룹 사전 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 0 && groupId[i][j] == 0) {
                    bfs(i, j);
                    groupCount++;
                }
            }
        }

        // 2. 벽에 대한 최종 값 계산
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) { // 벽
                    int count = getCount(i, j);
                    sb.append(count % 10);
                } else { // 빈칸
                    sb.append(0);
                }
            }
            sb.append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getCount(int i, int j) {
        int count = 1;
        HashSet<Integer> set = new HashSet<>();

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if (grid[nx][ny] == 0) {
                    int id = groupId[nx][ny];
                    if (!set.contains(id)) {
                        set.add(id);
                        count += groupSize[id];
                    }
                }
            }
        }
        return count;
    }

    private static void bfs(int startX, int startY) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startX, startY});
        groupId[startX][startY] = groupCount;
        int size = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && grid[nx][ny] == 0) {
                    if (groupId[nx][ny] == 0) {
                        groupId[nx][ny] = groupCount;
                        queue.add(new int[]{nx, ny});
                        size++;
                    }
                }
            }
        }
        groupSize[groupCount] = size; // 헤딩 그룹의 크기
    }
}
