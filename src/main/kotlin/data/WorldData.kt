package technology.zim.data

//Data structure wrapper for a set of tiles


@JvmInline
value class WorldData(val data: ArrayList<ArrayList<TileProperties>>) {
    fun setSize(xmin : Int, ymin : Int) {
        //Fill every column with an ArrayList of TileProperties
        with(data) {
            this.ensureCapacity(xmin)
            this.fill(ArrayList<TileProperties>())
        }

        //Fill every row with TileProperties
        for(y in data) {
            with(y) {
                this.ensureCapacity((ymin))
                this.fill(TileProperties())
            }
        }
    }
}
