import Foundation

func solution(_ my_string:String, _ overwrite_string:String, _ s:Int) -> String {
    var output = my_string
    
    output.replaceSubrange(
        output.index(output.startIndex, offsetBy: s) 
        ... 
        output.index(output.startIndex, offsetBy: s + overwrite_string.count - 1)
        , with: overwrite_string
    )
    
    return output
}