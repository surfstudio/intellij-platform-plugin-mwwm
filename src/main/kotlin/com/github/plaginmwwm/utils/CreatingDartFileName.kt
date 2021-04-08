package com.github.plaginmwwm.utils

import com.github.plaginmwwm.common.FileNaming
import com.github.plaginmwwm.common.SpecialFileNamingEnum
import java.lang.StringBuilder
import java.util.*

/// Имя файла
fun creatingFileName(nameClazz: String, naming: SpecialFileNamingEnum): String {

    val nameClass = getNameFile(nameClazz)
    return when (naming) {
        SpecialFileNamingEnum.widget -> nameClass + FileNaming.widget
        SpecialFileNamingEnum.wm -> nameClass + FileNaming.wm
        SpecialFileNamingEnum.route -> nameClass + FileNaming.route
        SpecialFileNamingEnum.di -> nameClass + FileNaming.di
    }
}

/// создаем имя файла дарт
private fun getNameFile(nameFIle: String): String {
    val answer = StringBuilder()
    val findUpper = nameFIle.toCharArray()
    var i = 0
    while (findUpper.size > i) {
        // пропускаем 1-й символ
        // ascii value in between 65 and 91 is A to Z
        if (i != 0 && findUpper[i] >= 65.toChar() && findUpper[i] <= 91.toChar()) {
            answer.append("_") //adding only uppercase
        }
        answer.append(findUpper[i])
        i++
    }
    return answer.toString()
        .toLowerCase(Locale.ROOT)
}