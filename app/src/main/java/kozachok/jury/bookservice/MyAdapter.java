package kozachok.jury.bookservice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import kozachok.jury.bookservice.data.BookItem;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.BookViewHolder> {
    private static List<BookItem> books_list = new ArrayList<>();
    private static Context context;


    public static class BookViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        CardView cv;
        ImageView book_cover;
        TextView book_info;

        public BookViewHolder(View v) {
            super(v);
            cv= (CardView) v.findViewById(R.id.card_view);
            book_cover = (ImageView)v.findViewById(R.id.book_cover);
            book_info = (TextView) v.findViewById(R.id.book_info);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MyAdapter.books_list.
                    get(getAdapterPosition()).getVolumeInfo().getPreviewLink()));
            MyAdapter.context.startActivity(intent);
        }
    }

    public MyAdapter(List<BookItem> list, Context context) {
        books_list = list;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
//        holder.book_cover.setImageResource(R.drawable.photo);
//        System.out.println("Position = "+books_list.get(position).getVolumeInfo().getImageLinks());
//        System.out.println("Position = "+);

//        if(books_list.get(position) != null &&
//                books_list.get(position).getVolumeInfo() != null &&
//                books_list.get(position).getVolumeInfo().getImageLinks() != null &&
//                books_list.get(position).getVolumeInfo().getImageLinks().getThumbnail() != null &&
//                books_list.get(position).getVolumeInfo().getTitle() != null) {


            Picasso.with(context).load(books_list.get(position).getVolumeInfo().
                    getImageLinks().getThumbnail()).placeholder(R.drawable.book_image).
                    into(holder.book_cover);
            holder.book_info.setText(books_list.get(position).getVolumeInfo().getTitle());
//        }
    }

    @Override
    public int getItemCount() {
        return books_list.size();
    }
}
