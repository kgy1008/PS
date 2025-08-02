class Solution {
    public int solution(String name) {
        int index = 0;
        int move = name.length()-1;
        int answer = 0;
        
        for(int i=0;i<name.length();i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            
            index = i+1; // 오른쪽으로 이동
            while(index<name.length() && name.charAt(index) == 'A') {
                index++;
            }
            move = Math.min(move, i*2+name.length()-index);
            move = Math.min(move, (name.length()-index)*2 + i);
        }
        return answer + move;
    }
}