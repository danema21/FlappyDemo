package com.danema21.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.danema21.game.FlappyDemo;

public class GameOverState extends State{
    private Texture background;
    private Texture gameOver;
    private Texture playBtn;

    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
        playBtn = new Texture("playbtn.png");
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
