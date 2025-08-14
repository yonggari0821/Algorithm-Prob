import java.util.*;

/*
    1) 서비스 가입자를 최대한 늘리는 것.
    2) 판매액을 최대한 늘리는 것.
    
    n명에게 m개를 할인 판매
    이모티콘마다 할인율 다름 (10 20 30 40% 중 택 1)
    
    이모티콘 구매 or 플러스 서비스 가입
    기준에 따라 일정 비율 이상 할인 이모티콘을 모두 구매한다고 가정하고
    구매 비용의 합이 일정 가격 이상 되면 구매를 모두 취소하고 서비스 가입
    
    10 / 20 / 30 / 40 할인에 따라
    사용자가 구매하는 이모티콘 가격의 총합을 구해서 users의 최대 가격과 비교해서 answer 구하기
    
    1 ≤ users의 길이 = n ≤ 100
    1 ≤ emoticons의 길이 = m ≤ 7
    이므로 자료구조 만들때 부담 X
    
    
    이모티콘 별로 할인율 다르게 할 수 있으므로 모든 조합을 구해야 함!!!!
*/

class ServiceOrPurchase implements Comparable<ServiceOrPurchase> {
    int service;
    int purchase;
    
    ServiceOrPurchase(int s, int p) {
        this.service = s;
        this.purchase = p;
    }
    
    @Override
    public int compareTo(ServiceOrPurchase o) {
        if (this.service == o.service) return o.purchase - this.purchase;
        return o.service - this.service;
    }
}

class Solution {
    
    private static int emoticonCount; // 이모티콘 갯수 == 이모티콘 별 할인가격의 row수 == dfs시에 이모티콘 별 할인율 수
    private static int[][] discounted;
    private static ArrayList<ServiceOrPurchase> list = new ArrayList<>();
    
    private static void dfs(int depth, int[] salesPercent, int[][] users) {
        if (depth == emoticonCount) {
            // 서비스 가입자 수
            int sc = 0;
            // 이모티콘 개별 구매 가격
            int ep = 0;
            for (int i = 0; i < users.length; i++) {
                int tmpP = 0;
                
                for (int j = 0; j < emoticonCount; j++) {
                    if (salesPercent[j] * 10 >= users[i][0]) tmpP += discounted[j][salesPercent[j]];
                }
                
                // 기준 가격 이상이면 서비스 가입
                if (tmpP >= users[i][1]) sc++;
                // 아니면 이모티콘 개별 구매
                else ep += tmpP;
            }
            
            // System.out.print("현재 할인율 조합: ");
            // for (int j = 0; j < emoticonCount; j++) {
            //     System.out.printf("%d%% ", salesPercent[j]*10);
            // }
            // System.out.printf("\n→ 서비스 가입자 수 : %d\n→ 이모티콘 개별 구매 금액 : %d\n", sc, ep);
            // System.out.println("리스트에 결과를 추가합니다.\n");
            
            list.add(new ServiceOrPurchase(sc, ep));
            return;
        }
        
        for (int p = 1; p <= 4; p++) {
            salesPercent[depth] = p;
            dfs(depth + 1, salesPercent, users);
            salesPercent[depth] = 0;
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        // 이모티콘 갯수
        emoticonCount = emoticons.length;
        // 할인가
        discounted = new int[emoticonCount][5];
        
        // 이모티콘 별로 할인가 구해두고
        for (int i = 0; i < emoticonCount; i++) {
            for (int p = 1; p <= 4; p++) {
                discounted[i][p] = (emoticons[i] * (100 - p * 10) / 100);
                // System.out.printf("%d번째 물건의 %d%% 할인가는 %d입니다\n", i, p*10, discounted[i][p]);
            }
        }
        
        dfs(0, new int[emoticonCount], users);
        
        Collections.sort(list);
        
        return new int[]{list.get(0).service, list.get(0).purchase};
    }
}