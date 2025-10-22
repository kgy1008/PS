import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        char[][] grid = new char[n][n];

        int heartR = -1;
        int heartC = -1;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            grid[i] = line.toCharArray();

            if (heartR == -1) {
                int headC = line.indexOf('*');
                if (headC != -1) {
                    heartR = i + 1;
                    heartC = headC;
                }
            }
        }

        sb.append(heartR + 1).append(" ").append(heartC + 1).append("\n");

        int armR = heartR;
        int leftArm = 0;
        for (int c = 0; c < heartC; c++) {
            if (grid[armR][c] == '*') {
                leftArm++;
            }
        }

        int rightArm = 0;
        for (int c = heartC + 1; c < n; c++) {
            if (grid[armR][c] == '*') {
                rightArm++;
            }
        }
        sb.append(leftArm).append(" ").append(rightArm).append(" ");

        int waist = 0;
        int legR = -1;
        for (int r = heartR + 1; r < n; r++) {
            if (grid[r][heartC] == '*') {
                waist++;
            } else {
                legR = r;
                break;
            }
        }

        if (legR == -1) {
            legR = n;
        }

        int leftLeg = 0;
        int rightLeg = 0;
        for (int r = legR; r < n; r++) {
            if (heartC > 0 && grid[r][heartC - 1] == '*') {
                leftLeg++;
            }
            if (heartC < n - 1 && grid[r][heartC + 1] == '*') {
                rightLeg++;
            }
        }
        sb.append(waist).append(" ").append(leftLeg).append(" ").append(rightLeg);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
