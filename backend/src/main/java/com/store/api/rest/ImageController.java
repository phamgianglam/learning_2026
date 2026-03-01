package com.store.api.rest;

import com.store.api.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file , @RequestParam("itemId") long itemId) {
        try {
            imageService.saveImage(file, itemId);
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(Map.of("message", "Image Uploaded Successfully"));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Resource> getImageByName(@PathVariable String name) throws IOException {
        try {
            Map<String, Object> data = imageService.getImageByName(name);
            String contentType = String.valueOf(data.get("contentType"));
            Resource resource = (Resource) data.get("resource");

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (IOException e) {
            return  ResponseEntity.notFound().build();
        }
    }
}
