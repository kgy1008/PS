import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static Map<Integer, Integer> sumA = new HashMap<>();
	static Map<Integer, Integer> sumB = new HashMap<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<n; i++) {
			int sum = 0;
			for (int j=i; j<n; j++) {
				sum += A[j];
				sumA.put(sum, sumA.getOrDefault(sum, 0) + 1);
			}
		}
		
		for (int i=0; i<m; i++) {
			int sum = 0;
			for (int j=i; j<m; j++) {
				sum += B[j];
				sumB.put(sum, sumB.getOrDefault(sum, 0) + 1);
			}
		}
		
		long answer = 0;
		for (int num : sumA.keySet()) {
			int v1 = sumA.get(num);
			int target = t - num;
			if (sumB.containsKey(target)) {
				int v2 = sumB.get(target);
				answer += ((long) v1*v2);
			}
		}
		System.out.println(answer);
	}
}
