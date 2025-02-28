import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] block = new int[w];
        for (int i = 0; i < w; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = w - 1;
        int leftMax = block[left], rightMax = block[right];
        int answer = 0;

        while (left < right) {
            if (leftMax < rightMax) {  // 왼쪽이 낮다면 왼쪽을 기준으로 물을 채움
                left++;
                leftMax = Math.max(leftMax, block[left]);
                answer += Math.max(0, leftMax - block[left]);
            } else {  // 오른쪽이 낮다면 오른쪽을 기준으로 물을 채움
                right--;
                rightMax = Math.max(rightMax, block[right]);
                answer += Math.max(0, rightMax - block[right]);
            }
        }

        System.out.println(answer);
    }
}
