package com.bcit.lab4yongeunkwon

import com.bcit.lab4yongeunkwon.Repeatable
import kotlin.random.Random

/*
    Yongeun Kwon
    A01263922
 */
fun main(){
    // 1. pass the missionListener into start()
    val dwarf = Dwarf()
    val gather = Gather(dwarf)

    gather.start(object: MissionListener{
        override fun missionStart(minion: Minion) {
            println("""
                ${minion.catchphrase}
                
                A ${minion.race} has sent off to gather some resources!
            """.trimIndent())
        }

        override fun missionProgress() {
            println("""
                ...
                ...
                ...
            """.trimIndent())
        }

        override fun missionComplete(minion: Minion, reward: String) {
            println("A ${minion.race} has returned from gathering, and found $reward!\n")
        }
    })

    val elf = Elf()
    val hunt = Hunt(elf)

    hunt.start(object: MissionListener{
        override fun missionStart(minion: Minion) {
            println("""
                ${minion.catchphrase}
                
                An ${minion.race} started a new hunt!
            """.trimIndent())
        }

        override fun missionProgress() {
            println("""
                ...
                ...
                ...
            """.trimIndent())
        }

        override fun missionComplete(minion: Minion, reward: String) {
            println("An ${minion.race} has returned from a hunt, and found a $reward!\n")
        }
    })

    // 2. pass the missionListener into repeat()
    val human = Human()

    val randomNum = listOf(1, 2).random()
    val missionType = if(randomNum == 1){
        "hunt"
    }else{
        "gather"
    }
    val mission = if (randomNum == 1) {
        Hunt(human)
    } else {
        Gather(human)
    }

    mission.repeat(5, object : MissionListener {
        override fun missionStart(minion: Minion) {
            println("""
                ${minion.catchphrase}
                
                A ${minion.race} has sent off to $missionType some resources!
            """.trimIndent())
        }

        override fun missionProgress() {
            println("""
                ...
                ...
                ...
            """.trimIndent())
        }

        override fun missionComplete(minion: Minion, reward: String) {
            println("A ${minion.race} has returned from $missionType, and found $reward!\n")
        }
    })
}