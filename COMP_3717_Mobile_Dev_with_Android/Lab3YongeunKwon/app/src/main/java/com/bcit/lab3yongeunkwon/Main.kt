package com.bcit.lab3yongeunkwon

/*
    Yongeun Kwon
    A01263922
 */

val history = mutableMapOf(
    1492 to "Christoper Columbus discovers America",
    1601 to "William Shakespeare writes Hamlet",
    1632 to "Galileo discovered the acceleration of gravity on Earth to be 9.8m/s",
    1838 to "Roughly 9.46 trillion km, the light-year is first used as a measurement in astronomy",
    2020 to "Covid 19 Pandemic"
)

fun main(){
    // red box print
//    history.forEach {
//        println(it)
//    }
    history.forEach(::println)

    println()

    // Purple box print
    println(columbus(1492))
    shakespeare(1601)
    galileo(history[1632])
    astronomy(::result)
    pandemic()
}

// One of the lines must be displayed using an anonymous function
val columbus = fun (arg1: Int): String?{
    return history[arg1]
}

// One of the lines must be displayed using a lambda function
val shakespeare: (Int) -> Unit = {
    println(history[1601])
}

// One of the lines must be displayed using a function as an argument
val galileo = { result: String?->
    println("$result")
}

// One of the lines must be displayed using a function reference (:: operator)
fun astronomy(result: (Map<Int, String>) -> String?) {
    println(result)
}
fun result(map: Map<Int, String>): String? {
    return map[1838]
}

// One of the lines can be displayed anyway you want but must use a function somehow
val pandemic: () -> Unit = {
    println(history[2020])
}