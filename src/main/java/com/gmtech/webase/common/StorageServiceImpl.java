package com.gmtech.webase.common;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void store(List<MultipartFile> files) {
		// TODO Auto-generated method stub
		createDirectory();
        for (MultipartFile file : files) {
            savefile(file);
        }

	}
	
    private String getExtension(String filename) {
        int dot = filename.lastIndexOf(".");
        if (dot > 0) {
          return filename.substring(dot).toLowerCase();
        }
        return "";
      }

      private String getUploadFileName(String fileName) {

          return fileName + "_" +
                  DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
                      .format(LocalDateTime.now())
                  + getExtension(fileName);
      }

      private void createDirectory() {
          Path path = Paths.get("/Users/kimitei/Documents/gmtech/files");
          if (!Files.exists(path)) {
            try {
              Files.createDirectory(path);
            } catch (Exception e) {
              //エラー処理は省略
            }
          }
      }
	
	private void savefile(MultipartFile file) {
	      String filename = getUploadFileName(file.getOriginalFilename());
	      Path uploadfile = Paths.get("/Users/kimitei/Documents/gmtech/files/" + filename);
	      try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
	        byte[] bytes = file.getBytes();
	        os.write(bytes);
	      } catch (IOException e) {
	        //エラー処理は省略
	      }
	    }

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
