package com.store.api.service;

import com.store.api.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${image.directory}")
    private String imageFolder;

    @Autowired
    private ItemRepository itemRepository;

    public void saveImage(MultipartFile file , long itemId) throws IOException {
        var item = itemRepository.getReferenceById(itemId);
        var contentType = file.getContentType();
        var extension = contentType.substring(contentType.lastIndexOf("/") + 1);
        Path destination = Path.of(imageFolder, itemId + "." + extension);
        Files.write(destination, file.getBytes());
        item.setImagePath(destination.toString());
        itemRepository.save(item);
    }

    public Map<String, Object> getImageByName(String name) throws IOException {
        var filePAth = Paths.get(imageFolder).resolve(name).normalize();
        Resource resource = new FileSystemResource(filePAth.toFile());

        if (!resource.exists() || !resource.isReadable()) {
            throw new FileNotFoundException("File not found");
        }
        Map<String, Object>  data = new HashMap<>();
        data.put("resource", resource);

        var contentType = Files.probeContentType(filePAth);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        data.put("contentType", contentType);
        return data;
    }
}
