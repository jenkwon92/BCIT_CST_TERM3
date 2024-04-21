package com.bcit.lab5yongeunkwon

abstract class Mission (
    protected val minion: Minion,
    protected val item: Item? = null,
    protected val companion: Companion ? = null
) {
    fun start(listener: MissionListener) {
        listener.missionStart(minion)
        listener.missionProgress()
        val time = determineMissionTime()
        val reward = reward(time)

        listener.missionComplete(minion, reward)
    }
    protected abstract fun determineMissionTime(): Int
    protected abstract fun reward(time: Int): String
}