package technology.zim.data

//Translated code from CS222 MaxHeap homework
//Cannot use index 0 due to Integer limitations
class TileHeap(val end: Tile, val fValue:(Tile, Tile) -> Int) {
   val dat = ArrayList<Tile>()
    init {
        //Shove some data into the zero slot
        if(dat.isEmpty())
            dat.add(Tile(0, 0))
    }

    fun popMin(): Tile {
        if(dat.size < 2) {
            throw ArrayIndexOutOfBoundsException()
        }

        val ret = dat[1]
        dat[1] = dat[dat.size - 1]
        dat.removeLast()
        siftDown(1)

        return ret
    }

    fun insert(value: Tile) {
        dat.add(value)
        siftUp(dat.size - 1)
    }

    private fun siftUp(index: Int)  {
        if(dat.isEmpty())
            throw ArrayIndexOutOfBoundsException()

        if(fValue(dat[index], end) < fValue(dat[parent(index)], end) && index > 1) {
            swap(index, parent(index))
            siftUp(parent(index))
        }
    }

    private fun siftDown(index: Int) {
        if(!isLeaf(index)) {
            var minValueIndex = index

            val l = leftChild(index)
            if(l < dat.size && fValue(dat[l], end) < fValue(dat[minValueIndex], end) ) {
                minValueIndex = l
            }

            val r = rightChild(index)
            if(r < dat.size && fValue(dat[r], end) < fValue(dat[minValueIndex], end) ) {
                minValueIndex = r
            }

            if(minValueIndex != index) {
                swap(index, minValueIndex)
                siftDown(minValueIndex)
            }
        }
    }

    //Helper functions for navigating the heap
    private fun parent(index: Int): Int = index / 2
    private fun leftChild(index: Int): Int = index * 2
    private fun rightChild(index: Int): Int = (index * 2) + 1

    //Instead of allocating the memory every time, do it once.
    var index1tmp:Tile = Tile(0, 0)
    private fun swap(index1: Int, index2: Int) {
        index1tmp = dat[index1]
        dat[index1] = dat[index2]
        dat[index2] = index1tmp
    }

    private fun isLeaf(index: Int): Boolean {
        return index > (dat.size / 2)
    }

}