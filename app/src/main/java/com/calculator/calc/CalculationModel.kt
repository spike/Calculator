package com.calculator.calc

import androidx.lifecycle.ViewModel

data class CalculationModel(var screen: String, var subscreen: String, var firstOperand: String, var secondOperand: String, var operator: String) : ViewModel() {


}