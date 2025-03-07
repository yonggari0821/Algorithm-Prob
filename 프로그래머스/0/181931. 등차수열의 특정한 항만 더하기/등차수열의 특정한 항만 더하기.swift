import Foundation

func solution(_ a:Int, _ d:Int, _ included:[Bool]) -> Int {
    var res : Int = 0;
    for (idx, val) in included.enumerated() {
        if (val) {res += (a + d * idx)};
    }
    return res
}