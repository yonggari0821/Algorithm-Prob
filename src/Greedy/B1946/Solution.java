package B1946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 갯수
        StringBuilder sb = new StringBuilder(); // 최종 답을 위한 StringtBuilder
        for (int t = 0; t < T; t++)
        {
            int N = Integer.parseInt(br.readLine()); // 테스트 케이스 한 개의 시험자 수
            int[] score = new int[N + 1]; // 굳이 2차원 배열로 안하고 1차원 배열로 해서 서류 등수를 index로 면접 등수를 value로 해도 된다.
            int tmp = 1; // 최종 답을 위한 임시 값 => 시험자가 1명이거나 서류등수와 면접등수가 모두가 동일하더라도 1명은 뽑히므로 디폴트값 1로!
            for (int i = 0; i < N; i++)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken()); // 서류점수(paper)
                int m = Integer.parseInt(st.nextToken()); // 면접점수(meeting)
                score[p] = m; // 서류등수를 index로 면접등수를 value로 하면 자동으로 정렬까지 됨! (1차원 배열로 하는 게 오히려 좋음!!)
            }
            int max = score[1]; // 면접 등수 비교를 위한 초기값(서류 1등의 면접 등수를 기초값으로 둠)

            for (int i = 2; i <= N; i++) // 1등 제외 2등부터 자기 보다 서류 등수가 높은(수적으로는 작은) 사람들 중 가장 높은 면접 등수 보다 높아야(수적으로는 작은) 뽑힐 수 있음!
            {
                if (score[i] < max) // 만약 그렇다면 면접 최고 등수 갱신 for 다음 비교
                {
                    max = score[i];
                    tmp++; // 뽑힌 사람 수 임시값++
                }
            }
            sb.append(tmp).append('\n'); // 전부 뽑고 나면 임시값을 최종 출력할 sb에 붙여주고 줄바꿈으로 테스트 케이스마다 구분!
        }
        String ans = sb.toString(); // 최종 답
        System.out.println(ans);
    }
}
