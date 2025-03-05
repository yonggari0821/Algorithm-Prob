import Foundation

func solution(_ str1:String, _ str2:String) -> String {
    var output : String = ""
    for ( s1, s2 ) in zip ( str1, str2 ) {
        output += String(s1) + String(s2)
    }
    return output
}