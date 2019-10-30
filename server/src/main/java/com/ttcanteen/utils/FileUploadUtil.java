package com.ttcanteen.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	/**
	 * 文件上传
	 * 
	 * @param file     文件对象
	 * @param uploadDir 保存的路径
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String upload(MultipartFile file, String uploadDir) throws IllegalStateException, IOException {
		// 文件后缀名
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		// 上传文件名
		String filename = UUID.randomUUID() + suffix;
		// 服务器端保存的文件对象
		File serverFile = new File(uploadDir + filename);

		if (!serverFile.exists()) {
			// 先得到文件的上级目录，并创建上级目录，在创建文件
			serverFile.getParentFile().mkdir();
			try {
				// 创建文件
				serverFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 将上传的文件写入到服务器端文件内
		file.transferTo(serverFile);

		return filename;
	}
}
