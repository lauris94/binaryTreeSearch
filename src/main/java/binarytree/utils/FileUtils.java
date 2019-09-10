package binarytree.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    private FileUtils() {
    }

    public static List<String> getFileLinesFromResources(String fileName) {
        URL url = FileUtils.class.getClassLoader().getResource(fileName);
        Path path;

        try {
            path = Paths.get(url.toURI());
        } catch (URISyntaxException | NullPointerException ex) {
            throw new IllegalArgumentException("Filename is incorrect", ex);
        }

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println(String.format("Error when reading file from %s", path));
        }
        return Collections.emptyList();
    }
}
