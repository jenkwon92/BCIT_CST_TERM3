package com.bcit.lab5yongeunkwon

class Orc(minion: Minion) : Minion by minion {

    override val race: String
        get() = "Orc"

    override val catchphrase: String
        get() =  "ARRGH!"

}