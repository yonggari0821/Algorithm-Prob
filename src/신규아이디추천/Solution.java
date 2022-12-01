package 신규아이디추천;

public class Solution {
    public static String solution(String new_id) {
        String answer = "";
        new_id = new_id.toLowerCase();
        StringBuilder sb = new StringBuilder(new_id);
        // 1단계 : toLowerCase는 소문자로 바꿔서 '반환'하는 함수지 void 함수가 아니므로 재초기화 필요!!

        for (int i = 0; i < sb.length(); i++)
        {
            if (sb.charAt(i) != '-' && sb.charAt(i) != '_' && sb.charAt(i) != '.' && !(sb.charAt(i) >= '0' && sb.charAt(i) <= '9') && !(sb.charAt(i) >= 'a' && sb.charAt(i) <= 'z')) {
                sb.deleteCharAt(i);
                i -= 1;
            }
        }
        // 2단계 : 유효하지 않은 것이 발견되면 지우고 인덱스 - 1 (StringBuilder는 알아서 땡겨지기 때문에 - 1을 해줘야 다시 그곳부터 체크 가능!!)

        for (int i = 0; i + 1 < sb.length(); i++)
        {
            if (sb.charAt(i) == '.')
            {
                while (i + 1 < sb.length() && sb.charAt(i + 1) == '.')
                {
                    sb.deleteCharAt(i+1);
                }
            }
        }
        // 3단계 : i번째가 온점이면 그 다음꺼를 체크해서 온점이 아닐 때 까지 지우기! 이 때, i + 1 < sb.length() 이 조건이 선행하는 것이 꼭 필요!!

        if (sb.length() > 0 && sb.charAt(0) == '.')
            sb.deleteCharAt(0);
        if (sb.length() > 0 && sb.charAt(sb.length()-1) == '.')
            sb.deleteCharAt(sb.length() - 1);
        // 4단계 : sb.length() > 0 이 조건이 선행하는 것이 꼭 필요!!

        if (sb.toString().equals("") | sb.toString().equals(0))
            sb.append('a');
        // 5단계 : sb는 StringBuilder지 String이 아니므로 toString()부터 해주고 equals 사용할 것!!

        if (sb.length() > 15)
            sb.setLength(15);
        // 6단계 : 길이가 15보다 길때만 15로 바꿔주면 됨! 작을 때도 바꾸면 0으로 채워지기 때문에!!

        if (sb.charAt(sb.length()-1) == '.')
            sb.deleteCharAt(sb.length() - 1);
        if (sb.length() <= 2)
        {
            for (int i = sb.length(); i < 3; i++ )
                sb.append(sb.charAt(sb.length() - 1));
        }
        // 7단계 : i를 sb.length()로 두고 그게 3될때까지 마지막 글자를 더해줌!

        answer = sb.toString();
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("새로운 아이디로 입력받은 문자열"));
    }
}