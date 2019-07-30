import kotlin.math.pow

private fun getZeroOrOne() = (0..1).random()

//inline fun Int.count(predicate: (Int) -> Boolean): Int {
//    var count = 0
//    var rest = this
//    while(true) {
//        println("[$this] count: $count, rest: $rest")
//        if (predicate(rest)) return count
//        count ++
//    }
//}

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

fun getRandomNumber2(maxNumber: Int) {
    val digitCount = getDigits(maxNumber)
    getBinaryList(maxNumber, digitCount)
}

tailrec fun getDigits(number: Int, acc: Int = 0): Int {
    return when {
        number < 2 -> acc + 1
        else -> getDigits(number / 2, acc + 1)
    }
}

fun getBinaryList(maxNumber: Int, digitCount: Int): Int {
    val tempList = List(digitCount - 1) {
        getZeroOrOne()
    }

    println("tempList: $tempList")
    println(
        getBinaryListToDecimal(tempList)
    )

    return 0
}

fun getBinaryListToDecimal(binaryList: List<Int>): Int {
    return binaryList.foldIndexed(0) { index, acc, value ->
        println("index $index, value: $value, acc: $acc")
        acc + (2.toDouble().pow(index) * value).toInt()
    }
}