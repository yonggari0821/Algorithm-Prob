import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {

        // t라는 단어를 만들었을 때의 필요한 단어 갯수의 최소값을 구하기 위해 그 전부터 이를 기록해두는 배열
        int[] dp = new int[t.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 시작점부터 0으로 두고 시작
        
        // t의 시작점부터 한글자씩 잡아서 단어 퍼즐을 대입해보면서 dp 채워 나가기
        for (int i = 0; i < t.length(); i++) {
            // 여태까지 도달 불가능했으면 그 뒤는 가볼 필요도 없으니 넘어가기
            if (dp[i] == Integer.MAX_VALUE) continue;
            
            // 도달 가능했다면 이제 strs에 담긴 단어 조각들 매칭 시켜보기
            for (int j = 0; j < strs.length; j++) {
                String cur = strs[j];
                
                // 매칭 시 범위부터 체크
                if (i + cur.length() > t.length()) continue;
                
                // 범위체크 후 글자 하나하나 매칭 확인해서
                int curStringIndex = 0;
                int curStringSize = cur.length();
                while (curStringIndex < curStringSize) {
                    if (t.charAt(i + curStringIndex) != cur.charAt(curStringIndex)) break;
                    curStringIndex++;
                }
                // 전부다 매칭 되지 않았다면 skip!
                if (curStringIndex != curStringSize) continue;
                
                // 매칭 됐다면 해당 단어 조각 크기만큼 이동한 곳에 기록
                dp[i + curStringSize] = Math.min(dp[i + curStringSize], dp[i] + 1);
            }
        }
        return dp[t.length()] == Integer.MAX_VALUE ? -1 : dp[t.length()];
        // return dp[t.length() - 1] == Integer.MAX_VALUE ? -1 : dp[t.length() - 1];
    }
}