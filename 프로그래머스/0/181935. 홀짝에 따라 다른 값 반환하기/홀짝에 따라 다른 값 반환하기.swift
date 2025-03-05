import Foundation

func solution(_ n:Int) -> Int {
    var output = 0
    var vn = n
    
    while(vn > 0) {
        output += (n & 1 == 0 ? (vn * vn) : vn)
        vn -= 2
    }
    return output
}