package technology.zim.data

class Heap<T> {
    private val dat = ArrayList<T>()

    fun popMin(): T {
        if(dat.isEmpty()) {
            throw ArrayIndexOutOfBoundsException()
        }

        val ret = dat.first()

        //TODO: Sift and such
        return dat.first()
    }

    fun popMax(): T {
        if(dat.isEmpty()) {
            throw ArrayIndexOutOfBoundsException()
        }

        val ret = dat.last()

        return ret
    }

    fun insert(value: T) {
        dat.add(value)
        //TODO: siftUp, siftDown

    }

    private fun siftUp() {

    }

    private fun siftDown() {

    }

    private fun delete() {

    }

    fun peekMax(): T {
        if(dat.isEmpty()) {
            throw ArrayIndexOutOfBoundsException()
        }

        return dat.last()
    }

    fun peekMin(): T {
        if(dat.isEmpty()) {
            throw ArrayIndexOutOfBoundsException()
        }
        return dat[0]
    }

}