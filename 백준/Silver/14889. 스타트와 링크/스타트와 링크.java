import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] abilities;
    static boolean[] isSelected;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        abilities = new int[N][N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                abilities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideTeam(0, 0);

        System.out.println(minDiff);
    }

    static void divideTeam(int idx, int count) {
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        for (int i = idx; i < N; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                divideTeam(i + 1, count + 1);
                isSelected[i] = false;
            }
        }
    }

    // 두 팀 간의 능력치 차이를 계산하는 함수
    static void calculateDifference() {
        int startTeam = 0;
        int linkTeam = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isSelected[i] && isSelected[j]) {
                    startTeam += (abilities[i][j] + abilities[j][i]);
                } else if (!isSelected[i] && !isSelected[j]) {
                    linkTeam += (abilities[i][j] + abilities[j][i]);
                }
            }
        }

        int difference = Math.abs(startTeam - linkTeam);
        minDiff = Math.min(minDiff, difference);
    }
}
