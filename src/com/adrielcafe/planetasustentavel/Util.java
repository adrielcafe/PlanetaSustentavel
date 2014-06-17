package com.adrielcafe.planetasustentavel;

import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.ShareActionProvider;

import com.google.gson.Gson;

public class Util {
	public static final Gson GSON = new Gson();
	public static final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/details?id=com.adrielcafe.planetasustentavel";
	public static final String ABOUT_MESSAGE = "<b>Planeta Sustentável</b> é um software livre, seu código fonte está disponível no <a href='https://github.com/adrielcafe/PlanetaSustentavel'>GitHub</a>.<br>O conteúdo foi extraído do <a href='http://www.iteia.org.br/textos/almanaque-de-praticas-sustentaveis'>Almanaque de Praticas Sustentáveis</a>, sob licença Creative Commons."
											 + "<br><br>Desenvolvido por:<br>Adriel Café (ac@adrielcafe.com)";
	
	public static String loadJSON(Context context, String path) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            return null;
        }
        return json;
    }
	
	public static void share(Context context, String text){
		Intent i = new Intent();
		i.setType("text/plain");
		i.setAction(Intent.ACTION_SEND);
		i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(text).toString());
		context.startActivity(i);
	}
	
	public static void showDialog(Context context, String title, String message, int icon){
		QustomDialogBuilder dialog = new QustomDialogBuilder(context);
		dialog.setTitleColor(context.getResources().getColor(R.color.greentheme_color));
		dialog.setDividerColor(context.getResources().getColor(R.color.greentheme_color));
		dialog.setTitle(Html.fromHtml(title));
		dialog.setMessage(Html.fromHtml(message));
		dialog.setIcon(icon);
		dialog.show();
	}

}