package tv.bokch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import tv.bokch.R;

public class SummarizedRecentListView extends RecentListView {

	public SummarizedRecentListView(Context context) {
		super(context);
		initialize(context);
	}
	public SummarizedRecentListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context);
	}
	public SummarizedRecentListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize(context);
	}

	private void initialize(Context context) {
		setOrientation(Orientation.Horizontal);
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.cell_summarized_recent;
	}

	@Override
	protected Cell createCell(View view) {
		return new RecentCell(view);
	}

	@Override
	protected int getFooterResId() {
		return R.layout.cell_dummy;
	}

	@Override
	protected Cell createFooter(View view) {
		return new DummyCell(view);
	}

	@Override
	protected int getHeaderResId() {
		return R.layout.cell_dummy;
	}

	@Override
	protected Cell createHeader(View view) {
		return new DummyCell(view);
	}
}