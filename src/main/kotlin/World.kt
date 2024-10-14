package technology.zim

//For now, keep it small with uncompresed tile representation
//In the future, this could be stored in a gzipped file and memory mapped


//Location in array is the tile's coordinates
//Value of int is barriers, bitwise operation

class World(val tiles: ArrayList<ArrayList<Walls>> ) {
    //TODO: Implement world generation algorithm
    fun generate(seed: Int) {

    }

    //Determine whether moving from one tile to another is valid
    //Should limit to adjacent tiles
    //Should detect walls and refuse movement if one exists in the desired direction
    //Or maybe this should be delegated to another class
    fun canPass(x: Int, y: Int, fromX: Int, fromY: Int) {

    }
}