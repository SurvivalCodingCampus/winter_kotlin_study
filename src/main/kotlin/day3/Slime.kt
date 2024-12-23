package day3

import day6.Actor
import day6.HealthPoint

open class Slime(
    suffix: String,
    hp: Int = 50,
) : Actor(name = "Slime $suffix", HealthPoint(hp)) {

    var target: Actor? = null
    open fun attack() {
        if (target == null) return
        println("Basic attack to $target")
    }

}