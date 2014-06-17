package com.adrielcafe.planetasustentavel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {
	private List<String[]> dbReeducacaoSustentavel;
	private List<String[]> dbAgua;
	private List<String[]> dbTransporte;
	private List<String[]> dbAlimentacaoSaudavel;
	private List<String[]> dbBebesEcologicos;
	private List<String[]> dbLimpezaHigienePessoal;
	private List<String[]> dbCidadaniaPlanetaria;
	
	private GridView gridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		overridePendingTransition(R.anim.open_activity, R.anim.close_activity);
		getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.greentheme_color)));
		setContentView(R.layout.activity_main);
		
		setup();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_about) {
			Util.showDialog(this, getString(R.string.about), Util.ABOUT_MESSAGE, R.drawable.ic_about);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setup(){
		dbReeducacaoSustentavel = Util.GSON.fromJson(Util.loadJSON(this, "db/1-reeducacao-sustentavel.json"), new TypeToken<List<String[]>>() {}.getType());
		dbAgua = Util.GSON.fromJson(Util.loadJSON(this, "db/2-agua.json"), new TypeToken<List<String[]>>() {}.getType());
		dbTransporte = Util.GSON.fromJson(Util.loadJSON(this, "db/3-transporte.json"), new TypeToken<List<String[]>>() {}.getType());
		dbAlimentacaoSaudavel = Util.GSON.fromJson(Util.loadJSON(this, "db/4-alimentacao-saudavel.json"), new TypeToken<List<String[]>>() {}.getType());
		dbBebesEcologicos = Util.GSON.fromJson(Util.loadJSON(this, "db/5-bebes-ecologicos.json"), new TypeToken<List<String[]>>() {}.getType());
		dbLimpezaHigienePessoal = Util.GSON.fromJson(Util.loadJSON(this, "db/6-limpeza-higiene-pessoal.json"), new TypeToken<List<String[]>>() {}.getType());
		dbCidadaniaPlanetaria = Util.GSON.fromJson(Util.loadJSON(this, "db/7-cidadania-planetaria.json"), new TypeToken<List<String[]>>() {}.getType());
		
		List<String[]> menuItems = new ArrayList<>();
		menuItems.add(new String[]{ R.drawable.menu_item_reeducacao_sustentavel + "", "Reeducação Sustentável"});
		menuItems.add(new String[]{ R.drawable.menu_item_agua + "", "Água"});
		menuItems.add(new String[]{ R.drawable.menu_item_transporte + "", "Transporte"});
		menuItems.add(new String[]{ R.drawable.menu_item_alimentacao_saudavel + "", "Alimentação Saudável"});
		menuItems.add(new String[]{ R.drawable.menu_item_bebes_ecologicos + "", "Bebês Ecológicos"});
		menuItems.add(new String[]{ R.drawable.menu_item_limpeza_higiene_pessoal + "", "Limpeza e Higiene"});
		menuItems.add(new String[]{ R.drawable.menu_item_cidadania_planetaria + "", "Cidadania Planetária"});
		
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new MainAdapter(this, menuItems));
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	String[] item = (String[]) gridView.getAdapter().getItem(position);
				Intent i = new Intent(MainActivity.this, CategoryActivity.class);
				i.putExtra("title", item[1]);
	        	
	            switch (Integer.parseInt(item[0])) {
					case R.drawable.menu_item_reeducacao_sustentavel:
						i.putExtra("db", Util.GSON.toJson(dbReeducacaoSustentavel));
						break;
					case R.drawable.menu_item_agua:
						i.putExtra("db", Util.GSON.toJson(dbAgua));
						break;
					case R.drawable.menu_item_transporte:
						i.putExtra("db", Util.GSON.toJson(dbTransporte));
						break;
					case R.drawable.menu_item_alimentacao_saudavel:
						i.putExtra("db", Util.GSON.toJson(dbAlimentacaoSaudavel));
						break;
					case R.drawable.menu_item_bebes_ecologicos:
						i.putExtra("db", Util.GSON.toJson(dbBebesEcologicos));
						break;
					case R.drawable.menu_item_limpeza_higiene_pessoal:
						i.putExtra("db", Util.GSON.toJson(dbLimpezaHigienePessoal));
						break;
					case R.drawable.menu_item_cidadania_planetaria:
						i.putExtra("db", Util.GSON.toJson(dbCidadaniaPlanetaria));
						break;
					default:
						break;
				}
	            
				startActivity(i);
	        }
	    });
	}
	
}