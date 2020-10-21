package com.xbaimiao.miao.web

enum class Type(val type: String) {
	HTML("text/html"),
	TXT("text/plain"),
	XML("text/xml"),
	GIF("image/gif"),
	JPG("image/jpeg"),
	PNG("image/png"),
	XHTML("application/xhtml+xml"),
	JSON("application/json"),
	AXML("application/atom+xml"),
	PDF("application/pdf"),
	WORD("application/msword"),
	DOWNLOAD("application/octet-stream"),
	CSS("text/css"),
	MID("audio/mid"),
	APK("application/vnd.android.package-archive"),
	EXE("application/x-msdownload");
}