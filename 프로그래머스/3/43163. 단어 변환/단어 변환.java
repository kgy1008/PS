import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        int len = begin.length();
        
        Deque<Word> queue = new ArrayDeque<>();
        queue.offer(new Word(-1, begin, 0));
        
        while (!queue.isEmpty()) {
            Word current = queue.poll();
            
            if (current.word.equals(target)) {
                return current.phase;
            }
            
            for (int j=0; j<words.length; j++) {
                String word = words[j];
                int diffCnt = 0;
                
                for (int i=0; i<len; i++) {
                    if (word.charAt(i) != current.word.charAt(i)) {
                        diffCnt++;
                    }
                }
                
                if (diffCnt == 1 && !visited[j]) {
                    visited[j] = true;
                    queue.offer(new Word(j, word, current.phase+1));
                }
            }
        }
        
        return 0;
    }
    
    static class Word {
        int idx;
        String word;
        int phase;
        
        Word(int idx, String word, int phase) {
            this.idx = idx;
            this.word = word;
            this.phase = phase;
        }
    }
}