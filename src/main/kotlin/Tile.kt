package technology.zim

//Data holder to encapsulate a particular location
//Might not be necessary, might add unnecessary memory usage

//Tile could use a simple int or long to combine both coordinates
//Retrieve X or Y with bitwise operations
//Removes boxing while allowing access. Still doesn't add walls.

@JvmInline
value class Tile(val coords: Int) {

}
