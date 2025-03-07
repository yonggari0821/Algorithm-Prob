import Foundation

func solution(_ a: Int, _ b: Int, _ c: Int) -> Int {
    let count = Set([a, b, c]).count
    var score = a + b + c
    
    func squareSum() -> Int { a * a + b * b + c * c }
    func cubeSum() -> Int { a * a * a + b * b * b + c * c * c }
    
    if count <= 2 {
        score *= squareSum()
    }
    if count <= 1 {
        score *= cubeSum()
    }
    
    return score
}