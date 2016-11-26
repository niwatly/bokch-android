package tv.bokch.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Review extends Data implements Parcelable {
	public Book book;
	public User user;
	public String comment;
	public int rating;
	public long created;
	
	public Review(JSONObject obj) throws JSONException {
		book = new Book(obj.optString("book_id"));
		user = new User(obj.optString("user_id"));
		comment = obj.optString("comment");
		rating = obj.optInt("rating");
		created = obj.optLong("created");
	}

	public Review(Parcel in) {
		book = in.readParcelable(Book.class.getClassLoader());
		user = in.readParcelable(User.class.getClassLoader());
		comment = in.readString();
		rating = in.readInt();
		created = in.readLong();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(book, flags);
		dest.writeParcelable(user, flags);
		dest.writeString(comment);
		dest.writeInt(rating);
		dest.writeLong(created);
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
		public Review createFromParcel(Parcel in) {
			return new Review(in);
		}
		public Review[] newArray(int size) {
			return new Review[size];
		}
	};	
}
