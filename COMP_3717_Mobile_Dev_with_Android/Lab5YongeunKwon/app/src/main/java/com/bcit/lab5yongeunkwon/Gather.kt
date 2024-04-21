package com.bcit.lab5yongeunkwon

class Gather(
    minion: Minion,
    item: Item?
) : Mission(minion, item) {
    override fun determineMissionTime(): Int {
        return (minion.backpackSize + minion.baseSpeed +  (item?.timeModifier ?: 0)) * (1..4).random()
    }
    override fun reward(time: Int): String {
        return when (time){
            in 10..21 -> "bronze"
            in 22..33 -> "silver"
            in 34..44 -> "gold"
            in 45..60 -> "diamond"
            else -> "nothing"
        }
    }
}