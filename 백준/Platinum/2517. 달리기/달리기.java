import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    static Runner[] runners;
    static int leafPointer = 1;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        runners = new Runner[n];

        for (int i = 0; i < n; i++) {
            int s = Integer.parseInt(br.readLine());
            runners[i] = new Runner(i, s);
        }

        Arrays.sort(runners, (o1, o2) -> Integer.compare(o1.skill, o2.skill));  // skill을 기준으로 오름차순 정렬
        for (int i = 0; i < n; i++) {
            Runner runner = runners[i];
            runner.skill = i;  // 데이터 압축
        }

        Arrays.sort(runners, (o1, o2) -> Integer.compare(o1.idx, o2.idx));  // idx를 기준으로 오름차순 정렬

        // 인덱스 트리 초기화
        while (leafPointer < n) {
            leafPointer <<= 1;
        }
        tree = new int[leafPointer * 2];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Runner runner = runners[i];
            int skill = runner.skill;
            update(skill);
            int rank = query(skill, n) + 1;
            sb.append(rank).append("\n");
        }
        
        bw.write(sb.toString());
        bw.flush();
        
        bw.close();
        br.close();
    }

    static void update(int skill) {
        int target = skill + leafPointer;
        tree[target]++;
        target /= 2;

        while (target >= 1) {
            tree[target] = tree[target * 2] + tree[target * 2 + 1];
            target /= 2;
        }
    }

    static int query(int skill, int n) {
        int left = leafPointer + skill + 1;
        int right = leafPointer + n - 1;
        int sum = 0;

        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }
            if (right % 2 == 0) {
                sum += tree[right--];
            }

            left /= 2;
            right /= 2;
        }
        return sum;
    }

    static class Runner {
        int idx;
        int skill;

        public Runner(final int idx, final int skill) {
            this.idx = idx;
            this.skill = skill;
        }
    }
}
