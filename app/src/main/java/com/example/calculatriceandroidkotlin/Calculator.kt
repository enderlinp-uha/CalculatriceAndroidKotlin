package com.example.calculatriceandroidkotlin

// Paramètres de l'application
const val BLANK_VALUE     = " "
const val DEFAULT_VALUE   = "0"
const val UNDEFINED_VALUE = "Non défini"

class Calculator  {

    private var input:   String = DEFAULT_VALUE
    private var history: String = BLANK_VALUE

    private fun clear(): Calculator {
        this.input   = DEFAULT_VALUE
        this.history = BLANK_VALUE
        return this
    }

    private fun compute(): Calculator {
        // On retourne un message d'erreur si division par 0
        if ("/0" in this.input) {
            this.input = UNDEFINED_VALUE
            return this
        }

        var number = 0
        val list   = mutableListOf<Double>()

        // Opérateur par défaut
        var operator = "+"

        // On parcourt la saisie caractère par caractère
        for ((index, value) in this.input.withIndex()) {
            // Si le caractère est un chiffre, on l'ajoute au précédent
            if (value.isDigit()) {
                number = (number.toString() + value).toInt()
            }
            // Si le caractère est un opérateur ou le dernier saisi
            if (value.toString() in listOf("+", "-", "/", "*", "%") || index == this.input.length - 1) {
                when (operator) {
                    "+" -> list.add(number.toDouble())
                    "-" -> list.add(-number.toDouble())
                    "/" -> list[list.size - 1] = list.last() / number
                    "*" -> list[list.size - 1] = list.last() * number
                    "%" -> list[list.size - 1] = list.last() % number
                }

                // On réinitialise les variables
                operator = value.toString()
                number   = 0
            }
        }
        // On somme tous les éléments de la liste de nombres
        this.input = format(list.sum())
        return this
    }

    private fun format(value: Double): String {
        // Si le reste de la division euclidienne est égal à 0.0
        // on retourne un entier
        val result = when(value % 1 == 0.0) {
            true -> value.toInt()
            else -> value
        }
        return result.toString()
    }

    fun getCurrentHistory(): String {
        return this.history
    }

    fun getCurrentInput(): String {
        return this.input
    }

    fun update(action: EAction, input: String = ""): Calculator {
        this.input   = input
        this.history = input

        return when (action) {
            EAction.CLEAR -> this.clear()
            EAction.EQUAL -> this.compute()
        }
    }

    @Override
    override fun toString(): String {
        return "Résultat : $this.input, Historique : $this.history"
    }
}