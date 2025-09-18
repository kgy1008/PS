class Solution {
    public String solution(String new_id) {
        // 1
        new_id = new_id.toLowerCase();
        
        // 2
        StringBuilder sb2 = new StringBuilder();
        for (char ch : new_id.toCharArray()) {
            if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '-' || ch == '_' || ch == '.') {
                sb2.append(ch);
            }
        }
        new_id = sb2.toString();
        
        // 3
        StringBuilder sb3 = new StringBuilder();
        boolean isDot = false;
        for (char ch : new_id.toCharArray()) {
            if (ch == '.') {
                if (!isDot) {
                    sb3.append(ch);
                }
                isDot = true;
            } else {
                sb3.append(ch);
                isDot = false;
            }
        }
        new_id = sb3.toString();

        // 4
        if (!new_id.isEmpty() && new_id.charAt(0) == '.') {
            new_id = new_id.substring(1);
        }
        if (!new_id.isEmpty() && new_id.charAt(new_id.length() - 1) == '.') {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        
        // 5
        if (new_id.isEmpty()) {
            new_id = "a";
        }
        
        // 6
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if (new_id.charAt(new_id.length() - 1) == '.') {
                new_id = new_id.substring(0, new_id.length() - 1);
            }
        }
        
        // 7
        while (new_id.length() <= 2) {
            new_id += new_id.charAt(new_id.length() - 1);
        }
        
        return new_id;
    }
}