package tv.bokch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import tv.bokch.R;
import tv.bokch.data.Book;

public class BookListView extends BaseListView<Book> {
	
	public BookListView(Context context) {
		super(context);
		initialize(context);
	}
	public BookListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context);
	}
	public BookListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize(context);
	}
	
	private void initialize(Context context) {
	}
	
	
	@Override
	protected int getLayoutResId() {
		return R.layout.cell_book;
	}
	
	@Override
	protected Cell createCell(View view) {
		return new BookCell(view);
	}
	
	@Override
	protected int getFooterResId() {
		return R.layout.cell_footer;
	}

	@Override
	protected int getHeaderResId() {
		return R.layout.cell_header;
	}
	
	protected class BookCell extends Cell {
		private BookView.BookViewHolder mBook;

		public BookCell(View view) {
			super(view);
			mBook = new BookView.BookViewHolder(view);
		}
		
		public void bindView(Book book, int position) {
			mBook.bindView(book);
			super.bindView(book, position);
		}
	}
}
