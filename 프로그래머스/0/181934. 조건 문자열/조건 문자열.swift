import Foundation

func solution(_ ineq:String, _ eq:String, _ n:Int, _ m:Int) -> Int {
    var res = 0
    if (ineq == "<") {
        res += n < m ? 1 : 0
    } else {
        res += n > m ? 1 : 0
    }
    if (eq == "=") {
        res += n == m ? 1 : 0
    }
    return res
}