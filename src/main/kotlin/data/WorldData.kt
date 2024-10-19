package technology.zim.data

//Data structure wrapper for more easily readable code


@JvmInline
value class WorldData constructor(val data: ArrayList<ArrayList<TileProperties>>) {
    fun setSize(xmin : Int, ymin : Int) {
        with(data) {
            this.ensureCapacity(xmin)
            this.fill(ArrayList<TileProperties>())
        }

        for(y in data) {
            with(y) {
                this.ensureCapacity((ymin))
                this.fill(TileProperties())
            }
        }
    }
}
