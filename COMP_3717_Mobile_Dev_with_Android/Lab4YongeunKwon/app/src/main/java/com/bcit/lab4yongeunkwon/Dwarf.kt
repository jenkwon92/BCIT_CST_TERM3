package com.bcit.lab4yongeunkwon

class Dwarf: Minion() {
    override val race: String
        get() = "Dwarf"

    override val baseHealth: Int
        get()  = 8

    override val baseSpeed: Int
        get() = 2

    override val backpackSize: Int
        get()  = 8

    override val catchphrase: String
        get() =  "Wheres' me trusty ol hammer?"
}