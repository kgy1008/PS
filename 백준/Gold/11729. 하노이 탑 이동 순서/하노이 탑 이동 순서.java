import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int moveCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        hanoi(k, 1, 3, 2);

        System.out.println(moveCount);
        bw.write(sb.toString());
        bw.flush();
    }

    private static void hanoi(int n, int start, int end, int aux) {
        if (n == 1) { // 원판이 1개일 경우 바로 이동
            sb.append(start).append(" ").append(end).append("\n");
            moveCount++;
            return;
        }
        // n-1개의 원판을 start에서 aux로 이동
        hanoi(n - 1, start, aux, end);
        // 가장 큰 원판을 start에서 end로 이동
        sb.append(start).append(" ").append(end).append("\n");
        moveCount++;
        // n-1개의 원판을 aux에서 end로 이동
        hanoi(n - 1, aux, end, start);
    }
}
