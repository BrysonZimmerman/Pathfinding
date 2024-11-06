package technology.zim.data

import technology.zim.World
import kotlin.math.abs

//Translated code from CS222 MaxHeap homework
//Cannot use index 0 due to Integer limitations
class TileHeap(val end: Tile) {
   val dat = ArrayList<Tile>()
    init {
        //Shove some data into the zero slot
        if(dat.isEmpty())
            dat.add(Tile(0, 0))
    }

    fun popMin(): Tile {
            if(dat.isEmpty()) {
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

    private fun siftUp(index: kotlin.Int)  {
        if(dat.isEmpty())
            throw ArrayIndexOutOfBoundsException()

        if(hValue(dat[index]) < hValue(dat[parent(index)]) && index > 1) {
            swap(index, parent(index))
            siftUp(parent(index))
        }
    }

    private fun siftDown(index: Int) {
        if(!isLeaf(index)) {
            var minValueIndex = index

            val l = leftChild(index)
            if(l < dat.size && hValue(dat[l]) < hValue(dat[minValueIndex]) ) {
                minValueIndex = l
            }

            val r = rightChild(index)
            if(r < dat.size && hValue(dat[r]) < hValue(dat[minValueIndex])) {
                minValueIndex = r
            }

            if(minValueIndex != index) {
                swap(index, minValueIndex)
                siftDown(minValueIndex)
            }
        }
    }

    fun peekMax(): Tile {
        if(dat.isEmpty()) {
            throw ArrayIndexOutOfBoundsException()
        }

        return dat.last()
    }

    fun peekMin(): Tile {
        if(dat.isEmpty()) {
            throw ArrayIndexOutOfBoundsException()
        }
        return dat[1]
    }

    //Helper functions for navigating the heap
    private fun parent(index: kotlin.Int): kotlin.Int = index / 2
    private fun leftChild(index: kotlin.Int): kotlin.Int = index * 2
    private fun rightChild(index: kotlin.Int): kotlin.Int = (index * 2) + 1

    private fun swap(index1: kotlin.Int, index2: kotlin.Int) {
        val index1tmp = dat[index1]
        dat[index1] = dat[index2]
        dat[index2] = index1tmp
    }

    private fun isLeaf(index: Int): Boolean {
        return index > (dat.size / 2)
    }

    private fun hValue(prospect: Tile): Int {
        return abs(prospect.x() - end.x()) + abs(prospect.y() - end.y())
    }

}