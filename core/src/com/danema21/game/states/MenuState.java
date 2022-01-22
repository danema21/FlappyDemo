package com.danema21.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.danema21.game.FlappyDemo;

import org.graalvm.compiler.replacements.Log;

public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private Rectangle playBtnBounds;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        playBtnBounds = new Rectangle(cam.position.x - (playBtn.getWidth()/2), cam.position.y - (playBtn.getHeight()/2), playBtn.getWidth(), playBtn.getHeight());
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touch = new Vector3(new Vector2(Gdx.input.getX(), Gdx.input.getY()), 0);
            cam.unproject(touch);
            if(playBtnBounds.contains(touch.x, touch.y)) {
                gsm.set(new PlayState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - (playBtn.getWidth() / 2), cam.position.y - (playBtn.getHeight() / 2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
