package kozachok.jury.bookservice;

import java.util.List;

import kozachok.jury.bookservice.data.BookItem;

public interface IBooksView {
    void onBooksLoaded(List<BookItem> books);
    void showRefreshButton();
}
