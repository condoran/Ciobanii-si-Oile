package cms.web;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileHelper {
    public static String storeFile(MultipartFile file, String location) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(location.substring(1)).toAbsolutePath().normalize();
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("!@#%^&*()")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = path.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return targetLocation.toString();
        } catch (IOException ex) {
            System.out.println(ex);
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
