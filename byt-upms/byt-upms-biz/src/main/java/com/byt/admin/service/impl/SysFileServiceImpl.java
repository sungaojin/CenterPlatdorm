/*
 *    Copyright (c) 2018-2025, liyang All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the byt.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: liyang (wangiegie@gmail.com)
 */
package com.byt.admin.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byt.admin.api.entity.SysFile;
import com.byt.admin.mapper.SysFileMapper;
import com.byt.admin.service.SysFileService;
import com.byt.common.core.constant.CommonConstants;
import com.byt.common.core.util.R;
import com.byt.common.minio.service.MinioTemplate;
import com.byt.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件管理
 *
 * @author Luckly
 * @date 2019-06-18 17:18:42
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {
	private final MinioTemplate minioTemplate;


	/**
	 * 上传文件
	 *
	 * @param file
	 * @return
	 */
	@Override
	public R uploadFile(MultipartFile file) {
		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", CommonConstants.BUCKET_NAME);
		resultMap.put("fileName", fileName);
		resultMap.put("url", String.format("/admin/sys-file/%s/%s", CommonConstants.BUCKET_NAME, fileName));

		try {
			minioTemplate.putObject(CommonConstants.BUCKET_NAME, fileName, file.getInputStream());
			//文件管理数据记录,收集管理追踪文件
			fileLog(file, fileName);
		} catch (Exception e) {
			log.error("上传失败", e);
			return R.failed(e.getLocalizedMessage());
		}
		return R.ok(resultMap);
	}

	/**
	 * 读取文件
	 *
	 * @param bucket
	 * @param fileName
	 * @param response
	 */
	@Override
	public void getFile(String bucket, String fileName, HttpServletResponse response) {
		try (InputStream inputStream = minioTemplate.getObject(bucket, fileName)) {
			response.setContentType("application/octet-stream; charset=UTF-8");
			IoUtil.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
			log.error("文件读取异常", e);
		}
	}


	/**
	 * 删除文件
	 *
	 * @param id
	 * @return
	 */
	@Override
	@SneakyThrows
	@Transactional(rollbackFor = Exception.class)
	public Boolean deleteFile(Long id) {
		SysFile file = this.getById(id);
		minioTemplate.removeObject(CommonConstants.BUCKET_NAME, file.getFileName());
		return this.removeById(id);
	}

	/**
	 * 文件管理数据记录,收集管理追踪文件
	 *
	 * @param file     上传文件格式
	 * @param fileName 文件名
	 */
	private void fileLog(MultipartFile file, String fileName) {
		SysFile sysFile = new SysFile();
		//原文件名
		String original = file.getOriginalFilename();
		sysFile.setFileName(fileName);
		sysFile.setOriginal(original);
		sysFile.setFileSize(file.getSize());
		sysFile.setType(FileUtil.extName(original));
		sysFile.setBucketName(CommonConstants.BUCKET_NAME);
		sysFile.setCreateUser(SecurityUtils.getUser().getUsername());
		this.save(sysFile);
	}

}