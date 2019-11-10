package com.ttcanteen.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ttcanteen.pojo.Common;
import com.ttcanteen.pojo.ResultPojo;
import com.ttcanteen.utils.FileUploadUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@Api(description = "文件上传接口")
@Transactional
public class FileUploadController {

	@Value("${default.uploadPath}")
	private String uploadPath;

	/**
	 * 上传文件，并返回新的文件名
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("uploadFile")
	@ApiOperation("文件上传")
	public ResultPojo upload(@RequestBody MultipartFile file) {
		String filename = "";
		try {
			// 上传目录地址
			String uploadDir = uploadPath;

			// 如果目录不存在，自动创建文件夹
			File dir = new File(uploadDir);

			if (!dir.exists()) {
				dir.mkdir();
			}

			if (file != null) {
				// 调用上传方法
				filename = FileUploadUtil.upload(file, uploadDir);
			}
		} catch (Exception e) {
			// 打印错误堆栈信息
			e.printStackTrace();
			return new ResultPojo(Common.ERR, file);
		}
		return new ResultPojo(Common.OK, "http://localhost:8081/img/" + filename);
	}

	@GetMapping(value = "img/{imgName}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	@ApiOperation("获取图片")
	@ResponseBody
	public byte[] getImg(@PathVariable String imgName) throws FileNotFoundException, IOException {
		Path path = Paths.get(uploadPath + imgName);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
