package 다트게임;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    public static int solution(String dartResult) throws Exception {
        int answer = 0;
        StringTokenizer str = new StringTokenizer(dartResult, "SDT*#", true);
        LinkedList<String> cut = new LinkedList<>();
        while (str.hasMoreTokens())
        {
            cut.add(str.nextToken().trim());
        }

        int tmp = 0;
        int numcnt = 0;
        int laststar = 0;
        int[] answerarr = new int[dartResult.length() / 2 + 1]; // n번 던질 경우 dartResult는 2n~3n의 길이를 가짐!

        for (int i = 0; i < cut.size(); i++)
        {
            if (cut.get(i).equals("S"))
                continue;
            else if (cut.get(i).equals("D"))
                tmp = tmp * tmp;
            else if (cut.get(i).equals("T"))
                tmp = tmp * tmp * tmp;
            else if (cut.get(i).equals("*"))
            {
                answerarr[numcnt - 1] *= 2;
                tmp *= 2;
            }
            else if (cut.get(i).equals("#"))
                tmp *= -1;
            else
            {
                answerarr[numcnt] = tmp;
                String tmpstr = new String(cut.get(i));
                numcnt++;
                try {
                    tmp = Integer.parseInt(tmpstr);
                }
                catch (Exception e){}
            }
            System.out.println(cut.get(i)); // 구분자 및 숫자로 구분되는 결과 출력!
        }
        System.out.println("---");

        if (laststar != numcnt)
            answerarr[numcnt] = tmp;

        for (int j = 0; j < answerarr.length; j++)
            System.out.println(answerarr[j]); // n번째 다트 점수 출력 (첫 값은 0번째이므로 무조건 0이 나오니 무시!)
        System.out.println("---");

        for (int j = 0; j < answerarr.length; j++)
            answer += answerarr[j];

        return answer;
    }

    public static void main(String[] args) throws Exception{
        String dartResult = new String();
        dartResult = "1D2S3T*";
        System.out.println(solution(dartResult));
    }
}
