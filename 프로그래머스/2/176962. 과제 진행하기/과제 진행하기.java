import java.util.*;

class Solution {

    private static int aboutTime(String hhmm) {
        StringTokenizer st = new StringTokenizer(hhmm, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        return hour * 60 + minute;
    }

    static class Task {
        String name;
        int remainTime;

        Task(String name, int remainTime) {
            this.name = name;
            this.remainTime = remainTime;
        }
    }

    public String[] solution(String[][] plans) {
        Arrays.sort(plans, new Comparator<String[]>(){
            @Override
            public int compare(String[] a, String[] b) {
                return a[1].compareTo(b[1]);
            }
        });

        // 남은 일
        Stack<Task> leftWorks = new Stack<>();
        // 답
        List<String> answerList = new ArrayList<>();

        for (int i = 0; i < plans.length - 1; i++) {
            String name = plans[i][0];
            int startTime = aboutTime(plans[i][1]);
            int playTime = Integer.parseInt(plans[i][2]);

            int nextStartTime = aboutTime(plans[i + 1][1]);

            // 현재 작업의 종료 시간 & 다음 작업의 시작시간까지 남은 시간
            int currentEnd = startTime + playTime;
            int availableTime = nextStartTime - startTime;

            // 다음 작업의 시작시간까지 남은 시간이 현재 작업이 걸리는 시간보다 많다면
            if (availableTime >= playTime) {
                // 현재 작업 다 하고 다음 작업 시작 가능하므로 현재 작업 답 리스트에 넣기
                answerList.add(name);
                // 그 후 빈 시간 있으면 남은 시간 동안 이전에 미뤄둔 작업 체크해서 있다면 처리
                int freeTime = availableTime - playTime;

                // 중단된 작업 처리
                while (freeTime > 0 && !leftWorks.isEmpty()) {
                    Task lastTask = leftWorks.pop();
                    // 다 할 수 있는 경우
                    if (lastTask.remainTime <= freeTime) {
                        freeTime -= lastTask.remainTime;
                        answerList.add(lastTask.name);
                    }
                    // 다는 못하는 경우
                    else {
                        lastTask.remainTime -= freeTime;
                        leftWorks.push(lastTask);
                        freeTime = 0;
                    }
                }
            } 
            // 다음 작업의 시작시간이 일러서 현재 작업 다 못하는 경우
            else {
                // 현재 작업 중단, 남은 시간 저장
                int remain = playTime - availableTime;
                leftWorks.push(new Task(name, remain));
            }
        }

        // 마지막 작업은 무조건 완료
        String lastName = plans[plans.length - 1][0];
        answerList.add(lastName);

        // 남은 작업 역순 처리
        while (!leftWorks.isEmpty()) {
            answerList.add(leftWorks.pop().name);
        }

        return answerList.toArray(new String[0]);
    }
}
