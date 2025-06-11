import java.util.*;

class Food {
    String type;
    int num;
    
    public Food(String type, int num) {
        this.type = type;
        this.num = num;
    }
    
    static Comparator<Food> typeComparator = new Comparator<>() {
        @Override
        public int compare(Food f1, Food f2) {
            return f1.type.compareTo(f2.type);
        }
    };
}

class Solution {
    static boolean checkPossible(int[] cur, Food[] target) {
        for (int i = 0; i < target.length; i++) {
            if (cur[i] != target[i].num) return false;
        }
        return true;
    }
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int[] cur = new int[want.length];
        Food[] target = new Food[want.length];
        
        for (int i = 0; i < want.length; i++) {
            target[i] = new Food(want[i], number[i]);
        }
        
        Arrays.sort(target, Food.typeComparator);
        
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i].type, i);
        }
        
        // 첫 번째 윈도우 (0~9일)
        for (int i = 0; i < 10; i++) {
            if (map.containsKey(discount[i])) {
                cur[map.get(discount[i])]++;
            }
        }
        
        if (checkPossible(cur, target)) answer++;
        
        // 슬라이딩 윈도우
        for (int i = 10; i < discount.length; i++) {
            // 왼쪽 끝 제거
            if (map.containsKey(discount[i - 10])) {
                cur[map.get(discount[i - 10])]--;
            }
            // 오른쪽 끝 추가
            if (map.containsKey(discount[i])) {
                cur[map.get(discount[i])]++;
            }
            
            if (checkPossible(cur, target)) answer++;
        }
        
        return answer;
    }
}
