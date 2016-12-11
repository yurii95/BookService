
package kozachok.jury.bookservice.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolumeInfo {
    private String title;
    private String previewLink;
    private String infoLink;
    private ImageLinks imageLinks;

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "title='" + title + '\'' + '}';
    }


    public String getTitle() {
        return title;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    @Override
    public boolean equals(Object o) {
        VolumeInfo that = (VolumeInfo) o;

        return title.equals(that.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
