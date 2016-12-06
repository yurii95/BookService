
package kozachok.jury.bookservice.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolumeInfo {
    private String title;
    private String subtitle;
    private List<String> authors = new ArrayList<>();
    private String publisher;
    private String publishedDate;
    private String description;
    private Integer pageCount;
    private String printType;
    private List<String> categories = new ArrayList<>();
    private String maturityRating;
    private Boolean allowAnonLogging;
    private String contentVersion;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
    private ImageLinks imageLinks;

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", description='" + description + '\'' +
                ", pageCount=" + pageCount +
                ", printType='" + printType + '\'' +
                ", categories=" + categories +
                ", maturityRating='" + maturityRating + '\'' +
                ", allowAnonLogging=" + allowAnonLogging +
                ", contentVersion='" + contentVersion + '\'' +
                ", language='" + language + '\'' +
                ", previewLink='" + previewLink + '\'' +
                ", infoLink='" + infoLink + '\'' +
                ", canonicalVolumeLink='" + canonicalVolumeLink + '\'' +
                '}';
    }


    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 
     * @param subtitle
     *     The subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 
     * @return
     *     The authors
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * 
     * @param authors
     *     The authors
     */
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    /**
     * 
     * @return
     *     The publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 
     * @param publisher
     *     The publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 
     * @return
     *     The publishedDate
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * 
     * @param publishedDate
     *     The publishedDate
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The readingModes
     */

    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * 
     * @param pageCount
     *     The pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 
     * @return
     *     The printType
     */
    public String getPrintType() {
        return printType;
    }

    /**
     * 
     * @param printType
     *     The printType
     */
    public void setPrintType(String printType) {
        this.printType = printType;
    }

    /**
     * 
     * @return
     *     The categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The maturityRating
     */
    public String getMaturityRating() {
        return maturityRating;
    }

    /**
     * 
     * @param maturityRating
     *     The maturityRating
     */
    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    /**
     * 
     * @return
     *     The allowAnonLogging
     */
    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }

    /**
     * 
     * @param allowAnonLogging
     *     The allowAnonLogging
     */
    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    /**
     * 
     * @return
     *     The contentVersion
     */
    public String getContentVersion() {
        return contentVersion;
    }

    /**
     * 
     * @param contentVersion
     *     The contentVersion
     */
    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    /**
     * 
     * @return
     *     The imageLinks
     */

    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @return
     *     The previewLink
     */
    public String getPreviewLink() {
        return previewLink;
    }

    /**
     * 
     * @param previewLink
     *     The previewLink
     */
    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    /**
     * 
     * @return
     *     The infoLink
     */
    public String getInfoLink() {
        return infoLink;
    }

    /**
     * 
     * @param infoLink
     *     The infoLink
     */
    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    /**
     * 
     * @return
     *     The canonicalVolumeLink
     */
    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    /**
     * 
     * @param canonicalVolumeLink
     *     The canonicalVolumeLink
     */
    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }


}
