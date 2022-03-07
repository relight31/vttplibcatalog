package sg.edu.nus.iss.LibCatalog.model;

import java.io.Serializable;
import java.util.Random;

public class Book implements Serializable {
    private String id;
    private String author = "Sample Author";
    private String title = "Sample Title";
    private String thumbnailUrl = "/thumbnails/no_book_cover.jpg";

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book(String author, String title) {
        this.id = generate(8);
        this.author = author;
        this.title = title;
    }

    public Book(String author, String title, String thumbnailUrl) {
        this.id = generate(8);
        this.author = author;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Book() {
        this.id = generate(8);
    }

    /*
     * synchronous methods allow for the method to be called by multiple users
     * concurrently without creating a race condition. It creates a queue of method
     * calls, requests served one by one.
     */

    private synchronized String generate(int numChars) {
        Random r = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < numChars) {
            stringBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return stringBuilder.toString().substring(0, numChars);
    }
}
