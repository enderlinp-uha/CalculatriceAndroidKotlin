package com.example.calculatriceandroidkotlin

class Calculator  {

    private val DEFAULT_VALUE = "0"
    private var number: Int = 0
    private var result: String = DEFAULT_VALUE

    init {
        this.number = number
        this.result = result
    }

    private fun multiply(): Calculator {
        return this
    }

    private fun divide(): Calculator {
        return this
    }

    private fun subtract(): Calculator {
        return this
    }

    private fun add(): Calculator {
        return this
    }

    private fun modulo(): Calculator {
        return this
    }

    private fun clear(): Calculator {
        this.result = DEFAULT_VALUE
        return this
    }

    private fun compute(): Calculator {
        return this
    }

    fun update(operator: EOperator): Calculator {
        return when (operator) {
            EOperator.CLEAR -> this.clear()
            EOperator.MODULO -> this.modulo()
            EOperator.MULTIPLY -> this.multiply()
            EOperator.DIVIDE -> this.divide()
            EOperator.SUBTRACT -> this.subtract()
            EOperator.ADD -> this.add()
            else -> this.compute()
        }
    }

    fun getResult(): String {
        return this.result
    }
}