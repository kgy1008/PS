import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int maxWeight = (int) 1e6;
		int leafPointer = 1;
		while (leafPointer < maxWeight) {
			leafPointer <<= 1;
		}
		
		tree = new int[leafPointer*2];
		for (int i=0; i<maxWeight; i++) { // 리프 노드 채우기
			tree[leafPointer + i] = 0;
		}
		
		for (int i = leafPointer -1; i>0; i--) { // 트리 전체 채우기
			tree[i] = tree[i*2] + tree[i*2+1];
		}
		
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while (n-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if (a == 2) { // 사탕 추가
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				tree[leafPointer + b - 1]  += c;

				int targetIdx = leafPointer + b - 1;
				update(targetIdx);
				
			} else {
				int b = Integer.parseInt(st.nextToken()); // 꺼낼 사탕의 순위
				int idx = 1;
				
				while (idx < leafPointer) {
					if (tree[idx*2] >= b) {
						idx *= 2;
					} else {
						b -= tree[idx*2];
						idx = idx*2 + 1;
					}
				}
				tree[idx] -= 1;
				sb.append(idx - leafPointer + 1).append("\n");
				update(idx);
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	// 트리 전체 업데이트
	static void update(int targetIdx) {
		while (targetIdx != 1) {
			targetIdx /= 2;
			tree[targetIdx] = tree[targetIdx*2] + tree[targetIdx*2+1];
		}
	}
}
