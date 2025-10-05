import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static int answer = 0;
    static int[] dr = {-1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static boolean dfs(int r, int c) {
        if (c == C - 1) {
            return true; 
        }

        for (int i = 0; i < 3; i++) {
            int nr = r + dr[i];
            int nc = c + 1;

            if (nr < 0 || nr >= R) {
                continue;
            }
            
            if (map[nr][nc] == 'x') {
                continue; 
            }

            map[nr][nc] = 'x'; 
            
            if (dfs(nr, nc)) {
                return true; 
            }
        }
        return false; 
    }
}
