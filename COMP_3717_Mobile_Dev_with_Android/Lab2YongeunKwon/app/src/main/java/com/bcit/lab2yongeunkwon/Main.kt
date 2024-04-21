package com.bcit.lab2yongeunkwon
/*
    Yongeun Kwon
    A01263922
 */
fun main(){
    val words = arrayOf("The", "Quick", "Brown", "Fox", "Went", "Over", "The", "Lazy", "Dog")

    // The first line should display words in an array/list
    println(words.contentToString())

    // The second line should display the character length for each word in an array
    val wordLengths = mutableListOf<Int>()
    for (word in words) {
        wordLengths.add(word.length)
    }
    println("[${wordLengths.joinToString(", ")}]")

    var maxLength = 0
    var index = 0
    while (index < wordLengths.size) {
        val word = wordLengths[index]
        if (word > maxLength) {
            maxLength = word
        }
        index++
    }

    var minLength = maxLength
    wordLengths.forEach { wordLength ->
        if (wordLength < minLength) {
            minLength = wordLength
        }
    }

    val longestWords = mutableListOf<String>()
    val shortestWords = mutableListOf<String>()
    val processedWords = mutableSetOf<String>()

    for (word in words) {
        if (processedWords.contains(word)) {
            continue //duplicate word prevent
        }

        if (word.length == maxLength) {
            longestWords.add(word)
        }

        if (word.length == minLength) {
            shortestWords.add(word)
        }


        processedWords.add(word)
    }

    println("Longest word(s): $longestWords")
    println("Shortest word(s): $shortestWords")
}