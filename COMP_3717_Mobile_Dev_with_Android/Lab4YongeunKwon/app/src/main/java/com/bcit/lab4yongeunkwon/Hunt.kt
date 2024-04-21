package com.bcit.lab4yongeunkwon

class Hunt(
    minion: Minion
) : Mission(minion), Repeatable {
    override fun determineMissionTime(): Int {
        return (minion.baseHealth  +  minion.baseSpeed)  *  (0..4).random()
    }

    override fun reward(amount: Int): String {
        return when(amount) {
            in 11..20 -> "mouse"
            in 21..30 -> "fox"
            in 31..50 -> "buffalo"
            else -> "nothing"
        }
    }

    override fun repeat(times: Int, listener: MissionListener) {
        repeat(times) {
            start(listener)
        }
    }
}