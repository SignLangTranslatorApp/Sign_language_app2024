package mquinn.sign_language.model;

public class ImageModel {
    private int imageResource;
    private String title;

    public ImageModel(int imageResource, String title) {
        this.imageResource = imageResource;
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }
}
