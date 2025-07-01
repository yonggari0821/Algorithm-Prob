import java.util.*;

/*
    영문 대문자는 원래 문구, 소문자는 특수기호를 의미
    이 문장을 못 봐서 원래 문구에도 소문자가 들어갈 수 있다고 생각해서 정말 엄청 어려운 문제라고 생각했었음....
    
    그냥 소문자인 경우 위치 기억해두고
    꺼내서 예외 제거하고 마지막에 공백제거하면 되는 문제였음!
*/
public class Solution {
	
	private static char[] sentence;
    private static String invalid = "invalid";
    private static LinkedHashMap<Character, List<Integer>> chars; // 글자들의 위치를 파악해두기 위한 연결 해시맵
    // 단어 구하기 메서드 (대문자만 진짜 단어)
	public String getStr(int start, int end) {
		StringBuilder str = new StringBuilder(); char c;
		for (int i = start; i <= end; ++i) {
			c = sentence[i];
			if (c >= 'A' && c <= 'Z')
				str.append(c);
		}
		return str.toString() + " ";
	}
	
	class Word {
		int rule, start, end;
		public Word(int rule, int start, int end) {
			this.rule = rule;
			this.start = start;
			this.end = end;
		}
	}
	
	public String solution(String s) {
    
        // 문자 리스트를 char[] 형태로 변환해두기
		sentence = s.toCharArray();
		
        // 선언 및 초기화
		int len = s.length(), i; 
        char c;
        chars = new LinkedHashMap<>();
        
        // 문자들 순회하면서 소문자의 경우 chars에 저장 => 특수 기호에 해당 하므로 향후 규칙 체크해야 함!
		for (i = 0; i < len; ++i) {
			c = sentence[i];
			if (Character.isLowerCase(c)) {
				chars.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
			}
		}
        
		
		List<Word> words = new ArrayList<>();
		int start_str = 0, // 현재 문자열의 시작 인덱스
        // 특수 문자 관련
			start_char, // 현재 특수 문자의 첫 등장 위치
            end_char, // 현재 특수 문자의 마지막 등장 위치
            start_char_pre = -1, // 이전 특수 문자의 첫 등장 위치
            end_char_pre = -1, // 이전 특수 문자의 마지막 등장 위치
        // 단어 관련
			start_word = 0, // 현재 단어의 시작 인덱스
            end_word = 0, // 현재 단어의 끝 인덱스
            end_word_pre = -1, // 이전 단어의 끝 인덱스
        // 기타
			num, // 문자의 등장 횟수
            rule = -1, // 규칙 (0: 미정 / 1: 규칙1 / 2: 규칙2)
            distance; // 문자 간 간격
        boolean isDistance2; // 문자 간 간격이 2인지 여부(규칙 1 때문에 필요!) 
        
        // chars에서 key값 말고 value값들 즉, 각 특수 문자들의 위치 index들의 모음인 positions 들만 꺼내서
        // 예를 들면 특수문자가 a인 경우 a가 등장한 모든 위치의 idx값들이 들어가있는 positions를 살펴보는 것!
		for (List<Integer> positions : chars.values()) {
			num = positions.size();
            // 단어 & 문자의 시작
			start_word = start_char = positions.get(0);
            // 단어 & 문자의 끝
			end_word = end_char = positions.get(num - 1);
            // 기본적으로 거리가 2라고 생각하기
			isDistance2 = true;
            
            // 동일 문자 간 거리 체크
			for (i = 1; i < num; i++) {
                // 거리
				distance = positions.get(i) - positions.get(i - 1);
                
                // 연속으로 붙어 있는 경우 => invalid
				if (distance < 2) return invalid;
                
                // 2 이상인 경우 => invalid 또는 규칙 2의 가능성 존재
				if (distance > 2) {
					isDistance2 = false;
					break;
				}
			}
            
            // 3번 이상 등장 => 확실히 규칙 1만 가능한데 => isDistance2가 아님 ==> invalid
			if (num >= 3 && !isDistance2) return invalid;
            
            // 1번만 등장하거나 3번 이상 등장했음 => 규칙 1만 가능
			else if (num == 1 || num >= 3) {
				rule = 1;
                // 규칙 1이므로 실제 단어는 특수문자 앞
				start_word--;
                // 규칙 1이므로 실제 단어는 특수문자 뒤까지
				end_word++;
                // 만약 맨 앞뒤의 글자가 없는 경우 규칙 1이 온전하게 적용된 게 아니므로 invalid
				if (start_word < 0 || len <= end_word) return invalid;
			}
            
            // 등장 횟수 2이면 => isDistance2값에 따라 규칙 2로 확정되거나 미정
			else if (num == 2) rule = isDistance2 ? 0 : 2; // 규칙 0 => 규칙 1 또는 2
            
            // 만약 이전 문자의 시작위치보다 현재 문자의 시작위치가 더 나중이고
            // 이전 문자의 끝 위치보다 현재 문자의 끝 위치가 더 먼저라면
            // 즉, 이전 문자의 범위에 현재 문자의 범위가 포함된다면 => 규칙 2가 규칙 1을 품은 경우만 허용 가능함!
            // ex) b H a E a L a L a O b
			if (start_char_pre < start_char && end_char < end_char_pre) {
                // 규칙 2인 경우 == invalid 한 경우 (한 단어에 규칙 2가 2번 들어갔다는 의미이므로!)
				if (rule == 2) return invalid; 
				continue; // 규칙 2 안의 규칙 1인 경우는 허용!
			}
            
            // 단어의 범위가 안맞음 => 이전 단어의 끝이 현재 단어 시작보다 크거나 같음 == 범위 겹침 == 말이 안됨
			if (end_word_pre >= start_word) return invalid;
            
            // 모든 예외 케이스를 통과 시 단어 추가
			words.add(new Word(rule, start_word, end_word));
            
            // 현재 것들을 이전 것들로 이전
			start_char_pre = start_char;
			end_char_pre = end_char;
			end_word_pre = end_word;
		}
        
		// 최종 답안 == words에 들은 word들을 rule에 맞게 이어 붙히기!
		StringBuilder answer = new StringBuilder();
		int size = words.size(); 
        Word word;
		
        for (i = 0; i < size; ++i) {
			word = words.get(i); // 현재 단어
			rule = word.rule; // 현재 규칙
            // 시작 위치와 끝 위치
			start_word = word.start;
			end_word = word.end;
            
            // 규칙 1 또는 2
			if (rule == 0) {
                // 규칙 1
				if (
                    // 앞쪽과 뒤쪽에 모두에 공간이 있다면 => 규칙 1일 수 밖에 없음
                    (start_str <= start_word - 1) 
                    && (
                        end_word + 1 
                            < 
                        (i < size - 1 ? words.get(i + 1).start : len)
                    )
                ) 
                { 
					start_word--;
					end_word++;
				}
			}
            // 앞에 처리되지 않은 문자열이 있다면 해당 문자열부터 붙히기
			if (start_str < start_word) answer.append(getStr(start_str, start_word - 1));
			// 이후 해당 문자열 붙히기
            answer.append(getStr(start_word, end_word));
            // 시작 문자열 다음 범위로 설정해두기
			start_str = end_word + 1;
		}
        
        // 마지막 단어 이후 남은 문자열이 있다면 답에 붙히기
		if (start_str < len) answer.append(getStr(start_str, len - 1));
		
        // 공백 제거까지해서 반환
        return answer.toString().trim();
	}

}