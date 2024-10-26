import org.junit.jupiter.api.Test

class CoordPackTest {

    @Test
    fun packUnpack() {
        val xOrig = -0x1
        val yOrig = 0x1

        val coords = pack(xOrig, yOrig)

        val xNew = x(coords)
        val yNew = y(coords)

        println("done")

        assert(xOrig == xNew)
        assert(yOrig == yNew)


    }

    fun pack(mostSignificantBits: Int, leastSignificantBits: Int): ULong {
        return (mostSignificantBits.toUInt().toULong() shl 32) or leastSignificantBits.toUInt().toULong()
    }

    fun x(coords: ULong): Int {
        return (coords shr 32).toLong().toInt()
    }
    fun y(coords: ULong): Int {
        return coords.toLong().toInt()
    }


}