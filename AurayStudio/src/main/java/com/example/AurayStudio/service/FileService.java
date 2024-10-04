package com.example.AurayStudio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AurayStudio.dao.FileDao;
import com.example.AurayStudio.dto.FileDto;

@Service
public class FileService {
	@Autowired
	private FileDao fileDao;
	
	public void fileUpload(FileDto dto) {
		fileDao.insertFile(dto);
	}
	
	public List<FileDto> fileDownloadList(int post_no) {
		return fileDao.selectFileByPostNo(post_no);
	}
	
	public FileDto fileDownload(int file_id) {
		return fileDao.selectFileById(file_id);
	}

}
