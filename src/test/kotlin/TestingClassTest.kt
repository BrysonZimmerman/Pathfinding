import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import technology.zim.TestingClass

class TestingClassTest {

    @Test
    fun basic() {
        val test = TestingClass()
        val res = test.basic()
        assertEquals(true, res)
    }
}