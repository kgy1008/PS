import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] croatias = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        String text = br.readLine();

        for (int i = 0; i < croatias.length; i++) {
            if (text.contains(croatias[i])) {
                text = text.replace(croatias[i], "!");
            }
        }
        System.out.println(text.length());
        br.close();
    }
}
