import java.util.*;

class Solution {
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        int[][] digit1 = convert(arr1, n);
        int[][] digit2 = convert(arr2, n);
        
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (digit1[i][j] == 1 || digit2[i][j] == 1) {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
             }
             answer[i] = sb.toString();
        }

        return answer;
    }
    
    private static int[][] convert(int[] arr, int n) {
        int[][] binaryArr = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            String binary = Integer.toBinaryString(arr[i]);
            
            while (binary.length() < n) {
                binary = "0" + binary;
            }
            
            for (int j = 0; j < n; j++) {
                binaryArr[i][j] = Character.getNumericValue(binary.charAt(j));
            }
        }
        return binaryArr;
    }
}