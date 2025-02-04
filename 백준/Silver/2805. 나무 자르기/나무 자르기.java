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
		
		int[] tree = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tree);
		int right = tree[n-1];
		int left = 0;
		int ans = 0;
		
		while (left <= right) {
			int mid = (right + left) / 2;
			long get = 0L;
			for (int t : tree) {
				if (t > mid) {
					get += (t-mid);
				}
			}
			
			if (get < m) {
				right = mid - 1;
			} else {
				if (get == m) {
					ans = mid;
					break;
				}
				ans = mid;
				left = mid + 1;
			}
		}
		
		System.out.print(ans);

	}
}
