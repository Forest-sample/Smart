package fr.umlv.lastproject.smart;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class represent the SMART item adapter for customize the ListView
 * 
 * @author Fad's
 * 
 */
public class SmartItemAdapter extends ArrayAdapter<ListViewItem> {

	private Context context;

	/**
	 * 
	 * @param context
	 *            : the Activity context
	 * @param resourceId
	 *            : the layout Id
	 * @param items
	 *            : list of item to add
	 */
	public SmartItemAdapter(Context context, int resourceId,
			List<ListViewItem> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	// private view holder class which represent the item componants
	private class SmartHolder {
		ImageView imageView;
		TextView txtTitle;
	}

	/**
	 * @return the item status
	 */
	@Override
	public boolean isEnabled(int position) {
		return this.getItem(position).isEnabled();
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		SmartHolder smartHolder = null;
		ListViewItem item = getItem(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_home_items, null);
			smartHolder = new SmartHolder();
			smartHolder.txtTitle = (TextView) convertView
					.findViewById(R.id.function_name);
			smartHolder.imageView = (ImageView) convertView
					.findViewById(R.id.function_icon);
			convertView.setTag(smartHolder);
		} else {
			smartHolder = (SmartHolder) convertView.getTag();
		}

		if (!item.isEnabled()) {
			convertView.setBackgroundColor(Color.argb(50, 0, 0, 0));
		} else {
			convertView.setBackgroundColor(Color.WHITE);
		}

		smartHolder.txtTitle.setText(item.getTitle());
		smartHolder.imageView.setImageResource(item.getImageId());

		return convertView;
	}
}