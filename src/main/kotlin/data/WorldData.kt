package technology.zim.data

//Data structure wrapper for a set of tiles
//Todo: Test converting to a single row-major array, multiplying the y value by row size to find the element position

@JvmInline
value class WorldData(val value: ArrayList<ArrayList<TileProperties>>) {
    fun setSize(xmin : Int, ymin : Int) {
        val emptyTile = TileProperties(0)
        //println("WorldData setting to: " + xmin + ", " + ymin)
        //Fill every column with an ArrayList of TileProperties
        value.removeAll {true}

        for(i in 0..xmin-1) {
            value.add(ArrayList<TileProperties>())
        }

        //Fill every row with TileProperties
        for(x in value) {
            for(i in 0..ymin-1) {
                x.add(emptyTile)
            }
        }

        //println("WorldData now sized at: " + value.size + ", " + value[0].size)
    }
}
