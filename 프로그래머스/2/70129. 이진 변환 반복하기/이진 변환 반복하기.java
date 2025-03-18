class Solution {
    public int[] solution(String s) {
        // 답변용 배열
        int[] ans = new int[2];
        
        // 2진수 문자열에서의 0의 갯수
        int zeroCnt = 0;
        
        // 2진수 문자열에서의 1의 갯수
        int oneCnt = 0;
        
        // 처음 문자열에서 int값으로 바꿀때는 직접 세서 변환(최대 15만 이므로!)
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') zeroCnt++;
            else oneCnt++;
        }
        
        // 현재 값 == 1의 갯수
        int val = oneCnt;
        
        // 변환 횟수는 1부터 시작
        int convertCnt = 1;
        
        int len; // 아래 주석 참고
        // val이 1보다 클 때동안
        while (val > 1) {
            // 현재 값을 2진수 문자열로 바꿨을 때의 길이
            // 현재 값의 highestOneBit의 값의 numberOfTrailingZeros의 갯수 + 1
            len = Integer.numberOfTrailingZeros(
                Integer.highestOneBit(val)
            ) + 1;
            
            // 현재 값의 2진수 문자열의 1의 갯수
            // bitCount 활용
            oneCnt = Integer.bitCount(val);
            
            // 다음 값으로 바뀌기 위해 제거해야 하는 0의 갯수
            // 현재 값의 2진수 문자열의 길이(len) - 현재 값의 2진수 문자열의 1의 갯수
            zeroCnt += (len - oneCnt);
            
            // 다음 값 = 현재 값의 2진수 문자열의 1의 갯수 & 변환 횟수 + 1
            val = oneCnt;
            convertCnt++;
        }
        
        // 답변 배열에 값 배정 후 return
        ans[0] = convertCnt;
        ans[1] = zeroCnt;
        
        return ans;
    }
}