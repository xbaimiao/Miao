package com.xbaimiao.miao.file

import java.io.File

object FileUtils {

    @JvmStatic
    fun buildFile(vararg name: String): File {
        val fileName = StringBuilder()
        name.forEach {
            fileName.append(it)
                .append(File.separator)
        }
        return File(fileName.toString())
    }

}