package sandelius

class BitGenerator(
        private val source: ByteArray
) : Generator<Boolean> {
    init {
        if (source.isEmpty())
            throw IllegalArgumentException()
    }

    private var index = 0
    private var temp = source[0].toInt()
    private var bitNumber: Byte = 0

    override operator fun next(): Boolean {
        if (bitNumber >= 8) {
            index = (index + 1) % source.size
            temp = source[index].toInt()
            bitNumber = 0
        }
        val bit = temp and 1
        val result = bit > 0
        temp = (temp - bit) / 2
        bitNumber++
        return result
    }
}
