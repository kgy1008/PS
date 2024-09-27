class Solution {
    int zero = 0;
    public int[] solution(String s) {
        int[] answer = new int[2];
        int cycle = 0;
        
        while (!s.equals("1")) {
            s = deleteZero(s);
            s = replaceBinary(s.length());
            cycle++;
        }
        
        answer[0] = cycle;
        answer[1] = zero;
        return answer;
    }
    
    private String deleteZero(String s) {
        String replaceString = s.replaceAll("0","");
        zero += s.length() - replaceString.length();
        return replaceString;
    }
    
    private String replaceBinary(int size) {
        if (size == 0) return "0";
        
        StringBuffer sb = new StringBuffer();
        
        while (size != 1) {
            int tmp = size % 2;
            sb.append(tmp);
            size /= 2;
        }
        sb.append(1);
        
        return sb.reverse().toString();
    }
}