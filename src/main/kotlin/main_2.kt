import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val runCount = 10000
        val maxNumber = 5
        val resultList = MutableList(maxNumber) { 0 }
        println("before resultMap: $resultList")
        val async = async {
            repeat(runCount) {
                launch {
                    val randomValue = getRandomNumber2ByResetInvalidNumber(maxNumber)
//                    println("[$it] randomValue: $randomValue")
                    resultList[randomValue]++
                    if (randomValue > maxNumber - 1) throw Exception("randomValue: $randomValue")
                }
            }
        }

        async.await()
        //여러 coroutine에서 한 개의 자원(resultList)에 접근하지만 값이 정상적으로 나옴. 왜?? 우연??
        println("execute run count: ${resultList.fold(0) { acc, next -> 
            acc + next
        }}")
        println("after resultList: ${resultList.map {
            it.toFloat() / runCount
        }}")
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

fun getRandomNumber2ByResetInvalidNumber(maxNumber: Int): Int {
    while(true) {
        val randomValue = getBinaryListToDecimal(
            getRandomBinaryList(
                getBinaryDigits(maxNumber)
            )
        )

        if (randomValue < maxNumber) return randomValue
    }
}

// 2진수 자릿수 구하기
tailrec fun getBinaryDigits(number: Int, acc: Int = 0): Int {
    if (number < 1) throw Exception("number must be bigger than 0: $number")

    return when {
        number < 2 -> acc + 1
        else -> getBinaryDigits(number / 2, acc + 1)
    }
}

fun getRandomBinaryList(digitCount: Int): List<Int> {
    if (digitCount < 0) throw Exception("digitCount must be bigger than -1: $digitCount")
    if (digitCount == 0) return emptyList()

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
        for (i in binaryList.indices) {
            val value = binaryList[i]
            if (value > 1) throw Exception("binaryList could not contain numbers bigger than 1: $value")
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
