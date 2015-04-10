/**
 * 
 */
package com.hadoop.hdfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;

/**
 * @author Administrator
 * 
 */
public class MapFileDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(
				URI.create("hdfs://192.168.20.131:9000/"), conf);
		Path mapFile = new Path("/test/mapFile.map");

		// Writer内部类用于文件的写操作,假设Key和Value都为Text类型
		MapFile.Writer writer = new MapFile.Writer(conf, fs,
				mapFile.toString(), Text.class, Text.class);
		// 通过writer向文档中写入记录
		writer.append(new Text("key"), new Text("value"));
		IOUtils.closeStream(writer);// 关闭write流
		// 通过reader从文档中读取记录
		Text key = new Text();
		Text value = new Text();
		// Reader内部类用于文件的读取操作
		MapFile.Reader reader = new MapFile.Reader(fs, mapFile.toString(), conf);
		while (reader.next(key, value)) {
			System.out.println(key);
			System.out.println(key);
		}
		IOUtils.closeStream(reader);// 关闭read流

	}

}
