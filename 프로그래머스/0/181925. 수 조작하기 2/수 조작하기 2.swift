import Foundation

func solution(_ numLog:[Int]) -> String {
    var result : [Character] = []
    for i in 0 ..< numLog.count - 1 {
        let val = numLog[i + 1] - numLog[i]
        switch val {
            case 1 :
                result.append("w")
            case -1 :
                result.append("s")
            case 10 :
                result.append("d")
            case -10 :
                result.append("a")
            default:
                break
        }
    }
    return String(result)
}