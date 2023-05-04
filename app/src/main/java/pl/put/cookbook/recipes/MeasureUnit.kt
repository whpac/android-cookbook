package pl.put.cookbook.recipes

import pl.put.cookbook.App
import pl.put.cookbook.R

enum class MeasureUnit(val displayUnit: Boolean, val displayQuantity: Boolean, private val unitSymbol: Int?) {
    NONE(false, false, null),
    PIECE(false, true, null),
    KILOGRAM(true, true, R.string.unit_kg),
    LITER(true, true, R.string.unit_l);

    fun getSymbol(): String{
        if(unitSymbol == null) return "";
        return App.appResources.getString(unitSymbol)
    }
}