import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] num = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=n; i++) {
			num[i] = num[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		int start = 0;
		int end = 1;
		
		while (true) {
			int sum = num[end] - num[start];
			if (sum < m) {
				if (end == n) {
					break;
				}
				end++;
			} else {
				if (sum == m) {
					answer++;
				}
				start++;
			}
		}
		
		System.out.println(answer);
	}
}
