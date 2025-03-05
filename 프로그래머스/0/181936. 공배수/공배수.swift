import Foundation

func solution(_ number:Int, _ n:Int, _ m:Int) -> Int {
    if (number % lcm(n, m) == 0) {return 1}
    else {return 0}
}

func gcd (_ a: Int, _ b: Int) -> Int {
    if (b == 0) {return a}
    return gcd(b, a % b)
}

func lcm (_ a: Int, _ b: Int) -> Int {
    return a * b / gcd(a, b)
}