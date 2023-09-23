import java.io.*;
import java.util.*;

/*
[ 최소 비용 신장 트리 (Minimum Spanning Tree) ]
신장 트리(그래프의 모든 간선과 일부 정점으로 이루어진 그래프) 중 최소한의 간선(또는 가중치)을 가지는 트리
무 방향 그래프이며 가중치를 가질 수도 있음
N개의 정점과 N-1개의 간선으로 구성되어 있음
사이클이 존재해서는 안됨!

두 가지 알고리즘으로 구현 가능 => 크루스칼 / 프림 // 현재 문제는 크루스칼 방법으로 구현!!

1) 크루스칼 알고리즘 (간선 중심)
모든 간선을 그 가중치에 따라서 오름차순으로 정렬하고 (Class Edge 구현 필요)
=> class Edge (int start, int end, int weight)
가중치가 낮은 간선부터 선택해가면서 (FIFO 구조의 Queue를 이용) 이어 나가기 (find-set과 union 방식 활용)
cf) 이 때, 만약 사이클이 존재한다면 가중치가 낮은 간선을 선택
Until 총 정점 갯수 - 1 개의 간선이 선택될 때까지
=> make-set / find-set / union 방식으로 구현

2) 프림 알고리즘 (정점 중심)
임의의 한 정점으로부터 시작해서 연결된 점들 중 최소비용간선을 갖는 정점을 선택해가면서 모든 정점이 선택될 때 까지 트리 구현 (Class Node 구현 필요)
=> class Node (int nodeNum, int weight)
해당 점까지의 최소 비용을 나타내는 배열(minDistance) 한 개와 방문처리용 배열(visited) 한 개 필요!
부모 배열(p)은 문제의 유형에 따라서 선택!!
시작 정점 우선순위 큐에 넣고, 해당 점에 연결된 점들 중 가중치가 낮은 점부터 빼나가면서 방문처리 & 가중치 더하기 및 다시 큐에 넣기
=> 그리디한 방식으로 구현
 */

class Edge {
    int startNum; // 시작 정점 번호
    int endNum; // 도착 정점 번호
    int weight; // 가중치

    public Edge(int startNum, int endNum, int weight) {
        this.startNum = startNum;
        this.endNum = endNum;
        this.weight = weight;
    }
}

public class Main
{
    static int V, E;
    static int[] p;

    static int findSet(int n)
    {
        if (p[n] != n) p[n] = findSet(p[n]);
        return p[n];
    }
    // findSet n번 정점의 최종 부모 정점을 찾아서 반환하는 함수
    // 만약 n번 정점의 부모가 n번 정점이라면 루트 노드이므로 그대로 반환하면 됨
    // 아니라면 n번 정점의 부모의 최종 부모 정점을 찾을 때까지 재귀를 돌려서 해당 번호를 반환!

    static void union(int a, int b) // b의 최종 부모의 부모를 a의 부모로 설정하면서 b를 a쪽에 합집합
    {
        p[findSet(b)] = findSet(a);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // Vertex Number
        E = Integer.parseInt(st.nextToken()); // Edge Number
        p = new int[V + 1];
        // make-set
        for (int v = 1; v <= V; v++) p[v] = v;
        PriorityQueue<Edge> edges = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        // get Edges
        for (int e = 1; e <= E; e++)
        {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 1 ~ 10000
            int B = Integer.parseInt(st.nextToken()); // 1 ~ 10000
            int weight = Integer.parseInt(st.nextToken()); // - million ~ + million
            edges.offer(new Edge(A, B, weight));
        }
        int minWeightOfMST = 0; // 뽑은 간선들의 가중치 합 => MST를 구성할 것이므로 그 값은 최소!!
        while (!edges.isEmpty())
        {
            Edge edge = edges.poll();
            if (findSet(edge.startNum) != findSet(edge.endNum)) // 같은 부모가 아니라면 즉, 서로 아직 연결되어 있지 않다면
            {
                union(edge.endNum, edge.startNum);
                minWeightOfMST += edge.weight;
            }
            // 만약 같은 부모라면 이미 다른 점에서 이어져있는 경우인데,
            // 이 경우 priorityQueue에서 가중치 오름차순으로 뽑아서 연결해왔기 때문에 먼저 나온 것의 가중치가 더 작으므로 그냥 버려버리면 됨!
        }
        ans.append(minWeightOfMST);
        bw.write(ans.toString());
        br.close();
        bw.close();
    }
}