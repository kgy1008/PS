import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_TEMPERATURE = 65536;

    static int leafPointer = 1;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());  // 전체 숫자의 개수
        int k = Integer.parseInt(st.nextToken());  // 윈도우 크기

        int rank = (k + 1) / 2;  // 중앙값 위치 계산

        // 세그먼트 트리의 리프노드 크기 계산 (온도 범위 최대값 기준)
        while (leafPointer < MAX_TEMPERATURE) {
            leafPointer <<= 1;
        }

        // 세그먼트 트리 크기 초기화 (리프 노드 수의 두 배)
        tree = new int[leafPointer * 2];

        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        long sum = 0L;
        int start = 0, end = k - 1;

        // 첫 윈도우 내의 값들을 세그먼트 트리에 추가
        for (int i = 0; i < end; i++) {
            update(num[i], 1);  // 값 추가
        }

        // 슬라이딩 윈도우 시작
        while (end < n) {
            update(num[end], 1); // 새로운 값을 세그먼트 트리에 추가
            sum += query(rank);  // 중앙값 찾기
            update(num[start], -1); // 윈도우에서 빠져나간 값 제거
            start++;
            end++;
        }

        System.out.println(sum);
    }

    // 세그먼트 트리에서 값 추가/제거 함수
    static void update(int value, int diff) {
        int idx = leafPointer + value;  // 리프 노드의 인덱스 계산
        tree[idx] += diff;  // 리프 노드에 값 추가 (또는 제거)

        // 부모 노드 갱신 (위로 올라가면서 구간 합 갱신)
        while (idx > 1) {
            idx /= 2;
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }
    }

    // 세그먼트 트리에서 중앙값 찾기
    static int query(int rank) {
        int idx = 1;
        while (idx < leafPointer) {  // 리프 노드에 도달할 때까지 탐색
            if (tree[idx * 2] >= rank) {
                idx *= 2;  // 왼쪽 자식으로 이동 (왼쪽에서 찾음)
            } else {
                rank -= tree[idx * 2];  // 왼쪽 자식에서 찾지 못했으므로 순위 감소
                idx = idx * 2 + 1;  // 오른쪽 자식으로 이동
            }
        }
        return idx - leafPointer;  // 리프 노드에 도달하면 해당 값 반환
    }
}
