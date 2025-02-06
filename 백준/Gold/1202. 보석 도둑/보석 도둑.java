import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Jewelry[] jw = new Jewelry[n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jw[i] = new Jewelry(m,v);
		}
		
		Arrays.sort(jw, (o1,o2) -> {
			int result = Integer.compare(o1.m, o2.m);  // 무게 오름차순 정렬
			if (result == 0) {
				return Integer.compare(o2.v, o1.v); //  가격 내림차순 정렬
			}
			return result;
		});
		
		int[] bag = new int[k];
		for (int i=0; i<k; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bag); // 가방 무게 순으로 오름차순 정렬
	
		PriorityQueue<Jewelry> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o2.v, o1.v));
		long answer = 0L;
		for (int i = 0, j = 0; i < k; i++) {
            // 현재 가방의 무게보다 작거나 같은 보석을 모두 우선순위 큐에 넣음.
            while (j < n && jw[j].m <= bag[i]) {
                pq.offer(jw[j++]);
            }
 
            if (!pq.isEmpty()) {
            	answer += pq.poll().v;
            }
        }

		System.out.println(answer);
	}
	
	static class Jewelry{
		final int m;
		final int v;
		
		Jewelry(int m, int v) {
			this.m = m;
			this.v = v;
		}
	}
}
