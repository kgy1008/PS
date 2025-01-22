import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();
        int size = bomb.length();

        for (int i = 0; i < target.length(); i++) {
            sb.append(target.charAt(i));

            if (sb.length() >= size) {
                boolean match = true;
                for (int j = 0; j < size; j++) {
                    if (sb.charAt(sb.length() - size + j) != bomb.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    sb.delete(sb.length() - size, sb.length());
                }
            }
        }

        if (sb.isEmpty()) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }

}
