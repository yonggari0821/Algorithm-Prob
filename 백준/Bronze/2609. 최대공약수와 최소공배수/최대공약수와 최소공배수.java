import java.io.*;
import java.util.*;

/*
백준 2609 최대공약수와 최소공배수
유클리드 호제법을 이용해서 풀면 됨
추가적으로
최소 공배수 = ( (수 1 / 최대 공약수) * (수 2 / 최대 공약수) ) * 최대 공약수
임을 알면 편하다.
*/

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n,m;

    static int gcd(int a, int b)
    {
        while( b > 0 )
        {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }


    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        System.out.println(gcd(n, m));
        System.out.println(gcd(n, m) * ( (n / gcd(n, m)) * (m / gcd(n, m)) ));
    }
}