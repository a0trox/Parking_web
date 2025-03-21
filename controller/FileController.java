package com.controller;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.entity.EIException;
import com.service.ConfigService;
import com.utils.R;

/**
 * 上传文件映射表
 */
@RestController
@RequestMapping("file")
@SuppressWarnings({"unchecked","rawtypes"})
public class FileController{
	@Autowired
    private ConfigService configService;
	/**
	 * 上传文件
	 */
	@RequestMapping("/upload")
    @IgnoreAuth
	public R upload(@RequestParam("file") MultipartFile file,String type) throws Exception {
		if (file.isEmpty()) {
			throw new EIException("上传文件不能为空");
		}
		String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		String decode = URLDecoder.decode(ResourceUtils.getURL("classpath:static").getPath(), "utf-8");
		File path = new File(decode);
		File path3 = new File("");
		if(!path.exists()) {
		    path = new File("");
		}
		File path1 = new File(path3.getAbsolutePath() + "/src/main/resources/static/upload/");
		File upload = new File(path.getAbsolutePath(),"/upload/");
		if(!upload.exists()) {
		    upload.mkdirs();
		}
		String fileName = new Date().getTime()+"."+fileExt;
        if(StringUtils.isNotBlank(type) && type.contains("_template")) {
            fileName = type + "."+fileExt;
            new File(upload.getAbsolutePath()+"/"+fileName).deleteOnExit();
        }
		File dest = new File(upload.getAbsolutePath()+"/"+fileName);
		file.transferTo(dest);

		if(StringUtils.isNotBlank(type) && type.equals("1")) {
			ConfigEntity configEntity = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
			if(configEntity==null) {
				configEntity = new ConfigEntity();
				configEntity.setName("faceFile");
				configEntity.setValue(fileName);
			} else {
				configEntity.setValue(fileName);
			}
			configService.insertOrUpdate(configEntity);
		}
		FileController.copyFile(dest, path1.getAbsolutePath());
		return R.ok().put("file", fileName);
	}
	
	/**
	 * 下载文件
	 */
	@IgnoreAuth
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) {
		try {
			String decode = URLDecoder.decode(ResourceUtils.getURL("classpath:static").getPath(), "utf-8");
			File path = new File(decode);
			if(!path.exists()) {
			    path = new File("");
			}
			File upload = new File(path.getAbsolutePath(),"/upload/");
			if(!upload.exists()) {
			    upload.mkdirs();
			}
			File file = new File(upload.getAbsolutePath()+"/"+fileName);
			if(file.exists()){

				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
			    headers.setContentDispositionFormData("attachment", fileName);    
			    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	public static void copyFile(File source,String dest )throws IOException{
		//创建目的地文件夹
		File destfile = new File(dest);
		if(!destfile.exists()){
			destfile.mkdir();
		}
		//如果source是文件夹，则在目的地址中创建新的文件夹
		if(source.isDirectory()){
			File file = new File(dest+"\\"+source.getName());//用目的地址加上source的文件夹名称，创建新的文件夹
			file.mkdir();
			//得到source文件夹的所有文件及目录
			File[] files = source.listFiles();
			if(files.length==0){
				return;
			}else{
				for(int i = 0 ;i<files.length;i++){
					copyFile(files[i],file.getPath());
				}
			}

		}
		//source是文件，则用字节输入输出流复制文件
		else if(source.isFile()){
			FileInputStream fis = new FileInputStream(source);
			//创建新的文件，保存复制内容，文件名称与源文件名称一致
			File dfile = new File(dest+"\\"+source.getName());
			if(!dfile.exists()){
				dfile.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(dfile);
			// 读写数据
			// 定义数组
			byte[] b = new byte[1024];
			// 定义长度
			int len;
			// 循环读取
			while ((len = fis.read(b))!=-1) {
				// 写出数据
				fos.write(b, 0 , len);
			}

			//关闭资源
			fos.close();
			fis.close();

		}
	}
}
