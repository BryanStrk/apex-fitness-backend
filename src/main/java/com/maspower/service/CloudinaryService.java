package com.maspower.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private static final Pattern VERSION_PREFIX = Pattern.compile("^v\\d+/");
    private static final String UPLOAD_SEGMENT = "/upload/";

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file, String folder) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", folder,
                        "resource_type", "image"
                )
        );
        return (String) uploadResult.get("secure_url");
    }

    public void deleteImage(String imageUrl) throws IOException {
        String publicId = extractPublicId(imageUrl);
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    /**
     * Extrae el public_id de una URL de Cloudinary, manejando carpetas anidadas.
     *
     * Ejemplo:
     *   URL:  https://res.cloudinary.com/dutmn3xde/image/upload/v1776594314/maspower/workouts/hiit_abc.jpg
     *   Out:  maspower/workouts/hiit_abc
     */
    private String extractPublicId(String imageUrl) {
        int uploadIndex = imageUrl.indexOf(UPLOAD_SEGMENT);
        if (uploadIndex == -1) {
            throw new IllegalArgumentException("URL de Cloudinary no válida: " + imageUrl);
        }

        String path = imageUrl.substring(uploadIndex + UPLOAD_SEGMENT.length());

        // Eliminar prefijo de versión (v1234567890/) si existe
        path = VERSION_PREFIX.matcher(path).replaceFirst("");

        // Eliminar extensión del archivo
        int lastDotIndex = path.lastIndexOf('.');
        return lastDotIndex != -1 ? path.substring(0, lastDotIndex) : path;
    }
}