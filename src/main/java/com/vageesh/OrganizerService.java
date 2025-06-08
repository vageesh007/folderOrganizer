package com.vageesh;

import javafx.concurrent.Task;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Background Task that organizes files in the given folder into subfolders by extension.
 */
public class OrganizerService extends Task<Void> {

    private static final Map<String, List<String>> EXT_MAP = createExtMap();

    private final Path folder;

    public OrganizerService(Path folder) {
        this.folder = folder;
    }

    @Override
    protected Void call() throws IOException {
    	List<Path> files = new ArrayList<>();
    	try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
    	    for (Path p : stream) {
    	        if (Files.isRegularFile(p)) {
    	            files.add(p);
    	        }
    	    }
    	}

    	long total = files.size();
    	long count = 0;

    	for (Path p : files) {
    	    if (isCancelled()) break;

    	    String category = categorize(p);
    	    Path targetDir = folder.resolve(category);
    	    Files.createDirectories(targetDir);
    	    Files.move(p, targetDir.resolve(p.getFileName()), StandardCopyOption.REPLACE_EXISTING);

    	    updateProgress(++count, total);
    	}
        return null;
    }

    private String categorize(Path file) {
        String name = file.getFileName().toString().toLowerCase(Locale.ROOT);
        int idx = name.lastIndexOf('.');
        String ext = (idx >= 0) ? name.substring(idx) : "";

        for (Map.Entry<String, List<String>> entry : EXT_MAP.entrySet()) {
            if (entry.getValue().contains(ext)) {
                return entry.getKey();
            }
        }
        return "Others";
    }

    private static Map<String, List<String>> createExtMap() {
        Map<String, List<String>> m = new HashMap<>();
        m.put("Images", Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".tiff", ".svg"));
        m.put("Videos", Arrays.asList(".mp4", ".mkv", ".mov", ".avi", ".flv", ".wmv"));
        m.put("Audio",  Arrays.asList(".mp3", ".wav", ".aac", ".flac", ".ogg", ".m4a"));
        m.put("Archives", Arrays.asList(".zip", ".rar", ".7z", ".tar", ".gz", ".bz2", ".xz"));
        m.put("Documents", Arrays.asList(".pdf", ".docx", ".doc", ".txt", ".xls", ".xlsx", ".ppt", ".pptx", ".odt"));
        m.put("Code", Arrays.asList(".java", ".js", ".py", ".cpp", ".c", ".cs", ".rb", ".go", ".rs", ".php", ".html", ".css", ".ts", ".swift"));
        m.put("Fonts", Arrays.asList(".ttf", ".otf", ".woff", ".woff2", ".eot"));
        m.put("Ebooks", Arrays.asList(".epub", ".mobi", ".azw3", ".pdf"));
        m.put("Disks", Arrays.asList(".iso", ".img", ".dmg", ".vhd", ".vmdk"));
        m.put("GIS", Arrays.asList(".shp", ".kml", ".geojson"));
        m.put("CAD", Arrays.asList(".dwg", ".dxf"));
        m.put("Data", Arrays.asList(".csv", ".json", ".xml", ".sql", ".db"));
        m.put("Presentations", Arrays.asList(".ppt", ".pptx"));
        m.put("Spreadsheets", Arrays.asList(".xls", ".xlsx", ".ods"));
        // Catch-all
        m.put("Others", Collections.emptyList());

        return Collections.unmodifiableMap(m);
    }
}
