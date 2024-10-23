package technology.zim.util

import kotlin.random.Random

class TrimmableArrayList<E> {
    val data = ArrayList<E>()

    fun addAll(c: Collection<E>) {
        data.addAll(c)
    }

    fun isNotEmpty(): Boolean {
        return data.isNotEmpty()
    }

    fun isEmpty(): Boolean {
        return data.isEmpty()
    }

    fun add(item: E) {
        data.add(item)

    }

    fun size():Int {
        return data.size
    }

    fun clear() {
        data.clear()
    }

    fun removeAt(index: Int) {
        data[index] = data[data.size - 1]
        data.removeLast()
    }

    //O(n) remove operation
    fun remove(item: E) {
        data[data.indexOf(item)] = data[data.size - 1]
        data.removeLast()
    }


    fun random(): E {
        return data[Random.nextInt(data.size)]
    }

}