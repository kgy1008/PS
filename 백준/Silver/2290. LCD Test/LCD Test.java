import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] seg = {{true, true, true, false, true, true, true},   // 0
            {false, false, true, false, false, true, false},   // 1
            {true, false, true, true, true, false, true},   // 2
            {true, false, true, true, false, true, true},   // 3
            {false, true, true, true, false, true, false},   // 4
            {true, true, false, true, false, true, true},   // 5
            {true, true, false, true, true, true, true},   // 6
            {true, false, true, false, false, true, false},   // 7
            {true, true, true, true, true, true, true},   // 8
            {true, true, true, true, false, true, true}    // 9
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        String n = st.nextToken();

        int rows = 2 * s + 3;
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < rows; r++) {
            for (int idx = 0; idx < n.length(); idx++) {
                int num = n.charAt(idx) - '0';

                if (r == 0) { // 상단 가로
                    sb.append(' ');
                    for (int k = 0; k < s; k++) {
                        if (seg[num][0]) {
                            sb.append('-');
                        } else {
                            sb.append(' ');
                        }
                    }
                    sb.append(' ');
                } else if (r <= s) { // 좌상/우상 세로
                    if (seg[num][1]) {
                        sb.append('|');
                    } else {
                        sb.append(' ');
                    }
                    sb.append(" ".repeat(s));
                    if (seg[num][2]) {
                        sb.append('|');
                    } else {
                        sb.append(' ');
                    }
                } else if (r == s + 1) { // 가운데 가로
                    sb.append(' ');
                    for (int k = 0; k < s; k++) {
                        if (seg[num][3]) {
                            sb.append('-');
                        } else {
                            sb.append(' ');
                        }
                    }
                    sb.append(' ');
                } else if (r > s + 1 && r < 2 * s + 2) { // 좌하/우하 세로
                    if (seg[num][4]) {
                        sb.append('|');
                    } else {
                        sb.append(' ');
                    }
                    sb.append(" ".repeat(Math.max(0, s)));
                    if (seg[num][5]) {
                        sb.append('|');
                    } else {
                        sb.append(' ');
                    }
                } else { // 하단 가로
                    sb.append(' ');
                    for (int k = 0; k < s; k++) {
                        if (seg[num][6]) {
                            sb.append('-');
                        } else {
                            sb.append(' ');
                        }
                    }
                    sb.append(' ');
                }

                if (idx != n.length() - 1) {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}
