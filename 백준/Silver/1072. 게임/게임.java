import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int curZ = (int) ((y*100L) / x);
		
		if (curZ >= 99) {
			System.out.println(-1);
			return;
		}
		
		int low = 0;
		int high = (int) 1e9;
		int answer = -1;
		
		while (low <= high) {
			int mid = (low + high) / 2;
			int z = (int) (((y+mid) * 100L) / (x+mid));
			
			if (z <= curZ) {
				low = mid + 1;
			} else {
				answer = mid;
				high = mid - 1;
			}
		}
		System.out.println(answer);
	}

}
