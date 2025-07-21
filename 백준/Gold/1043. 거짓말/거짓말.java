import static java.lang.System.in;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] party;
    static List<Integer>[] willKnowTruth;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사람 수
        int m = Integer.parseInt(st.nextToken()); // 파티 수

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수
        List<Integer> knowTruth = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            knowTruth.add(Integer.parseInt(st.nextToken())); // 진실을 아는 사람 번호
        }

        party = new List[m + 1];
        for (int i = 0; i < m + 1; i++) {
            party[i] = new ArrayList<>(); // 초기화
        }

        willKnowTruth = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            willKnowTruth[i] = new ArrayList<>(); // 초기화
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()); // 파티에 참석하는 사람 수
            for (int j = 0; j < p; j++) {
                int personNum = Integer.parseInt(st.nextToken());
                party[i].add(personNum); // 파티에 참석하는 사람 번호
                willKnowTruth[personNum].add(i); // 해당 사람이 방문하는 파티 번호를 기록
            }
        }

        visited = new boolean[n + 1]; // 진실을 아는 사람 (true), 모르는 사람 (false)

        for (int person : knowTruth) {
            if (!visited[person]) {
                dfs(person);
            }
        }

        int answer = 0; // 거짓말을 할 수 있는 파티의 개수
        for (int i = 1; i <= m; i++) {
            boolean canLie = true; // 해당 파티에서 거짓말을 할 수 있는지 여부
            for (int person : party[i]) {
                if (visited[person]) { // 진실을 아는 사람이 있다면
                    canLie = false; // 거짓말을 할 수 없음
                    break;
                }
            }
            if (canLie) {
                answer++;
            }
        }
        System.out.println(answer); // 거짓말을 할 수 있는 파티의 개수 출력
    }

    static void dfs(int person) {
        visited[person] = true;

        for (int nextParty : willKnowTruth[person]) {
            for (int nextPerson : party[nextParty]) {
                if (!visited[nextPerson]) {
                    visited[nextPerson] = true; // 진실을 알게 됨
                    dfs(nextPerson); // 다음 사람도 진실을 알게 됨
                }
            }
        }
    }
}
