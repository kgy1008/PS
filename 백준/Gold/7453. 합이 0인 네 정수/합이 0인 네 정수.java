import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] prefixSum = new int[n*n];
		int[] suffixSum = new int[n*n];
		
		int idx = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				prefixSum[idx] = A[i] + B[j];
				suffixSum[idx] = C[i] + D[j];
				idx++;
			}
		}
		Arrays.sort(prefixSum);
		Arrays.sort(suffixSum);
		
		long answer = 0L;
		int left = 0, right = n*n - 1;
		
		while (left < prefixSum.length && right >= 0) {
			int sum = prefixSum[left] + suffixSum[right];
			if (sum == 0) {
				long leftCount = 1L;
				long rightCount = 1L;
				while (left + 1 < prefixSum.length && prefixSum[left] == prefixSum[left+1]) {
					leftCount++;
					left++;
				}
				while (right - 1 >= 0 && suffixSum[right] == suffixSum[right - 1]) {
					rightCount++;
					right--;
				}
				answer += (leftCount * rightCount);
				left++;
				right--;
			} else if (sum < 0) {
				left++;
			} else {
				right--;
			}
		}
		System.out.println(answer);
	}
}
