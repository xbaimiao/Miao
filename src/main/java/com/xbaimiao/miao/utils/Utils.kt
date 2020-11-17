package com.xbaimiao.miao.utils

import java.io.*
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.JarFile
import kotlin.collections.ArrayList

object Utils {

	private val jarFiles by lazy {
		val filePlugins = File("${System.getProperty("user.dir")}${File.separator}plugins")
		val list = ArrayList<JarFile>()
		if (filePlugins.listFiles() != null) {
			for (file in filePlugins.listFiles()!!) {
				if (!file.isDirectory) {
					if (file.name.endsWith(".jar")) {
						list.add(JarFile(file))
					}
				}
			}
		}
		list
	}


	@JvmStatic
	fun getPluginFileName(name: String): String? {
		for (jarFile in jarFiles) {
			val pluginYml = jarFile.getEntry("plugin.yml") ?: continue
			val br = BufferedReader(InputStreamReader(jarFile.getInputStream(pluginYml), StandardCharsets.UTF_8))
			var s: String?
			while (br.readLine().also { s = it } != null) {
				if (s != null) {
					s = s!!.replace(" ", "")
					if (s!!.startsWith("name")) {
						if (s!!.split(":")[1] == name) {
							return jarFile.name
						}
					}
				}
			}
		}
		return null
	}

	@JvmStatic
	fun getPluginFile(name: String): File? {
		val a = getPluginFileName(name) ?: return null
		return File(a)
	}

	private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

	@JvmStatic
	fun stampToDate(s: Long): String {
		val date = Date(s)
		return simpleDateFormat.format(date)
	}

	@JvmStatic
	fun dateToStamp(s: String): Long {
		val date = simpleDateFormat.parse(s)
		val ts = date.time
		return ts.toString().toLong()
	}

	@JvmStatic
	fun getCurrentTime(): String {
		return stampToDate(System.currentTimeMillis())
	}

	class FileNotFind(s: String) : Exception(s)

	@JvmStatic
	fun copyFile(file1: File, file2: File) {
		if (file1.exists()) {
			val input = FileInputStream(file1)
			val out = FileOutputStream(file2)
			val buff = ByteArray(1024)
			var len: Int?
			while (input.read(buff).also { len = it } > 0) {
				out.write(buff, 0, len!!)
			}
			input.close()
			out.flush()
			out.close()
			return
		}
		if (file2.exists()) {
			copyFile(file2, file1)
			return
		}
		try {
			throw FileNotFind("文件都不给我,我怎么复制")
		} catch (e: FileNotFind) {
			e.printStackTrace()
		}
	}

	@JvmStatic
	fun moveFile(file: File, path: String, isCover: Boolean) {
		moveFile(file, File(path), isCover)
	}

	@JvmStatic
	fun moveFile(file: File, path: String) {
		moveFile(file, File(path), true)
	}

	@JvmStatic
	fun moveFile(file: File, file1: File) {
		moveFile(file, file1, true)
	}

	@JvmStatic
	fun moveFile(file: File, file1: File, isCover: Boolean) {
		if (!isCover) {
			if (file1.exists()) {
				return
			}
		}
		if (file.exists()) {
			val input = FileInputStream(file)
			val out = FileOutputStream(file1)
			val buff = ByteArray(1024)
			var len: Int?
			while (input.read(buff).also { len = it } > 0) {
				out.write(buff, 0, len!!)
			}
			out.flush()
			out.close()
			input.close()
			file.delete()
			return
		}
		try {
			throw FileNotFind("文件未找到,你让我怎么移动")
		} catch (e: FileNotFind) {
			e.printStackTrace()
		}
	}

}