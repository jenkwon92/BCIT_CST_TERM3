package com.bcit.lab5yongeunkwon

import kotlin.properties.Delegates

class Hunt(
    minion: Minion,
    item: Item?,
    companion: Companion?,
) : Mission(minion, item, companion), Repeatable {

    override fun determineMissionTime(): Int {
        return (minion.baseHealth  +  minion.baseSpeed +  (item?.timeModifier ?: 0))  *  (0..4).random()
    }

    override fun reward(time: Int): String {
        return companion?.huntReward(time)
            ?: when (time) {
                in 11..20 -> "mouse"
                in 21..30 -> "fox"
                in 31..40 -> "buffalo"
                in 41..60 -> "dinosaur"
                else -> "nothing"
            }
    }

    override var repeatNum: Int by Delegates.vetoable(3) { _, _, newValue ->
        newValue <= 3
    }


    override fun repeat(int: Int, listener: MissionListener) {
        if(int > 3) {
            println("A minion cannot repeat a hunt more than 3 times! Repeating a hunt 3 times...\n")
        } else {
            println("Repeating a hunt $int times...")
        }

        repeatNum = int
        repeat(repeatNum) {
            start(listener)
        }
    }
}