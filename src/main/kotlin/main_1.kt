fun main() {
    val maxNumber = 2048
    (0 ..  999999999999999).forEach { _ ->
        val randomValue = getRandomNumber(maxNumber)
        println("randomValue: $randomValue")
        if (randomValue > maxNumber - 1) throw Exception("randomValue: $randomValue")
    }
}

/*
실행: get_random(5)
결과: 0 or 1 or 2 or 3 or 4

실행: get_random(3)
결과: 0 or 1 or 2
 */
fun getRandomNumber(maxNumber: Int): Int {
    if (maxNumber < 1) throw Exception("maxNumber must be bigger than 0 : $maxNumber")
    if (maxNumber == 1) return 0

    var rest = maxNumber
    var accRandomValue = getZeroOrOne()
    var timesValue = 1

    while (true) {
        rest /= 2
        timesValue *= 2
        val nextRandomValue = timesValue * getZeroOrOne()
        if (rest > 1) {
            accRandomValue += nextRandomValue
        } else {
            if (maxNumber > accRandomValue + nextRandomValue) accRandomValue += nextRandomValue
            break
        }
    }
    return accRandomValue
}

