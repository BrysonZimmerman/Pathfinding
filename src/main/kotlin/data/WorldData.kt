package technology.zim.data

//Data structure wrapper for more easily readable code


@JvmInline
value class WorldData constructor(val data: ArrayList<ArrayList<TileProperties>>) {
    constructor(xmin : Int, ymin : Int) : this(ArrayList<ArrayList<TileProperties>>()) {
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
