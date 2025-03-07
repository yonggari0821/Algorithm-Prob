import Foundation

var mode = 0

func solution(_ code: String) -> String {
    let codeArray = Array(code)  // O(n)
    var retArray: [Character] = []
    
    
    for i in 0..<codeArray.count {  // Int 인덱스 직접 사용
        let char = codeArray[i]
        if mode == 0 {
            if char != "1" && i & 1 == 0 {
                retArray.append(char)
            } else if char == "1" {
                mode = toggle01(mode)
            }
        } else {
            if char != "1" && i & 1 == 1 {
                retArray.append(char)
            } else if char == "1" {
                mode = toggle01(mode)
            }
        }
    }
    return retArray.isEmpty ? "EMPTY" : String(retArray)
}

// 0이면 1, 1이면 0을 반환
func toggle01 (_ n: Int) -> Int {
    return n ^ 1
}