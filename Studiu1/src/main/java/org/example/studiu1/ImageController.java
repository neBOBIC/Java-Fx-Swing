package org.example.studiu1;

import javafx.scene.image.Image;
import java.io.File;
import java.util.ArrayList;

public class ImageController {
    private ArrayList<Image> images;
    private int currentIndex;

    public ImageController(String folderPath) {
        images = new ArrayList<>();
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
                    images.add(new Image(file.toURI().toString()));
                }
            }
        }
        currentIndex = 0;
    }

    public ArrayList<Image> getImages() { return images; }

    public Image getCurrentImage() {
        if (images.isEmpty()) return null;
        return images.get(currentIndex);
    }

    public Image nextImage() {
        if (images.isEmpty()) return null;
        currentIndex = (currentIndex + 1) % images.size();
        return images.get(currentIndex);
    }

    public Image prevImage() {
        if (images.isEmpty()) return null;
        currentIndex = (currentIndex - 1 + images.size()) % images.size();
        return images.get(currentIndex);
    }

    public void setImageByIndex(int index) {
        if (index >= 0 && index < images.size()) currentIndex = index;
    }
}
