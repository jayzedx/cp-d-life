package com.mdc.cpfit.util

class IDCardUtil {
companion object {

    fun isValid(idCard: String): Boolean {
        if (idCard.length != 13) {
            return false
        }
        var sum = 0
        for (i in 0..11) {
            sum += Character.getNumericValue(idCard[i]) * (13 - i)
        }
        return (11 - sum % 11) % 10 == Character.getNumericValue(idCard[12])

    }
}

}