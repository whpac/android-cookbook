package pl.put.cookbook.recipes

class Ingredient (private val name: String, private val quantity: Double, private val unit: MeasureUnit) {

    fun getName(): String {
        return this.name
    }

    fun getQuantity(): Double {
        return this.quantity
    }

    fun getUnit(): MeasureUnit {
        return this.unit
    }
}