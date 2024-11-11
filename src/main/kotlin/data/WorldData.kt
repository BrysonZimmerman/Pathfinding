package technology.zim.data

//Data structure wrapper for a set of tiles
//Todo: Test converting to a single row-major array, multiplying the y value by row size to find the element position

@JvmInline
value class WorldData(val value: ArrayList<ArrayList<TileProperties>>) {

    //Accepts a list of directions, removes those directions from every TileProperties in WorldData
    fun scrubDirections(rem: List<Directions>) {
        var mask = rem.fold(0) { sum, element -> sum + element.dir}
        mask = mask.inv()
        value.forEachIndexed {
            x, row ->
            row.forEachIndexed {
                y, tile ->
                value[x][y] = TileProperties(tile.connections and(mask))
            }
        }
    }

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
