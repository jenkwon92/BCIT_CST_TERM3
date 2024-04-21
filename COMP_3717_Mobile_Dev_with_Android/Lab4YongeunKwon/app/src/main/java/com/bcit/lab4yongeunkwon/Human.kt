package com.bcit.lab4yongeunkwon

class Human: Minion() {
    override val race: String
        get() = "Human"

    override val baseHealth: Int
        get()  = 5

    override val baseSpeed: Int
        get() = 5

    override val backpackSize: Int
        get()  = 5

    override val catchphrase: String
        get() =  ""

}