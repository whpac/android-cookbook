package pl.put.cookbook

class Recipe private constructor(private var name: String, private var recipe: String) {

    fun getRecipe(): String {
        return recipe
    }

    fun getName(): String {
        return name
    }

    override fun toString(): String {
        return name
    }

    companion object {
        val recipes = arrayOf(
            Recipe("Schabowy", "Idź do sklepu i kup sobie schab"),
            Recipe("Bigos", "Odgrzej bigos z zeszłego tygodnia")
        )
    }

}