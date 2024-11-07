package technology.zim.data

import java.util.HashMap
import kotlin.math.abs

//Translated code from CS222 MaxHeap homework
//Cannot use index 0 due to Integer limitations
//TODO: Consider better options than passing in the information this thing needs
class TileHeap(val end: Tile, val gVals: HashMap<Tile, Int>) {
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

        if(fValue(dat[index]) < fValue(dat[parent(index)]) && index > 1) {
            swap(index, parent(index))
            siftUp(parent(index))
        }
    }

    private fun siftDown(index: Int) {
        if(!isLeaf(index)) {
            var minValueIndex = index

            val l = leftChild(index)
            if(l < dat.size && fValue(dat[l]) < fValue(dat[minValueIndex]) ) {
                minValueIndex = l
            }

            val r = rightChild(index)
            if(r < dat.size && fValue(dat[r]) < fValue(dat[minValueIndex])) {
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
    private fun parent(index: Int): Int = index / 2
    private fun leftChild(index: Int): Int = index * 2
    private fun rightChild(index: Int): Int = (index * 2) + 1

    private fun swap(index1: Int, index2: Int) {
        val index1tmp = dat[index1]
        dat[index1] = dat[index2]
        dat[index2] = index1tmp
    }

    private fun isLeaf(index: Int): Boolean {
        return index > (dat.size / 2)
    }

    private fun fValue(prospect: Tile): Int {
        return hValue(prospect).plus(gVals.get(prospect) ?: 0)
    }

    private fun hValue(prospect: Tile): Int {
        return abs(prospect.x() - end.x()) + abs(prospect.y() - end.y())
    }

}