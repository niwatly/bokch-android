package tv.bokch.data;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import timber.log.Timber;
import tv.bokch.R;

public class ReviewViewHolder {
	private RatingBar mRating;
	private TextView mComment;

	public ReviewViewHolder(View view) {
		mRating = (RatingBar)view.findViewById(R.id.rating);
		mComment = (TextView)view.findViewById(R.id.comment);
	}

	public void bindView(Review review) {
		if (review == null) {
			return;
		}
		Timber.d("tks, review id = %d, user = %s, book = %s, comment = %s", review.id, review.user.userId, review.book.title, review.comment);
		if (mRating != null) {
			mRating.setRating(review.rating);
		}
		if (mComment != null) {
			mComment.setText(review.comment);
		}
	}
}