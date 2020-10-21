package com.xbaimiao.miao.web

enum class Protocol(val code: String) {

	Http200("HTTP/1.1 200 ok"),
	Http204("204 No Content"),
	Http201("201 created"),
	Http301("301 Moved"),
	Http400("400 Bad Request"),
	Http403("403 Forbidden"),
	Http404("404 Not Found"),
	Http405("405 Method Not Allowed"),
	Http500("500 Internal Server Error"),
	Http503("503 Service Unavailable");

	fun getBytes(): ByteArray {
		return code.toByteArray()
	}

}