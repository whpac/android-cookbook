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
        val recipesMainCourses = arrayOf(
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
            )),
            Recipe("Zupa jarzynowa", R.drawable.jarzynowa, arrayOf(
                Ingredient("Cebula", 1.0, MeasureUnit.PIECE),
                Ingredient("Ziemniaki", 0.25, MeasureUnit.KILOGRAM),
                Ingredient("Pietruszka", 0.15, MeasureUnit.KILOGRAM),
                Ingredient("Marchew", 0.2, MeasureUnit.KILOGRAM),
                Ingredient("Por", 0.2, MeasureUnit.KILOGRAM),
                Ingredient("Kostka rosołowa", 1.0, MeasureUnit.PIECE)
            ), arrayOf(
                Action("Warzywa obierz, oczyść i pokrój na kawałki"),
                Action("Podsmaż warzywa, a jak się zarumienią, wlej 1l wody"),
                Action("Dodaj kostkę rosołową i gotuj ok. 20 minut", 20*60),
                Action("Zmiksuj zupę na krem, dopraw do smaku")
            )),
            Recipe("Pizza domowa", R.drawable.pizza, arrayOf(
                Ingredient("Mąka pszenna", 0.5, MeasureUnit.KILOGRAM),
                Ingredient("Woda", 0.3, MeasureUnit.LITER),
                Ingredient("Sos pomidorowy lub keczup", 0.25, MeasureUnit.KILOGRAM),
                Ingredient("Cukier", 0.05, MeasureUnit.KILOGRAM),
                Ingredient("Oliwa", 0.05, MeasureUnit.LITER),
                Ingredient("Sól", 0.0, MeasureUnit.NONE),
                Ingredient("Drożdże", 0.0, MeasureUnit.NONE),
                Ingredient("Wybrane dodatki", 0.0, MeasureUnit.NONE)
            ), arrayOf(
                Action("Wszystkie składniki ciasta połącz ze sobą, dobrze wyrabiając"),
                Action("Odstaw ciasto do wyrośnięcia na 40 minut", 40*60),
                Action("Przygotuj dodatki, krojąc je na plasterki lub kawałki"),
                Action("Po wyrośnięciu podziel ciasto na 4 równe kule, każdą rozwałkuj"),
                Action("Każdy placek posmaruj sosem pomidorowym, a następnie ułóż dodatki"),
                Action("Piecz pizzę w temperaturze 200 stopni przez około 25 minut", 25*60)
            ))
        )

        val recipesDesserts = arrayOf(
            Recipe("Jabłecznik", R.drawable.jablecznik, arrayOf(
                Ingredient("Mąka krupczatka", 0.25, MeasureUnit.KILOGRAM),
                Ingredient("Masło", 0.1, MeasureUnit.KILOGRAM),
                Ingredient("Cukier", 0.1, MeasureUnit.KILOGRAM),
                Ingredient("Cukier waniliowy", 1.0, MeasureUnit.PIECE),
                Ingredient("Jajka", 2.0, MeasureUnit.PIECE),
                Ingredient("Jabłka", 0.5, MeasureUnit.KILOGRAM)
            ), arrayOf(
                Action("Obierz jabłka i pokrój je w kostkę"),
                Action("Włóż jabłka do garnka i gotuj przez około 3 minuty", 180),
                Action("Oddziel żółtka od białek, utrzyj masło z cukrem i cukrem waniliowym"),
                Action("Dodaj żółtka i ubijaj aż masa się połączy. Wsyp mąkę i mieszaj aż ciasto będzie jednolite"),
                Action("Połową ciasta wyłóż dno tortownicy, na to połóż podgotowane jabłka"),
                Action("Na wierzch pokrusz resztę ciasta"),
                Action("Piecz w piekarniku w temperaturze 170 stopni przez około 20 minut", 20*60)
            )),
            Recipe("Kruche ciasto z jagodami", R.drawable.jagodowe, arrayOf(
                Ingredient("Mąka pszenna", 0.45, MeasureUnit.KILOGRAM),
                Ingredient("Jajko", 1.0, MeasureUnit.PIECE),
                Ingredient("Masło", 0.25, MeasureUnit.KILOGRAM),
                Ingredient("Cukier", 0.2, MeasureUnit.KILOGRAM),
                Ingredient("Sól", 0.0, MeasureUnit.NONE),
                Ingredient("Proszek do pieczenia", 0.0, MeasureUnit.NONE),
                Ingredient("Jagody", 0.5, MeasureUnit.KILOGRAM)
            ), arrayOf(
                Action("Do miski wsyp mąkę i łyżeczkę proszku do pieczenia. Dodaj cukier i szczyptę soli, masło i jajko. Zagnieć"),
                Action("Połową ciasta wyłóż spód blachy"),
                Action("Podpiecz w temperaturze 180 stopni przez 10 minut", 600),
                Action("Na podpieczony spód połóż jagody"),
                Action("Rozsyp na wierzchu resztę ciasta"),
                Action("Piecz w temperaturze 180 stopni przez 35 minut", 35*60)
            )),
            Recipe("Rolada bananowa", R.drawable.bananowa, arrayOf(
                Ingredient("Jajka", 3.0, MeasureUnit.PIECE),
                Ingredient("Cukier", 0.1, MeasureUnit.KILOGRAM),
                Ingredient("Mąka pszenna", 0.1, MeasureUnit.KILOGRAM),
                Ingredient("Kakao", 0.03, MeasureUnit.KILOGRAM),
                Ingredient("Śmietana kremówka", 0.4, MeasureUnit.LITER),
                Ingredient("Banany", 2.0, MeasureUnit.PIECE),
                Ingredient("Czekolada gorzka", 0.05, MeasureUnit.KILOGRAM),
                Ingredient("Proszek do pieczenia", 0.0, MeasureUnit.NONE)
            ), arrayOf(
                Action("Białka oddziel od żółtek i ubij na pianę."),
                Action("Do piany dodaj cukier i żółtka."),
                Action("Dodaj przesianą mąkę, łyżeczkę proszku do pieczenia i kakao"),
                Action("Przełóż ciasto na blaszkę i piecz 8 minut w temp. 200 stopni.", 8*60),
                Action("Ubij śmietanę, dodaj do niej drobno startą czekoladę i wymieszaj"),
                Action("Posmaruj masą upieczony biszkopt"),
                Action("Połóż na środku obrane ze skórki banany i zawiń ciasto w rulon"),
                Action("Odłóż do lodówki na minimum godzinę", 3600)
            ))
        )

        val recipes = arrayOf(recipesMainCourses, recipesDesserts)
    }

}