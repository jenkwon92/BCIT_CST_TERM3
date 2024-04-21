package com.bcit.lab5yongeunkwon

class Elf: Minion, Companion {
    override val race: String
        get() = "Elf"

    override val baseHealth: Int
        get()  = 2

    override val baseSpeed: Int
        get() = 8

    override val backpackSize: Int
        get()  = 3

    override val catchphrase: String
        get() =  "My arrows never miss!"

    override fun huntReward(int: Int): String {
        return when (int){
            in 11..20 -> "fish"
            in 21..30  -> "forest bear"
            in 31..40  -> "orc"
            in 41..60  -> "troll"
            else  -> "nothing"
        }
    }
}