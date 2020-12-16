package com.xbaimiao.miao.utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.URL
import java.net.URLConnection

object Http {

	/**
	 * 发送Post请求
	 */
	@JvmStatic
	fun sendPost(url: String, param: String): String {
		val result = StringBuilder()
		try {
			val realUrl = URL(url)
			// 打开和URL之间的连接
			val conn: URLConnection = realUrl.openConnection()
			conn.setRequestProperty("accept", "*/*")
			conn.setRequestProperty("connection", "Keep-Alive")
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
			// 发送POST请求必须设置如下两行
			conn.doOutput = true
			conn.doInput = true
			// 获取URLConnection对象对应的输出流
			val out = PrintWriter(conn.getOutputStream())
			// 发送请求参数
			out.print(param)
			// flush输出流的缓冲
			out.flush()
			val input = BufferedReader(InputStreamReader(conn.getInputStream()))
			var line: String?
			while (input.readLine().also { line = it } != null) {
				result.append(line)
			}
		} catch (e: Exception) {
			println("[POST请求]向地址：$url 发送数据：$param 发生错误!")
		}
		return result.toString()
	}

	/**
	 * 发送Get请求
	 */
	@JvmStatic
	fun sendGet(url: String): String {
		val result = StringBuilder()
		try {
			val realUrl = URL(url)
			val conn = realUrl.openConnection() // 打开和URL之间的连接
			conn.setRequestProperty("accept", "*/*") // 设置通用的请求属性
			conn.setRequestProperty("connection", "Keep-Alive")
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)")
			conn.connectTimeout = 4000
			conn.connect() // 建立实际的连接
			val input = BufferedReader(InputStreamReader(conn.getInputStream(), "UTF-8")) // 定义BufferedReader输入流来读取URL的响应
			var line: String?
			while (input.readLine().also { line = it } != null) {
				result.append(line)
			}
		} catch (e: Exception) {
			println("发送GET请求出现异常！$url")
		}
		return result.toString()
	}
}