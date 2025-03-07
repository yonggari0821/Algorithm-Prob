import Foundation

func solution(_ a:Int, _ b:Int, _ c:Int) -> Int {
    var diceSet : Set<Int> = [a, b, c]
    switch (diceSet.count) {
        case 3 :
            return a + b + c
        case 2 :
            return (a*a + b*b + c*c) * (a + b + c)
        case 1 :
            return (a*a*a + b*b*b + c*c*c) * (a*a + b*b + c*c) * (a + b + c)
        default :
            return 0
    }
}