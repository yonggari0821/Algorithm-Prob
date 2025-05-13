import java.util.*;

class Solution {

    static HashMap<String, String> map = new HashMap();
    static HashMap<String, Integer> idxMap = new HashMap();

    static void func(int toAdd, String cur, int[] ans) {
        if (cur.equals("center") || toAdd < 1) return; // center면 종료, 1원 미만이면 종료

        int idx = idxMap.get(cur);
        int toParent = toAdd / 10; // 10% 절사
        ans[idx] += toAdd - toParent; // 자기 몫

        func(toParent, map.get(cur), ans); // 추천인에게 분배
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        int pplNum = enroll.length;
        int[] answer = new int[pplNum];
        map.clear();
        idxMap.clear();

        for (int i = 0; i < pplNum; i++) {
            if (referral[i].equals("-")) {
                map.put(enroll[i], "center");
            } else {
                map.put(enroll[i], referral[i]);
            }
            idxMap.put(enroll[i], i);
        }

        for (int i = 0; i < seller.length; i++) {
            int toPlus = amount[i] * 100;
            String cur = seller[i];
            func(toPlus, cur, answer);
        }

        return answer;
    }
}
