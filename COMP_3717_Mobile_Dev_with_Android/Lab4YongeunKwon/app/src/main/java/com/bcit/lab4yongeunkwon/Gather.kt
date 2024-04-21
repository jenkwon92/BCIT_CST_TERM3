package com.bcit.lab4yongeunkwon

class Gather(
    minion: Minion
) : Mission(minion), Repeatable {
    override fun determineMissionTime(): Int {
        return (minion.backpackSize + minion.baseSpeed) * (1..4).random()
    }
    override fun reward(amount: Int): String {
        return when (amount){
            in 10..21 -> "bronze"
            in 22..33 -> "silver"
            in 34..50 -> "gold"
            else -> "nothing"
        }
    }

    override fun repeat(times: Int, listener: MissionListener) {
        repeat(times) {
            start(listener)
        }
    }
}