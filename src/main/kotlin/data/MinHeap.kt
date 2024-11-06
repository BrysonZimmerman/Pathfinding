package technology.zim.data

//Translated code from CS222 MaxHeap homework
//Cannot use index 0 due to Integer limitations
class MinHeap() {
   val dat = ArrayList<Int>(2)

    init {
        dat.add(Int.MIN_VALUE)
    }

    fun popMin(): Int {
        if(dat.isEmpty()) {
            throw ArrayIndexOutOfBoundsException()
        }

        val ret = dat.first()
        dat[1] = dat[dat.size - 1]
        siftDown(1)

        return ret
    }

    fun insert(value: Int) {
        dat.add(value)
        siftUp(dat.size - 1)
    }

    private fun siftUp(index: kotlin.Int)  {
        if(dat.isEmpty())
            throw ArrayIndexOutOfBoundsException()

        if(dat[index] < dat[parent(index)] && index > 1) {
            swap(index, parent(index))
            siftUp(parent(index))
        }
    }

    private fun siftDown(index: Int) {
        if(!isLeaf(index)) {
            var maxIndex = index

            val l = leftChild(index)
            if(dat[l] < dat[index] && leftChild(index) <= dat.size) {
                maxIndex = l
            }

            val r = rightChild(index)
            if(dat[r] < dat[index] && rightChild(index) <= dat.size) {
                maxIndex = r
            }

            if(maxIndex != index) {
                swap(index, maxIndex)
                siftDown(maxIndex)
            }
        }
    }

    fun peekMax(): Int {
        if(dat.isEmpty()) {
            throw ArrayIndexOutOfBoundsException()
        }

        return dat.last()
    }

    fun peekMin(): Int {
        if(dat.isEmpty()) {
            throw ArrayIndexOutOfBoundsException()
        }
        return dat[1]
    }

    //Helper functions for navigating the heap
    private fun parent(index: kotlin.Int): kotlin.Int = index / 2
    private fun leftChild(index: kotlin.Int): kotlin.Int = index * 2
    private fun rightChild(index: kotlin.Int): kotlin.Int = (index * 2) - 1

    private fun swap(index1: kotlin.Int, index2: kotlin.Int) {
        val index1tmp = dat[index1]
        dat[index1] = dat[index2]
        dat[index2] = index1tmp
    }

    private fun isLeaf(index: Int): Boolean {
        return index > (dat.size / 2)
    }

}