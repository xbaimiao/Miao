package com.xbaimiao.miao.utils

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException

class Config(private val file: File) : YamlConfiguration() {

	constructor(plugin: Plugin, filename: String) : this(File(plugin.dataFolder.path, filename))

	fun remove(): Boolean {
		return file.delete()
	}

	fun save() {
		try {
			save(file)
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	init {
		val file1 = file.parentFile
		if (!file1.exists()) {
			file1.mkdirs()
		}
		if (!file.exists()) {
			file.createNewFile()
		}
		loadConfiguration(file)
	}
}
