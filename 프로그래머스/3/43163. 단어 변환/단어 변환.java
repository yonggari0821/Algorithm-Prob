class Solution {

    static boolean[] used;
    static String[] candidates;
    static int finalIdx;
    static int successConvNum;

    static void getConvNum (int cur, int convNum) {
        if (cur == finalIdx) {
            successConvNum = Math.min(convNum, successConvNum);
            return;
        }
        used[cur] = true;
        for (int i = 0; i < candidates.length - 1; i++) {
            if (used[i]) continue;
            if (getDifferences(candidates[cur], candidates[i]) == 1) {
                getConvNum(i, convNum + 1);
            }
        }
    }

    // 다른 글자 수 구하기
    static int getDifferences(String str1, String str2) {
        int num = 0;
        for (int i = 0; i < str1.length(); i++) {
            num += str1.charAt(i) != str2.charAt(i) ? 1 : 0;
        }
//        System.out.println(str1 + " and " + str2 + " has " + num + " diffs!");
        return num;
    }

    public int solution(String begin, String target, String[] words) {
        int wordsNum = words.length; // 단어 갯수
        used = new boolean[wordsNum + 1]; // 사용 여부
        finalIdx = -1; // 최종 문자열의 인덱스
        successConvNum = Integer.MAX_VALUE; // 최종 변환 횟수
        // 최종 문자열의 인덱스를 찾음
        for (int i = 0; i < wordsNum; i++) {
//            System.out.println("words[ " + i + " ] = " + words[i]);
            if (words[i].equals(target)) {
                finalIdx = i;
                break;
            }
        }
//        System.out.println("finalIdx = " + finalIdx);
        // 만약에 최종 문자열이 없으면 바로 0 return
        if (finalIdx == -1) return 0;
        // 시작 문자열을 맨 마지막에 포함하는 후보군 배열
        candidates = new String[wordsNum + 1];
        // 후보군에 후보 채우기 (시작 문자열도 맨 마지막에 포함해주기!)
        for (int i = 0; i < wordsNum; i++) {
            candidates[i] = words[i];
        }
        candidates[wordsNum] = begin;
        // 변환 횟수 구하기
        getConvNum(wordsNum, 0);
        return successConvNum == Integer.MAX_VALUE ? 0 : successConvNum;
    }
}