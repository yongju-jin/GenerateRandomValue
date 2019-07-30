import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.ExpectedException

class Main_2KtTest {

    @Rule
    @JvmField
    val expectedException: ExpectedException = ExpectedException.none()

    @Test
    fun test_getBinaryDigits_exception() {
        expectedException.expect(Exception::class.java)
        expectedException.expectMessage("number must be bigger than 0: 0")
        getBinaryDigits(0)
    }

    @Test
    fun test_getBinaryDigits() {
        val one = getBinaryDigits(1)
        assertEquals(1, one)

        val ten = getBinaryDigits(513)
        assertEquals(10, ten)

        val eleven = getBinaryDigits(1024)
        assertEquals(11, eleven)
    }

    @Test
    fun test_getRandomBinaryList_exception() {
        expectedException.expect(Exception::class.java)
        expectedException.expectMessage("digitCount must be bigger than -1: -1")
        getRandomBinaryList(-1)
    }

    @Test
    fun test_getRandomBinaryList() {
        (0 .. 10).forEach {
            val randomValue = (0 .. 99999).random()
            val randomBinaryList = getRandomBinaryList(randomValue)
            assertEquals(randomValue, randomBinaryList.size)
        }
    }

    @Test
    fun test_getBinaryListToDecimal_exception() {
        expectedException.expect(Exception::class.java)
        expectedException.expectMessage("binaryList could not contain numbers bigger than 1: 53")
        getBinaryListToDecimal(listOf(1, 0, 1, 1, 1, 53))
    }

    @Test
    fun test_getBinaryListToDecimal() {
        val zero = getBinaryListToDecimal(emptyList())
        assertEquals(0, zero)

        val three = getBinaryListToDecimal(listOf(1, 1, 0))
        assertEquals(3, three)

        val value_173 = getBinaryListToDecimal(listOf(1, 0, 1, 1, 0, 1, 0, 1))
        assertEquals(173, value_173)

        val value_76469 = getBinaryListToDecimal(listOf(1,0,1,0,1,1,0,1,0,1,0,1,0,1,0,0,1))
        assertEquals(76469, value_76469)
    }

    @Test
    fun test_powOfTwo_exception() {
        expectedException.expect(Exception::class.java)
        expectedException.expectMessage("value is must be bigger than 0: -53")
        powOfTwo(-53)
    }

    @Test
    fun test_powOfTwo() {
        val one = powOfTwo(0)
        assertEquals(1, one)

        val two = powOfTwo(1)
        assertEquals(2, two)

        val four = powOfTwo(2)
        assertEquals(4, four)

        val val_1024 = powOfTwo(10)
        assertEquals(1024, val_1024)

        val val_512 = powOfTwo(9)
        assertEquals(512, val_512)
    }
}
