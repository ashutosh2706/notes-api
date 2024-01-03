package mynotes.api.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static mynotes.api.Application.UPLOAD_DIR;

@Service
public class DownloadService {

    private final Path fileStorageLocation = Paths.get(UPLOAD_DIR);

    public Resource loadFileAsResource(String fileName) {
        Resource resource = null;
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            resource = new UrlResource(filePath.toUri());
            if(resource.exists()) return resource;
            else throw new FileNotFoundException("Requested resource was not found on this server");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resource;
    }
}
