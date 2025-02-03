import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static List<Integer> sumA = new ArrayList<>();
	static List<Integer> sumB = new ArrayList<>();

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
				sumA.add(sum);
			}
		}
		
		for (int i=0; i<m; i++) {
			int sum = 0;
			for (int j=i; j<m; j++) {
				sum += B[j];
				sumB.add(sum);
			}
		}
		
		sumB.sort(null);
		
		long answer = 0;
		for (int num : sumA) {
			int target = t - num;
			int left = lowerBound(target);
			int right = upperBound(target);
			answer += (right - left);
			
		}
		System.out.println(answer);
	}
	
	static int lowerBound(int target){
        int low = 0;
        int high = sumB.size();
        int mid;

        while(low < high){
            mid = (low + high)/2;
            if(sumB.get(mid) >= target){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return high;
    }

    static int upperBound(int target){
        int low = 0;
        int high = sumB.size();
        int mid;

        while(low < high){
            mid = (low + high)/2;
            if(sumB.get(mid) > target){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return high;
    }
}
