import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		solve(nums, s);
		int result = answer == Integer.MAX_VALUE ? 0 : answer;
		System.out.println(result);
	}
	
	static void solve(int[] nums, int target) {
		int low = 0, end = 0, sum = 0;
		
		while (end < nums.length) {
			sum += nums[end++];
			while (sum >= target) {
				answer = Math.min(answer, end - low);
				sum -= nums[low++];
			}
		}
		
	}
}
