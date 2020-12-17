package com.xbaimiao.miao.web

import java.io.*

object Web {

    @JvmStatic
    fun sendTxT(code: Protocol, txt: String, out: OutputStream) {
        val sb = StringBuilder()
        sb.append("HTTP/1.1 ").append(code.code).append("\r\n")
            .append("Content-Language:zh-CN \r\n")
            .append("Content-Type:").append(Protocol.Type.TXT).append("; charset=UTF-8 \r\n\r\n")
            .append(txt)
        out.write(sb.toString().toByteArray())
        out.flush()
        out.close()
    }

    @JvmStatic
    fun sendHtml(code: Protocol, html: File, Type: Protocol.Type, out: OutputStream): Boolean {
        if (html.exists()) {
            val sb = StringBuilder()
            sb.append("HTTP/1.1 ").append(code.code).append("\r\n")
                .append("Content-Language:zh-CN \r\n")
                .append("Content-Type:").append(Type.type).append("; charset=UTF-8 \r\n")
                .append("Content-Length:").append(html.length()).append("\r\n\r\n")
            val reader = BufferedReader(FileReader(html))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.append(line).append("\r\n")
            }
            out.write(sb.toString().toByteArray())
            out.flush()
            out.close()
            reader.close()
            return true
        }
        return false
    }

    @JvmStatic
    fun sendFile(code: Protocol, file: File, Type: Protocol.Type, out: OutputStream): Boolean {
        if (file.exists()) {
            val fis = FileInputStream(file)
            val head = StringBuffer()
            head.append("HTTP/1.1 ").append(code.code).append("\r\n")
                .append("Content-Language:zh-CN\r\n")
                .append("Content-Type:${Type.type}; charset=UTF-8\r\n")
                .append("Content-Length:${file.length()}\r\n\r\n")
            out.write(head.toString().toByteArray())
            var data: Int
            val bit = ByteArray(1024)
            while (fis.read(bit).also { data = it } > 0) {
                out.write(bit, 0, data)
            }
            out.flush()
            out.close()
            fis.close()
            return true
        }
        return false
    }

    @JvmStatic
    fun send301(website: String, out: OutputStream) {
        val code = """
			HTTP/1.1 301 Moved PermanentlyContent-Type:text/html; charset=UTF-8 
			Location: $website
			
			
			"""
        out.write(code.toByteArray())
        out.flush()
        out.close()
    }
}