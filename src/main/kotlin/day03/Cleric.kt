package day03

//object Const {}


class Cleric(
    var name: String,
    var hp: Int = MAX_HP,
    var mp: Int = MAX_MP,
) {

    companion object {
        const val MAX_HP: Int = 50
        const val MAX_MP: Int = 10
    }

    fun selfAid() {
        if (mp < 5) {
            println("mp가 부족합니다")
        } else if (hp == MAX_HP) {
            println("hp가 최대치 입니다")
        } else {
            mp -= 5
            hp = MAX_HP      //mp -5 한번에 최대 hp까지 풀 충전 완료
            println("hp가 회복되었습니다")
        }
    }

    fun pray(time: Int): Int {
        val num = (0..2).random()
        var recoveryMp = time + num
        var currentMp = mp
        if (recoveryMp + currentMp > MAX_MP) {
            return MAX_MP - currentMp
        }
        return recoveryMp
    }
}

fun main() {
    var hero = Cleric("용사")
    hero.selfAid()
    hero.pray(3)
}