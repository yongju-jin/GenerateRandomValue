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

fun main(args: Array<String>) {
//    (0 .. 100).forEach {
//        println(getZeroOrOne())
//    }
//    (0 .. 10).forEach {
//        println(getDigit(it))
//    }
    println(
        getRandomNumber(1000)
    )
}
/*
실행: get_random(5)
결과: 0 or 1 or 2 or 3 or 4

실행: get_random(3)
결과: 0 or 1 or 2
 */
fun getRandomNumber(maxNumber: Int): Int {
    if (maxNumber < 0) throw Exception("maxNumber must be positive: $maxNumber")
    if (maxNumber == 0) return 0

    var rest = maxNumber
    var randomValue = getZeroOrOne()
    var count = 1
    while (rest > 1) {
        rest /= 2
        randomValue += (getTwoPower(count) * getZeroOrOne())
        count ++
    }
    println("randomValue: $randomValue")
    return randomValue
}

fun getTwoPower(x: Int): Int {
    var result = 1
    for (x in 1 .. x) {
         result *= 2
    }

    return result
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