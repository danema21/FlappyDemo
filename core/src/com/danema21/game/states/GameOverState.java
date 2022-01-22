package com.danema21.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.danema21.game.FlappyDemo;

public class GameOverState extends State{
    private Texture background;
    private Texture gameOver;
    private Texture playBtn;
    private Rectangle playBtnBounds;

    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
        playBtn = new Texture("playbtn.png");
        playBtnBounds = new Rectangle(cam.position.x - (playBtn.getWidth()/2), cam.position.y - (playBtn.getHeight()/2), playBtn.getWidth(), playBtn.getHeight());
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touch = new Vector3(new Vector2(Gdx.input.getX(), Gdx.input.getY()), 0);
            cam.unproject(touch);
            if(playBtnBounds.contains(touch.x, touch.y)){
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
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(gameOver, cam.position.x - (gameOver.getWidth()/2), cam.position.y + playBtn.getHeight());
        sb.draw(playBtn, cam.position.x - (playBtn.getWidth()/2), cam.position.y - (playBtn.getHeight()/2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
        playBtn.dispose();
    }
}
