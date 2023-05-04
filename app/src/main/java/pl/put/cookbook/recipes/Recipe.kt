package pl.put.cookbook.recipes

import pl.put.cookbook.R

class Recipe private constructor(
    private val name: String,
    private val imageId: Int,
    private val ingredients: Array<Ingredient>,
    private val actions: Array<Action>) {

    fun getRecipe(): String {
        val sb = StringBuilder()
        for(i in this.ingredients) {
            sb.append(i.getName())
            val unit = i.getUnit()

            if(unit.displayQuantity){
                sb.append(": ")
                if(!unit.displayUnit) {
                    sb.append(i.getQuantity().toInt())
                }else{
                    sb.append(i.getQuantity().toString() + " " + unit.getSymbol())
                }
            }
            sb.append("\n")
        }
        sb.append("\n")

        var i = 1
        for(a in this.actions) {
            sb.append(i.toString() + ". " + a.getText() + "\n")
            i++
        }
        return sb.toString()
    }

    fun getIngredients(): Array<Ingredient> {
        return this.ingredients
    }

    fun getActions(): Array<Action> {
        return this.actions
    }

    fun getName(): String {
        return name
    }

    fun getImageResourceId(): Int {
        return this.imageId
    }

    override fun toString(): String {
        return name
    }

    companion object {
        val recipes = arrayOf(
            Recipe("Schabowy",R.drawable.schabowy, arrayOf(
                Ingredient("Schab", 1.0, MeasureUnit.KILOGRAM),
                Ingredient("Przyprawy", 0.0, MeasureUnit.NONE),
                Ingredient("Jajko", 1.0, MeasureUnit.PIECE),
                Ingredient("Bułka tarta", 0.0, MeasureUnit.NONE)
            ), arrayOf(
                Action("Pokrój schab na kawałki"),
                Action("Rozbij kawałki schabu"),
                Action("Dopraw do smaku"),
                Action("Upanieruj, zamaczając najpierw w jajku, potem w bułce tartej"),
                Action("Smaż przez 5 minut, przewracając regularnie na drugą stronę", 300)
            )),
            Recipe("Bigos", R.drawable.bigos, arrayOf(
                Ingredient("Kapusta kiszona", 0.5, MeasureUnit.KILOGRAM),
                Ingredient("Grzyby", 0.2, MeasureUnit.KILOGRAM),
                Ingredient("Średniej wielkości cebula", 1.0, MeasureUnit.PIECE)
            ), arrayOf(
                Action("Pokrój grzyby na kawałki"),
                Action("Pokrój na małe kawałki cebulę"),
                Action("Podsmaż grzyby z cebulą", 300),
                Action("Gotuj kapustę razem z grzybami", 3600)
            ))
        )
    }

}