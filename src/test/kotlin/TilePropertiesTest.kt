import technology.zim.data.Directions.*
import technology.zim.data.TileProperties
import kotlin.test.Test

class TilePropertiesTest {

    @Test
    fun addConnectionsTest() {
        val tileProperties = TileProperties(0)
        var result = tileProperties + UP

        assert(result.connections and(UP.dir) == UP.dir)
        assert(result.connections and(DOWN.dir) == 0)

        result += LEFT

        assert(result.connections and(UP.dir) == UP.dir)
        assert(result.connections and(LEFT.dir) == LEFT.dir)
        assert(result.connections and(RIGHT.dir) == 0)
        assert(result.connections and(DOWN.dir) == 0)

        val after = result + UP

        assert(result == after)
    }

    @Test
    fun removeConnectionsTest() {
        val props = TileProperties(UP.dir + LEFT.dir + RIGHT.dir + DOWN.dir)
        var result = props - UP

        assert(result.connections and (UP.dir) == 0)
        assert(result.connections and (LEFT.dir) == LEFT.dir)
        assert(result.connections and (RIGHT.dir) == RIGHT.dir)
        assert(result.connections and (DOWN.dir) == DOWN.dir)

        val after = result - UP
        assert(result == after)
    }

}