package com.adrielcafe.planetasustentavel;

import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.text.Html;
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

public class CategoryAdapter extends ArrayAdapter<String[]> {
	
	public CategoryAdapter(Context context, List<String[]> objects) {
		super(context, R.layout.category_item, objects);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		String[] item = getItem(position);
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.share = (ImageView) convertView.findViewById(R.id.share);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.title.setText(Html.fromHtml(item[0]));
		viewHolder.share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] item = getItem(position);
				String text = "DICA DE SUSTENTABILIDADE:<br>" + item[0] + "<br>" + item[1] + "<br><br>" + Util.GOOGLE_PLAY_URL;
				Util.share(getContext(), text);
			}
		});
		
		return convertView;
	}

	private class ViewHolder {
		TextView title;
		ImageView share;
	}
	
}