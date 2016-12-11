package kozachok.jury.bookservice.data;

import java.util.List;

import kozachok.jury.bookservice.data.BookItem;

public class BookResponse {
    private String kind;
    private int totalCount;
    private List<BookItem> items;

    public String getKind() {
        return kind;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<BookItem> getItems() {
        return items;
    }
}
