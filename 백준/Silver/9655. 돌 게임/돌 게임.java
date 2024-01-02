import java.io.*;
import java.util.*;

/*
[DP 9655]_돌 게임_안상준

상근 => 창영
순서대로 주어지는 돌 중
3개 또는 1개씩 가져갈 때 마지막 돌을 가져가는 사람이 승리

잘 보면 홀수일때는 무조건 상근이가
짝수일때는 무조건 창영이의 승이다.

 */




public class Main {
	static int N;
	static String itSK(boolean skcy)
	{
		if (skcy) return "SK";
		return "CY";
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		System.out.println( itSK( (N & 1) != 0 ) );
		br.close();
	}
}