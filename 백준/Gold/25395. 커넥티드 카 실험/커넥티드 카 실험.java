import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] positions = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        int[] fuels = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            fuels[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        // 시작점 처리
        queue.offer(s);
        visited[s] = true;
        
        int leftPointer = s - 1;
        int rightPointer = s + 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            int minPos = positions[cur] - fuels[cur];
            int maxPos = positions[cur] + fuels[cur];
            
            while (leftPointer >= 1 && positions[leftPointer] >= minPos) {
                if (!visited[leftPointer]) {
                    visited[leftPointer] = true;
                    queue.offer(leftPointer);
                }
                leftPointer--;
            }

            while (rightPointer <= n && positions[rightPointer] <= maxPos) {
                if (!visited[rightPointer]) {
                    visited[rightPointer] = true;
                    queue.offer(rightPointer);
                }
                rightPointer++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }
}