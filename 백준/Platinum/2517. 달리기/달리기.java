import java.io.*;
import java.util.*;

public class Main {

	static List<Runner> runners = new ArrayList<>();
	static int leafPointer = 1;
	static int[] tree;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());  // 전체 선수의 수
		
		for (int i=0; i<n; i++) {
			int skill = Integer.parseInt(br.readLine());
			runners.add(new Runner(i, skill));
		}
		br.close();
		
		runners.sort((o1,o2) -> Integer.compare(o1.skill, o2.skill));  // skill을 기준으로 오름차순
		
	    for (int i=0; i<n; i++) {
	    	Runner runner = runners.get(i);
	    	runner.skill = i+1;  // 데이터 압축 (skill의 값이 중요한 것이 아니라 순서가 중요하기 때문)
	    }
	    
	    runners.sort((o1,o2) -> Integer.compare(o1.idx, o2.idx));  // idx 기준으로 재 정렬
	    
	    while(leafPointer < n) {
	    	leafPointer <<= 1;
	    }
	    
	    tree = new int[leafPointer * 2]; // 인덱스 트리 초기화
	    
	    StringBuilder sb = new StringBuilder();
	    for (int i=0; i<n; i++) {
	    	Runner runner = runners.get(i);
	    	int skill = runner.skill;
	    	int rank = query(skill, n) + 1;
	    	sb.append(rank).append("\n");
	    }
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
	}
	
	static int query(int start, int end) {
		int left = leafPointer + start -1;
		int right = leafPointer + end - 1;
		int sum = 0;
		
		while (left <= right) {
			if (left % 2 == 1) {
				sum += tree[left++];
			}
			if (right % 2 == 0) {
				sum += tree[right--];
			}
			
			left /= 2;
			right /= 2;
		}
		
		update(leafPointer + start -1);  // 트리 업데이트
		return sum;
	}
	
	static void update(int target) {
		tree[target]++; // 인원 한명 추가
		
		target /= 2;
		
		while (target >= 1) {
			tree[target] = tree[target*2] + tree[target*2 + 1];
			target /= 2;
		}
	}
	
	static class Runner{
		int idx;
		int skill;
		
		Runner(int idx, int skill) {
			this.idx = idx;
			this.skill = skill;
		}
	}
}