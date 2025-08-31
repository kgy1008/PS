class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        // 1개 단위(i=1)부터 문자열 길이의 절반(s.length()/2)까지 압축 단위를 늘려가며 시도
        // 문자열 길이의 절반을 초과하는 압축 단위는 의미가 없습니다.
        for (int i = 1; i <= s.length() / 2; i++) {
            answer = Math.min(answer, getCompressedLength(s, i));
        }
        
        return answer;
    }
    
    private int getCompressedLength(String s, int unit) {
        StringBuilder compressed = new StringBuilder();
        String prev = s.substring(0, unit);
        int count = 1;
        
        for (int i = unit; i < s.length(); i += unit) {
            // 현재 단위만큼의 문자열을 잘라냅니다.
            String current;
            if (i + unit > s.length()) {
                current = s.substring(i);
            } else {
                current = s.substring(i, i + unit);
            }

            if (current.equals(prev)) {
                count++;
            } else {
                // 이전 문자열과 다른 경우, 압축 결과를 StringBuilder에 추가
                if (count > 1) {
                    compressed.append(count);
                }
                compressed.append(prev);
                
                // 현재 문자열을 이전 문자열로 설정하고 카운트 초기화
                prev = current;
                count = 1;
            }
        }
        
        // 반복문 종료 후, 마지막으로 남은 문자열 처리
        if (count > 1) {
            compressed.append(count);
        }
        compressed.append(prev);
        
        return compressed.length();
    }
}