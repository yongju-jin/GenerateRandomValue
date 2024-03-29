import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val runCount = 10000
        val maxNumber = 5

        val resultList = MutableList(maxNumber) { 0 }
        println("before resultList: $resultList")
        val async = async {
            repeat(runCount) {
                launch {
                    val randomValue = getRandomNumberByResetInvalidNumber(maxNumber)
//                    println("randomValue: $randomValue")
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
        println("after resultList: ${resultList.map { it.toFloat() / runCount }}")
    }
}

/*
실행: get_random(5)
결과: 0 or 1 or 2 or 3 or 4

실행: get_random(3)
결과: 0 or 1 or 2
 */
fun getRandomNumberByResetInvalidNumber(maxNumber: Int): Int {
    if (maxNumber < 1) throw Exception("maxNumber must be bigger than 0 : $maxNumber")
    if (maxNumber == 1) return 0

    var rest = maxNumber // 나머지 값
    var accRandomValue = getZeroOrOne() // 랜덤 값
    var timesValue = 1 // 곱하기를 위한 값

    while (true) {
        rest /= 2
        timesValue *= 2
        val nextRandomValue = timesValue * getZeroOrOne()

        if (rest > 1) {
            accRandomValue += nextRandomValue
        } else {
            if (maxNumber > accRandomValue + nextRandomValue) {
                accRandomValue += nextRandomValue
                break
            } else {
                rest = maxNumber
                accRandomValue = getZeroOrOne()
                timesValue = 1
            }
        }
    }

    return accRandomValue
}

fun getRandomNumber(maxNumber: Int): Int {
    if (maxNumber < 1) throw Exception("maxNumber must be bigger than 0 : $maxNumber")
    if (maxNumber == 1) return 0

    var rest = maxNumber // 나머지 값
    var accRandomValue = getZeroOrOne() // 랜덤 값
    var timesValue = 1 // 곱하기를 위한 값

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
