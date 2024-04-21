package com.bcit.lab5yongeunkwon

interface Repeatable {
    var repeatNum: Int
    fun repeat(int: Int, listener: MissionListener)
}