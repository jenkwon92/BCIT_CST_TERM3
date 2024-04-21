package com.bcit.lab4yongeunkwon

abstract class Mission (
    protected val minion: Minion
) {
    fun start(listener: MissionListener) {
        listener.missionStart(minion)
        listener.missionProgress()
        val time = determineMissionTime()
        val reward = reward(time)

        listener.missionComplete(minion, reward)
    }
    protected abstract fun determineMissionTime(): Int
    protected abstract fun reward(amount: Int): String

}