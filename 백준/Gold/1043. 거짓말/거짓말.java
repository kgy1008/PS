import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 파티 수

        boolean[] knowsTruth = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());// 진실을 아는 사람 수
        List<Integer> truthPeople = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            knowsTruth[num] = true;
            truthPeople.add(num);
        }

        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        List[] canAttend = new ArrayList[M + 1];
        for (int i = 1; i <= M; i++) {
            canAttend[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());
            int first = -1;
            for (int j = 0; j < partySize; j++) {
                int person = Integer.parseInt(st.nextToken());
                canAttend[i + 1].add(person);
                if (j == 0) {
                    first = person;
                } else {
                    union(parent, first, person);
                }
            }
        }

        boolean[] groupHasTruth = new boolean[N + 1];
        for (int t : truthPeople) {
            groupHasTruth[find(parent, t)] = true;
        }

        int answer = 0;
        for (int i = 1; i <= M; i++) {
            List<Integer> list = canAttend[i];
            boolean canLie = true;
            for (int person : list) {
                if (groupHasTruth[find(parent, person)]) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    static void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}
