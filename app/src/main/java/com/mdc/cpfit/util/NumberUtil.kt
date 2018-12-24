package com.mdc.cpfit.util

import java.math.BigDecimal
import java.text.DecimalFormat

class NumberUtil {
    companion object {
        /**
         * Round to certain number of decimals
         *
         * @param d
         * @param decimalPlace
         * @return
         */
        fun roundFloat(number: Float, decimalPlace: Int): Float {
            var bd = BigDecimal(java.lang.Float.toString(number))
            bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP)
            return bd.toFloat()
        }

        fun comma(agr1: Double): String {
            if (agr1 != 0.0) {

                val formatter = DecimalFormat("#,###")
                return formatter.format(agr1)
            }
            return "0"
        }

        fun comma2Decimal(agr1: String): String {
            var agr1 = agr1
            agr1 = agr1.replace(",", "")
            val agr2 = agr1.toFloat()
            val formatter = DecimalFormat("#,##0.00")
            return formatter.format(agr2)

        }

        fun deleteComma(agr1: String?): String? {
            return agr1?.replace(",", "") ?: agr1
        }

    }
}