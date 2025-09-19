import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] down = new int[n / 2]; // 석순
        int[] up = new int[n / 2]; // 종유석

        for (int i = 0; i < n / 2; i++) {
            down[i] = Integer.parseInt(br.readLine());
            up[i] = Integer.parseInt(br.readLine());
        }
        // 오름차순 정렬
        Arrays.sort(down);
        Arrays.sort(up);

        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int i = 1; i <= h; i++) {
            int dCnt = down.length - lowerBound(down, i);
            int uCnt = up.length - lowerBound(up, h - i + 1);

            int total = dCnt + uCnt;

            if (total < min) {
                min = total;
                count = 1;
            } else if (total == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }
    
    public static int lowerBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
}
