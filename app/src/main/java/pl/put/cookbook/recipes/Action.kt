package pl.put.cookbook.recipes

class Action (private val text: String, private val seconds: Int? = null) {

    fun getText(): String {
        return this.text
    }

    fun getTimeInSeconds(): Int? {
        return this.seconds
    }
}