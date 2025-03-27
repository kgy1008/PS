import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][2 * N - 1];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                map[i][j] = ' ';
            }
        }
        
        drawStar(0, N - 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
    
    static void drawStar(int x, int y, int n) {
        if (n == 3) {
            // 가장 작은 삼각형
            map[x][y] = '*';
            map[x + 1][y - 1] = '*';
            map[x + 1][y + 1] = '*';
            map[x + 2][y - 2] = '*';
            map[x + 2][y - 1] = '*';
            map[x + 2][y] = '*';
            map[x + 2][y + 1] = '*';
            map[x + 2][y + 2] = '*';
            return;
        }

        // 위쪽 삼각형
        drawStar(x, y, n / 2);

        // 왼쪽 아래 삼각형
        drawStar(x + n / 2, y - n / 2, n / 2);

        // 오른쪽 아래 삼각형
        drawStar(x + n / 2, y + n / 2, n / 2);
    }
}
