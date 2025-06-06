import java.util.*;

class Solution {

    Map<Integer, List<Integer>> trees = new HashMap<>();
    boolean[] visited = new boolean[1000001];

    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];

        // 1. 노드와 간선 정보 저장
        for (int node : nodes) trees.put(node, new ArrayList<>());
        for (int[] edge : edges) {
            trees.get(edge[0]).add(edge[1]);
            trees.get(edge[1]).add(edge[0]);
        }

        // 2. 홀짝 트리 검사
        for (int node : nodes) {
            // 아직 방문 전인 node이면서 해당 node를 root 노드로 했을 때 홀짝 트리가 되는 경우
            if (!visited[node] && checkHolZzak(node, -1)) answer[0]++;
        }

        // 3. 방문 배열 초기화
        visited = new boolean[1000001];

        // 4. 역 홀짝 트리 검사
        for (int node : nodes) {
            // 아직 방문 전인 node이면서 해당 node를 root 노드로 했을 때 역홀짝 트리가 되는 경우
            if (!visited[node] && checkReverseHolZzak(node, -1)) answer[1]++;
        }

        return answer;
    }

    // 홀짝 트리 검사
    private boolean checkHolZzak(int current, int parent) {
        
        // 자식 노드 리스트
        List<Integer> children = trees.get(current);
        int childrenSize = children.size() - 1; // 부모 노드를 제외한 자식 수만 구하기
        // 단 루트노드인 경우 다시 1을 더해주기
        if (parent == -1) childrenSize++;

        // 현재 수의 홀짝 여부와 자식 수의 홀짝 여부가 같은 경우
        if ((current & 1) == (childrenSize & 1)) {
            // 방문 표시하고
            visited[current] = true;
            // 해당 노드의 자식들도 체크하기
            for (int child : children) {
                if (child == parent) continue; // 부모 노드는 제외
                
                // 만약 한개라도 다르면 바로 false로 리턴
                if (!checkHolZzak(child, current)) {
                    visited[current] = false; // 여기서 방문 처리를 false로 다시 돌려놔야 다른 곳을 root로 해서 살펴볼 때 예외가 발생하지 않음!
                    return false; // 하나라도 불일치하면 실패
                }
            }
        } 
        // 틀린 경우 이미 실패
        else {
            return false;
        }
        return true; // 모든 조건을 만족하면 true 반환
    }

    // 역 홀짝 트리 검사
    private boolean checkReverseHolZzak(int current, int parent) {
        List<Integer> children = trees.get(current);
        int childrenSize = children.size() - 1;
        if (parent == -1) childrenSize++;

        if (current % 2 != childrenSize % 2) {
            visited[current] = true;
            for (int child : children) {
                if (child == parent) continue;
                if (!checkReverseHolZzak(child, current)) {
                    visited[current] = false;
                    return false;
                }
            }
        } 
        else return false;
        return true;
    }
}