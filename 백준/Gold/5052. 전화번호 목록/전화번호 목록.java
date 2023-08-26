import java.io.*;
import java.util.*;

class digit
{
    char c;
    digit[] nextNum;
    boolean isEnd;

    public digit(char c, digit[] nextNum, boolean isEnd) {
        this.c = c;
        this.nextNum = nextNum;
        this.isEnd = isEnd;
    }
}

public class Main {
    static boolean con;
    static String isCon(boolean con)
    {
        if  (con) return "YES";
        return "NO";
    }
    static void conCheck(digit d)
    {
    	if (d.isEnd)
    	{
    		for (int i = 0; i < 10; i++)
			{
				if (d.nextNum[i] != null) {
					con = false;
					return;
				}
			}
    	}
    	for (int i = 0; i < 10; i++)
		{
			if (d.nextNum[i] != null) conCheck(d.nextNum[i]);
		}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            con = true; // 일관성 여부 default는 true
            int N = Integer.parseInt(br.readLine()); // 전화번호 갯수
            char[][] nums = new char[N][]; // 전화번호들 각각
            for (int n = 0; n < N; n++) nums[n] = br.readLine().toCharArray(); // 전화번호 받아서 각 숫자를 캐릭터로 받아 둠
            digit root = new digit('r', new digit[10], false); // Trie의 최상단 노드인 root
            for (int n = 0; n < N; n++)
            {
                digit cur = root; // 번호 바뀔 때 마다 루트에서 가져옴
                for (int i = 0; i < nums[n].length; i++) // 가져온 번호 순회
                {
                    char nowDigit = nums[n][i]; // 가져온 번호의 i번째 숫자 (-48해주면 int화)
                    if (cur.nextNum[nowDigit - 48] == null) cur.nextNum[nowDigit - 48] = new digit(nowDigit, new digit[10], false);
                    cur = cur.nextNum[nowDigit - 48];
                }
                cur.isEnd = true; // 끝 처리
            }
            digit check = root;
            conCheck(check);
            ans.append(isCon(con)).append('\n');
        }
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}