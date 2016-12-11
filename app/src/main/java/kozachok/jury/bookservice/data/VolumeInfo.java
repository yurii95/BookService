
package kozachok.jury.bookservice.data;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolumeInfo that = (VolumeInfo) o;

        return title.equals(that.title);

    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    public String getInfoLink() {
        return infoLink;
    }

}
