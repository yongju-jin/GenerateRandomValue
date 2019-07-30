import kotlin.math.pow

fun main() {
    val maxNumber = 2048
    (0 ..  999999999999999).forEach { _ ->
        val randomValue = getRandomNumber2(maxNumber)
        println("randomValue: $randomValue")
        if (randomValue > maxNumber - 1) throw Exception("randomValue: $randomValue")
    }
}

fun getRandomNumber2(maxNumber: Int): Int {
    val digitCount = getBinaryDigits(maxNumber)
    val randomBinaryList = getRandomBinaryList(digitCount)

    val randomValue = getBinaryListToDecimal(randomBinaryList)
    return if (randomValue >= maxNumber) {
        randomValue - powOfTwo(randomBinaryList.size - 1)
    } else randomValue
}

tailrec fun getBinaryDigits(number: Int, acc: Int = 0): Int {
    if (number < 1) throw Exception("number must be bigger than 0: $number")

    return when {
        number < 2 -> acc + 1
        else -> getBinaryDigits(number / 2, acc + 1)
    }
}

fun getRandomBinaryList(digitCount: Int): List<Int> {
    if (digitCount < 0) throw Exception("digitCount must be bigger than -1: $digitCount")
    if (digitCount == 0) emptyList<Int>()

    val retList = mutableListOf<Int>()
    for (i in 0 until digitCount) {
        retList.add(getZeroOrOne())
    }

    return retList
//    return List(digitCount) {
//        getZeroOrOne()
//    }
}

fun getBinaryListToDecimal(binaryList: List<Int>): Int {
    return if (binaryList.isEmpty()) 0
    else {
        var acc = 0
        for (i in 0 until binaryList.size) {
            val value = binaryList[i]
            if (value > 1)throw Exception("binaryList could not contain numbers bigger than 1: $value")
            acc += powOfTwo(i) * value
        }
        acc
    }
//    else binaryList.foldIndexed(0) { index, acc, value ->
//        if (value > 1) throw Exception("binaryList could not contain numbers bigger than 1: $value")
//        acc + (2.toDouble().pow(index) * value).toInt()
//    }
}

fun powOfTwo(value: Int): Int {
    return when {
        value < 0 -> throw Exception("value is must be bigger than 0: $value")
        value == 0 -> 1
        value == 1 -> 2
        else -> 2 shl (value - 1)
    }
//    return (2 shl value)
//    2.toDouble().pow(value).toInt()
}
