package com.atguigu.atcrowdfunding.potal.service.impl;

import com.atguigu.atcrowdfunding.potal.service.PicService;
import com.atguigu.atcrowdfunding.util.FtpUtil;
import com.atguigu.atcrowdfunding.util.IDUtils;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PicServiceImpl implements PicService {
	@Value("${ftp.host}")
	private String host;
	@Value("${ftp.port}")
	private Integer port;
	@Value("${ftp.username}")
	private String username;
	@Value("${ftp.password}")
	private String password;
	@Value("${ftp.basePath}")
	private String basePath;
	@Value("${imageserver.url}")
	private String imageUrl;

	public Map uploadPic(MultipartFile uploadFile) {
		Map resultMap = new HashMap();

		try {
			String oldName = uploadFile.getOriginalFilename();
			String newName = IDUtils.genImageName();
			String filename = newName + oldName.substring(oldName.indexOf("."));

			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			String filePath = dateFormat.format(date);
			boolean result = FtpUtil.uploadFile(this.host, this.port.intValue(), this.username, this.password,
					this.basePath, filePath, filename, uploadFile.getInputStream());
			if (!result) {
				resultMap.put("error", Integer.valueOf(1));
				resultMap.put("message", "文件上传失败");
			} else {
				resultMap.put("error", Integer.valueOf(0));
				resultMap.put("url", this.imageUrl + filePath + "/" + filename);
			}
			return resultMap;
		} catch (Exception e) {
			resultMap.put("error", Integer.valueOf(1));
			resultMap.put("message", "文件上传发生异常");
			e.printStackTrace();
		}
		return resultMap;
	}

	public void downLoad(String fileName, OutputStream outputStream) {
		try {
			//boolean bool = FtpUtil.downloadFile(host, port, username, password, fileName,basePath, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
