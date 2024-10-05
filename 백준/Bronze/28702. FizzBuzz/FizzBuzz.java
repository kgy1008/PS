import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int count=0;
        int idx=3;
        for(int i=0; i<3; i++){
            String a = br.readLine();
            if(!a.equals("Fizz") && !a.equals("Buzz") && !a.equals("FizzBuzz")){
                count = Integer.valueOf(a);
                idx = i;
            }
        }

        if (idx == 0) {
            count += 3;
        } 
        else if (idx == 1) {
            count += 2;
        } 
        else if (idx == 2) {
            count += 1;
        }

        if(count%3==0){
            if(count%5==0){
                System.out.println("FizzBuzz");
            }
            else{
                System.out.println("Fizz");
            }
        }
        else{
            if(count%5==0){
                System.out.println("Buzz");
            }
            else{
                System.out.println(count);
            }
        }

    }
}