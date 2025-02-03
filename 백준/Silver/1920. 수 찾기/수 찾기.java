import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[] nums;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		int[] numbers = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<m; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		for (int number : numbers) {
			solve(number);
		}
	}
	
	static void solve(int n) {
		int start = 0; 
		int end = nums.length - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (nums[mid] < n) {
				start = mid + 1;
			} else {
				if (nums[mid] == n) {
					System.out.println(1);
					return;
				} else {
					end = mid - 1;
				}
			}
		}
		System.out.println(0);
	}
}
