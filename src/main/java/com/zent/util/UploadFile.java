package com.zent.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	public static String upload(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		if (file.getOriginalFilename() != null && file.getOriginalFilename() != ""
				&& file.getSize() > 0) {
			try {
				InputStream ip = file.getInputStream();
				OutputStream outStream = new FileOutputStream(Constants.PATH + file.getOriginalFilename());
				byte[] buffer = new byte[8 * 1024];
				int bytesRead;
				while ((bytesRead = ip.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				outStream.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return fileName;
	}
}
