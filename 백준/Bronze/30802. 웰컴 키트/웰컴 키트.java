import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[] List = new int[6];
		for (int i=0;i<6;i++) {
			List[i]=sc.nextInt();
		}
		int a = sc.nextInt();
		int b = sc.nextInt();
		int t=0;
		for (int i=0;i<6;i++) {
			t+=List[i]/a;
			if (List[i]%a!=0) {
				t++;
			}
		}
		System.out.println(t);
		System.out.println(num/b+" "+num%b);
	}
}
