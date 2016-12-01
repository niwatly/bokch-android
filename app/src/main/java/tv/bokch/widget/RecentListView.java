package tv.bokch.widget;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import tv.bokch.R;
import tv.bokch.data.BookViewHolder;
import tv.bokch.data.History;
import tv.bokch.data.ReviewViewHolder;
import tv.bokch.data.UserViewHolder;

public abstract class RecentListView extends BaseListView<History> {

	public RecentListView(Context context) {
		super(context);
		initialize(context);
	}
	public RecentListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context);
	}
	public RecentListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize(context);
	}

	private void initialize(Context context) {
	}

	@Override
	protected void onCellClick(History history) {
		//startReviewActivity(history);
	}

	protected class RecentCell extends Cell {
		protected BookViewHolder mBook;
		protected UserViewHolder mUser;
		protected ReviewViewHolder mReview;
		protected TextView mCreated;
		
		private boolean mDisableBookClick;
		private boolean mDisableUserClick;

		public RecentCell(View view) {
			super(view);
			mBook = new BookViewHolder(view);
			mUser = new UserViewHolder(view);
			mReview = new ReviewViewHolder(view);
			mCreated = (TextView)view.findViewById(R.id.created);
		}

		public void bindView(final History history, int position) {
			super.bindView(history, position);
			mBook.bindView(history.book);
			if (!mDisableBookClick) {
				mBook.setOnJacketClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						startBookActivity(history.book);
					}
				});
			}
			mUser.bindView(history.user);
			if (!mDisableUserClick) {
				mUser.setOnIconClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						startUserActivity(history.user);
					}
				});
			}
			
			mReview.bindView(history.review);
			if (mCreated != null) {
				mCreated.setText(DateFormat.format("yyyy/MM/dd", history.created * 1000));
			}
		}
		
		public void disableBookClick() {
			mDisableBookClick = true;
		}
		public void disableUserClick() {
			mDisableUserClick = true;
		}
	}
}
