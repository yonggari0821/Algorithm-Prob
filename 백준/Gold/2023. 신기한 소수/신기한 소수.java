import java.io.*;
import java.util.*;

public class Main {

    static int n; // 1 ~ 8
    static StringBuilder sb = new StringBuilder();

    /*
    8 자리면 최대 99999999까지 가능하므로
    배열 만들어서 하면 안됨!
    일단 자릿수에 소수만 더해서 수를 만든 후에
    그 수의 루트를 씌운 값까지 소수 판별을 하면 됨!

     */
    static boolean isPrime(int num) {
        int a = 0;
        int og = num;
        while (num > 10) {
            num /= 10;
            a++;
        }
        for (int b = a; b >= 0; b--) {
            int tmp = og / (int)Math.pow(10, b);
            if (tmp <= 1) return false;
            for (int i = 2; i <= (int) Math.sqrt(tmp); i++) {
                if (tmp % i == 0) return false;
            }
        }
        return true;
    }

    static void func (int num, int picked) {
        if (picked == n) {
            if (isPrime(num)) sb.append(num).append('\n');
            return ;
        }

        for (int i = 1; i <= 9; i++) {
            int next = num * 10 + i;
            if (isPrime(next)) func(num * 10 + i, picked + 1);
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        func(0, 0);

        System.out.println(sb.toString());
    }
}