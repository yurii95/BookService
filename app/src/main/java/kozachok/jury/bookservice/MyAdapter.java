package kozachok.jury.bookservice;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kozachok.jury.bookservice.data.BookItem;

/**
 * Created by Юрий on 05.12.2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.BookViewHolder> {
    private List<BookItem> mDataset= new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView book_cover;
        public TextView book_info;

        public BookViewHolder(View v) {
            super(v);
            cv= (CardView) v.findViewById(R.id.card_view);
            book_cover = (ImageView)v.findViewById(R.id.book_cover);
            book_info = (TextView) v.findViewById(R.id.book_info);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<BookItem> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        System.out.println("onCreateHolder");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        BookViewHolder vh = new BookViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        System.out.println("onBindHolderr");
        holder.book_cover.setImageResource(R.drawable.book_cover);
        holder.book_info.setText(mDataset.get(position).getVolumeInfo().getTitle());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        System.out.println("sizeeeee");
        return mDataset.size();
    }
}
