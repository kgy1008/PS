import java.util.*;
import java.util.regex.*;

class Solution {
    private static final Pattern pattern = Pattern.compile("([^\\d]+)(\\d+)(.*)");
    
    public String[] solution(String[] files) {
        Arrays.sort(files, (o1,o2) -> {
            String[] arr1 = parse(o1);
            String[] arr2 = parse(o2);
            int result = arr1[0].toUpperCase().compareTo(arr2[0].toUpperCase());
            if (result == 0) {
                int a = Integer.parseInt(arr1[1]);
                int b = Integer.parseInt(arr2[1]);
                return Integer.compare(a,b);
            }
            return result;
        });
        return files; 
    }
    
    public static String[] parse(String filename) {
        Matcher matcher = pattern.matcher(filename);
        if (matcher.matches()) {
            String head = matcher.group(1);
            String number = matcher.group(2);
            String tail = matcher.group(3);
            return new String[]{head, number, tail};
        }

        return new String[]{filename, "", ""};
    }
}