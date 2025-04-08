import java.util.*;

/*

많이 재생된 장르 먼저
장르 내에서는 많이 재생된 노래 먼저
횟수까지 같은면 고유 번호가 낮은 노래 먼저(인덱스)

*/
class Song {
    int num;
    int play;
    
    public Song (int num, int play) {
        this.num = num;
        this.play = play;
    }
}


class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        int len = genres.length;
        
        // 장르 별 재생횟수
        Map<String, Integer> map1 = new HashMap<>();
        
        // 장르 별 노래 별 재생횟수     
        Map<String, Queue<Song>> map2 = new HashMap<>();
        
        for (int i = 0; i < len; i++) {
            map1.put(genres[i], map1.getOrDefault(genres[i], 0) + plays[i]);
            
            if (map2.containsKey(genres[i])) {
                map2.get(genres[i]).offer(new Song(i, plays[i]));
            }
            else {
                Queue<Song> pq = new PriorityQueue<Song>((a, b) -> b.play == a.play ? a.num - b.num : b.play - a.play);
                pq.offer(new Song(i, plays[i]));
                map2.put(genres[i], pq);
            }
        }
        
        List<String> genreList = new ArrayList<>();
        int cnt = 0;
        for (Map.Entry<String, Queue<Song>> entry : map2.entrySet()) {
            String gen = entry.getKey();
            int playNums = entry.getValue().size();
            // System.out.println(gen + " played " + playNums + " times!");
            genreList.add(gen);
            cnt += Math.min(playNums, 2);
        }

        answer = new int[cnt];
        genreList.sort((a, b) -> map1.get(b) - map1.get(a));
        
        int idx = 0;
        for (int i = 0; i < genreList.size(); i++) {
            Queue<Song> curPq = map2.get(genreList.get(i));
            answer[idx++] = curPq.poll().num;
            if (!curPq.isEmpty()) answer[idx++] = curPq.poll().num;
        }
        
        return answer;
    }
}