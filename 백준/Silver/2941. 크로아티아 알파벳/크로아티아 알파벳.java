import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();
        int len = text.length();
        int count = 0;

        for (int i = 0; i < len; i++) {
            char ch = text.charAt(i);
            if (ch == 'c' && i < len - 1) {
                if (text.charAt(i + 1) == '=' || text.charAt(i + 1) == '-') {
                    i++;
                }

            } else if (ch == 'd' && i < len - 1) {
                if (text.charAt(i + 1) == '-') {    // d- 일 경우
                    i++;
                } else if (i < len - 2 && text.charAt(i + 1) == 'z') {
                    if (text.charAt(i + 2) == '=') {    // dz= 일 경우
                        i += 2;
                    }
                }
            } else if ((ch == 'l' || ch == 'n') && i < len - 1) {
                if (text.charAt(i + 1) == 'j') {    // lj 또는 nj 일 경우
                    i++;
                }
            } else if ((ch == 's' || ch == 'z') && i < len - 1) {
                if (text.charAt(i + 1) == '=') {    // s= 또는z= 일 경우
                    i++;
                }
            }
            count++;
        }
        System.out.println(count);
    }
}
