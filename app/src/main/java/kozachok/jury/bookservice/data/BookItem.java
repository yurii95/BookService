package kozachok.jury.bookservice.data;

import android.os.Parcel;
import android.os.Parcelable;


public class BookItem implements Parcelable{
    private VolumeInfo volumeInfo;

    protected BookItem(Parcel in) {
    }

    public static final Creator<BookItem> CREATOR = new Creator<BookItem>() {
        @Override
        public BookItem createFromParcel(Parcel in) {
            return new BookItem(in);
        }

        @Override
        public BookItem[] newArray(int size) {
            return new BookItem[size];
        }
    };

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(volumeInfo.getTitle());
        dest.writeString(volumeInfo.getImageLinks().getThumbnail());
        dest.writeString(volumeInfo.getInfoLink());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        return volumeInfo.equals(bookItem.volumeInfo);

    }

    @Override
    public int hashCode() {
        return volumeInfo.hashCode();
    }
}
