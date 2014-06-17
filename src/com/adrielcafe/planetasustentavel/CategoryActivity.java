package com.adrielcafe.planetasustentavel;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

public class CategoryActivity extends ListActivity {
	private List<String[]> db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		overridePendingTransition(R.anim.open_activity, R.anim.close_activity);
		getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.greentheme_color)));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_category);

		setup(getIntent());
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.open_activity, R.anim.close_activity);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
		}
		return false;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String[] item = (String[]) getListAdapter().getItem(position);
		
		if(!item[1].isEmpty())
			Util.showDialog(this, item[0], item[1], R.drawable.ic_launcher);
	}
	
	private void setup(final Intent intent) {
		db = Util.GSON.fromJson(intent.getStringExtra("db"), new TypeToken<List<String[]>>() {}.getType());
		
		setTitle(intent.getStringExtra("title"));
		setListAdapter(new CategoryAdapter(this, db));
	}
}