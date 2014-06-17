package com.adrielcafe.planetasustentavel;

import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainAdapter extends ArrayAdapter<String[]> {

	public MainAdapter(Context context, List<String[]> objects) {
		super(context, R.layout.main_item, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String[] item = getItem(position);
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.main_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.image.setImageResource(Integer.parseInt(item[0]));
		viewHolder.title.setText(item[1]);

		return convertView;
	}

	private class ViewHolder {
		ImageView image;
		TextView title;
	}
	
}