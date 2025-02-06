import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(); //최소 힙
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1,o2) -> Integer.compare(o2, o1)); // 최대 힙
		
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if (minHeap.size() == maxHeap.size()) {
				maxHeap.offer(num);
			} else {
				minHeap.offer(num);
			}
			
			if (!minHeap.isEmpty() &&  !maxHeap.isEmpty()) {
				if (maxHeap.peek() > minHeap.peek()) {
					int temp = maxHeap.poll();
					maxHeap.offer(minHeap.poll());
					minHeap.offer(temp);
				}
			}
			sb.append(maxHeap.peek()).append("\n");
		}

		String answer = sb.toString();
		bw.write(answer);
		bw.flush();
		
		br.close();
        bw.close();
	}
}
