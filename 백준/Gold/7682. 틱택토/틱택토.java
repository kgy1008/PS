import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            String board = br.readLine();
            if (board.equals("end")) {
                break;
            }

            HashMap<Character, Integer> map = new HashMap<>();
            for (char c : board.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            // x,o 개수가 유효하지 않을 경우
            int existCount = 9 - map.getOrDefault('.', 0);
            int oCount = map.getOrDefault('O', 0);
            if (existCount / 2 != oCount) {
                sb.append("invalid\n");
                continue;
            }

            // 승리 조건 검사
            boolean xWin = false;
            boolean oWin = false;
            char[][] grid = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    grid[i][j] = board.charAt(i * 3 + j);
                }
            }

            for (int i = 0; i < 3; i++) {
                // 가로 검사
                if (grid[i][0] != '.' && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                    if (grid[i][0] == 'X') {
                        xWin = true;
                    } else {
                        oWin = true;
                    }
                }
                // 세로 검사
                if (grid[0][i] != '.' && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                    if (grid[0][i] == 'X') {
                        xWin = true;
                    } else {
                        oWin = true;
                    }
                }
            }
            // 대각선 검사
            if (grid[0][0] != '.' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
                if (grid[0][0] == 'X') {
                    xWin = true;
                } else {
                    oWin = true;
                }
            }

            if (grid[0][2] != '.' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
                if (grid[0][2] == 'X') {
                    xWin = true;
                } else {
                    oWin = true;
                }
            }

            if (xWin && oWin) {
                sb.append("invalid\n");
            } else if (xWin) {
                if (existCount % 2 == 1) {
                    sb.append("valid\n");
                } else {
                    sb.append("invalid\n");
                }
            } else if (oWin) {
                if (existCount % 2 == 0) {
                    sb.append("valid\n");
                } else {
                    sb.append("invalid\n");
                }
            } else {
                if (existCount == 9) {
                    sb.append("valid\n");
                } else {
                    sb.append("invalid\n");
                }
            }
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
