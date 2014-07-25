package com.example.planetario;

import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Sphere;
import min3d.vos.Light;
import min3d.vos.TextureVo;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

public class Planetario extends RendererActivity implements OnCompletionListener{

	MediaPlayer player;
	
	
	// -----------------VAriables de objetos 3d

	public Object3dContainer sol;
	public Object3dContainer mercurio;
	public Object3dContainer venus;
	public Object3dContainer tierra;
	public Object3dContainer marte;
	public Object3dContainer jupiter;
	public Object3dContainer saturno;
	public Object3dContainer urano;
	public Object3dContainer neptuno;

	// -----------------------Velocidad al girar

	private final float velocidadMercurio = (float) (Math.PI / 75);
	private final float velocidadVenus = (float) (Math.PI / 100);
	private final float velocidadTierra = (float) (Math.PI / 125);
	private final float velocidadMarte = (float) (Math.PI / 150);
	private final float velocidadJupiter = (float) (Math.PI / 175);
	private final float velocidadSaturno = (float) (Math.PI / 200);
	private final float velocidadUrano = (float) (Math.PI / 225);
	private final float velocidadNeptuno = (float) (Math.PI / 250);

	int contadorMercurio = 0;
	int contadorVenus = 0;
	int contadorTierra = 0;
	int contadorMarte = 0;
	int contadorJupiter = 0;
	int contadorSaturno = 0;
	int contadorUrano = 0;
	int contadorNeptuno = 0;

	Button btn_reproducir;

	
	
	public void initScene()

	{

		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		AssetManager manager = this.getAssets();
	    player = new MediaPlayer();
		try{
			AssetFileDescriptor descriptor = manager.openFd("script.mp3");
			player.setDataSource(descriptor.getFileDescriptor(),
					descriptor.getStartOffset(),descriptor.getLength());
			player.prepare();
			player.start();
			player.setOnCompletionListener(this);
		}catch(Exception e){
			
		}
		
		
		btn_reproducir = (Button) findViewById(R.id.button1);

		scene.lights().add(new Light());
		sol();

		mercurio();
		venus();
		tierra();
		marte();
		jupiter();
		saturno();
		urano();
		neptuno();

	}

	
	@Override
	public void onCompletion(MediaPlayer arg0) {
	player.stop();	
	}
	
	public void updateScene() {
		float x_mercurio = (float) (Math.sin(contadorMercurio % 300
				* velocidadMercurio) * -1.0f);
		float y_mercurio = (float) (Math.cos(contadorMercurio % 300
				* velocidadMercurio) * 1.0f);
		mercurio.position().setAll(x_mercurio, y_mercurio, 0);
		contadorMercurio++;
		System.out.println(contadorMercurio);

		float x_venus = (float) (Math.sin(contadorVenus % 400 * velocidadVenus) * -1.25f);
		float y_venus = (float) (Math.cos(contadorVenus % 400 * velocidadVenus) * 1.25f);
		venus.position().setAll(x_venus, y_venus, 0);
		contadorVenus++;

		float x_tierra = (float) (Math.sin(contadorTierra % 500
				* velocidadTierra) * -1.5f);
		float y_tierra = (float) (Math.cos(contadorTierra % 500
				* velocidadTierra) * 1.5f);
		tierra.position().setAll(x_tierra, y_tierra, 0);
		contadorTierra++;

		float x_marte = (float) (Math.sin(contadorMarte % 600 * velocidadMarte) * -1.75f);
		float y_marte = (float) (Math.cos(contadorMarte % 600 * velocidadMarte) * 1.75f);
		marte.position().setAll(x_marte, y_marte, 0);
		contadorMarte++;

		float x_jupiter = (float) (Math.sin(contadorJupiter % 700
				* velocidadJupiter) * -2.0f);
		float y_jupiter = (float) (Math.cos(contadorJupiter % 700
				* velocidadJupiter) * 2.0f);
		tierra.position().setAll(x_jupiter, y_jupiter, 0);
		contadorJupiter++;

		float x_saturno = (float) (Math.sin(contadorSaturno % 800
				* velocidadSaturno) * -2.25f);
		float y_saturno = (float) (Math.cos(contadorSaturno % 800
				* velocidadSaturno) * 2.25f);
		saturno.position().setAll(x_saturno, y_saturno, 0);
		contadorSaturno++;

		float x_urano = (float) (Math.sin(contadorUrano % 900 * velocidadUrano) * -2.5f);
		float y_urano = (float) (Math.cos(contadorUrano % 900 * velocidadUrano) * 2.5f);
		urano.position().setAll(x_urano, y_urano, 0);
		contadorUrano++;

		float x_neptuno = (float) (Math.sin(contadorNeptuno % 1000
				* velocidadNeptuno) * -2.75f);
		float y_neptuno = (float) (Math.cos(contadorNeptuno % 1000
				* velocidadNeptuno) * 5.75f);
		neptuno.position().setAll(x_neptuno, y_neptuno, 0);
		contadorNeptuno++;

	}

	public void ejecutar(View v) {
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
		Uri data = Uri.parse("/res/raw/fullyalive.mp3");
		intent.setDataAndType(data, "audio/mp3");
		startActivity(intent);
	}

	public void sol() {
		// --------Texturizando una sol------------------------------

		sol = new Sphere(.5f, 16, 32);
		sol.rotation().x = 90.0f;
		scene.addChild(sol);
		sol.texturesEnabled();
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.sol1);
		Shared.textureManager().addTextureId(b, "sol1", true);
		b.recycle();
		TextureVo texture = new TextureVo("sol1");
		sol.textures().add(texture);
	}

	public void mercurio() {
		// --------Texturizando una mercurio------------------------------

		mercurio = new Sphere(.15f, 16, 32);
		mercurio.rotation().x = 90.0f;
		scene.addChild(mercurio);
		mercurio.texturesEnabled();
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.mercurio);
		Shared.textureManager().addTextureId(b, "mercurio", true);
		b.recycle();
		TextureVo texture = new TextureVo("mercurio");
		mercurio.textures().add(texture);
	}

	public void venus() {
		// --------Texturizando una venus------------------------------

		venus = new Sphere(.15f, 16, 32);
		venus.rotation().x = 90.0f;
		scene.addChild(venus);
		venus.texturesEnabled();
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.venus);
		Shared.textureManager().addTextureId(b, "venus", true);
		b.recycle();
		TextureVo texture = new TextureVo("venus");
		venus.textures().add(texture);

	}

	public void tierra() {
		// --------Texturizando una tierra------------------------------

		tierra = new Sphere(.18f, 16, 32);
		tierra.rotation().x = 90.0f;
		scene.addChild(tierra);
		tierra.texturesEnabled();
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.earth);
		Shared.textureManager().addTextureId(b, "earth", true);
		b.recycle();
		TextureVo texture = new TextureVo("earth");
		tierra.textures().add(texture);
	}

	public void marte() {
		// --------Texturizando una marte------------------------------

		marte = new Sphere(.18f, 16, 32);
		marte.rotation().x = 90.0f;
		scene.addChild(marte);
		marte.texturesEnabled();
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.marte);
		Shared.textureManager().addTextureId(b, "marte", true);
		b.recycle();
		TextureVo texture = new TextureVo("marte");
		marte.textures().add(texture);
	}

	public void jupiter() {
		// --------Texturizando una jupiter------------------------------

		jupiter = new Sphere(.21f, 16, 32);
		jupiter.rotation().x = 90.0f;
		scene.addChild(jupiter);
		jupiter.texturesEnabled();
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.jupiter);
		Shared.textureManager().addTextureId(b, "jupiter", true);
		b.recycle();
		TextureVo texture = new TextureVo("jupiter");
		jupiter.textures().add(texture);

	}

	public void saturno() {
		// --------Texturizando una saturno------------------------------

		saturno = new Sphere(.20f, 16, 32);
		saturno.rotation().x = 90.0f;
		scene.addChild(saturno);
		saturno.texturesEnabled();
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.saturno);
		Shared.textureManager().addTextureId(b, "saturno", true);
		b.recycle();
		TextureVo texture = new TextureVo("saturno");
		saturno.textures().add(texture);
	}

	//
	public void urano() {
		// --------Texturizando una urano------------------------------

		urano = new Sphere(.21f, 16, 32);
		urano.rotation().x = 90.0f;
		scene.addChild(urano);
		urano.texturesEnabled();
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.urano);
		Shared.textureManager().addTextureId(b, "urano", true);
		b.recycle();
		TextureVo texture = new TextureVo("urano");
		urano.textures().add(texture);
	}

	public void neptuno() {
		// --------Texturizando una neptuno------------------------------

		neptuno = new Sphere(.21f, 16, 32);
		neptuno.rotation().x = 90.0f;
		scene.addChild(neptuno);
		neptuno.texturesEnabled();
		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.neptuno);
		Shared.textureManager().addTextureId(b, "neptuno", true);
		b.recycle();
		TextureVo texture = new TextureVo("neptuno");
		neptuno.textures().add(texture);
	}

	

}
