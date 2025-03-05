import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());  // 왼쪽 위치
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // 오른쪽 위치

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());

            if (maxHeap.size() <= minHeap.size()) {
                maxHeap.add(number);
            } else {
                minHeap.add(number);
            }

            if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                if (maxHeap.peek() > minHeap.peek()) {  // swap
                    int temp1 = maxHeap.poll();
                    int temp2 = minHeap.poll();
                    maxHeap.add(temp2);
                    minHeap.add(temp1);
                }
            }
            System.out.println(maxHeap.peek());
        }
    }
}
