package com.melody.common

import android.content.Context
import android.util.Log
import okio.buffer
import okio.sink
import okio.source
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset

/**
 * 读取assert文件内容
 */
fun readAssertFileContent(context: Context, fileName: String): String {
    try {
        //获取assets资源管理器
        val assetManager = context.assets
        val inputStream = InputStreamReader(
                assetManager.open(fileName))
        return inputStream.buffered().use(BufferedReader::readText)
    } catch (e: IOException) {
        Log.e("FileUtil", "readAssertFileContent:" + e.message)
    }
    return ""
}

/**
 * 创建一个目录
 */
fun File.createFileDir() {
    if (!exists()) {
        mkdirs()
    } else {
        Log.i("FileUtil", "file not exists")
    }
}

/**
 * 创建noMedia文件
 */
fun File.createNoMediaFile() {
    val fileNoMedia = File(this, ".nomedia")
    if (!fileNoMedia.exists()) {
        try {
            fileNoMedia.createNewFile()
        } catch (e: IOException) {
            Log.e("FileUtil", "createNoMediaFile:" + e.message)
        }
    } else {
        Log.i("FileUtil", "file not exists")
    }
}

/**
 * 复制文件
 * destFile.copyFile(srcFile)
 */
@Throws(IOException::class)
fun File.copyFile(srcFile: File) {
    val source = srcFile.source().buffer()
    val sink = sink().buffer()
    sink.writeAll(source)
    source.close()
    sink.flush()
    sink.close()
}

/**
 * 保存文件内容
 * File 保存的文件
 * @param content 即将写入文件的内容
 */
fun File.saveFile(content: String): Boolean {
    if (!exists()) {
        createNewFile()
    }
    try {
        val bufferSink = sink().buffer()
        bufferSink.writeString(content, Charset.forName("utf-8"))
        bufferSink.close()
    } catch (e: Exception) {
        return false
    }
    return true
}
