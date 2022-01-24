package com.danema21.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Bird {
    public static final int GRAVITY = -15;
    public static boolean DEAD = false;
    private static final int MOVEMENT = 100;
    private static final int MAX_ROTATION = -45;

    private Vector3 position;
    private Vector3 velocity;
    private int rotation;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture spriteSheet;
    private Sound flap;


    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        rotation = 0;
        spriteSheet = new Texture("birdanimation.png");
        birdAnimation = new Animation(spriteSheet, 3, 0.5f);
        bounds = new Rectangle(x, y, spriteSheet.getWidth() / 3, spriteSheet.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0) {
            velocity.add(0, GRAVITY * dt, 0);
            if(rotation > MAX_ROTATION) {
                rotation--;
                for (Sprite sprite : birdAnimation.getFrames()) {
                    sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
                    sprite.setRotation(rotation);
                }
            }
            //position.scl(dt);
        }

        if(!DEAD) {
            position.add(MOVEMENT * dt, velocity.y, 0);
        }else{
            position.add(0, velocity.y, 0);
        }
        //velocity.scl(1/dt);
        if(position.y < 0){
            position.y = 0;
        }

        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Sprite getSprite() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 5;
        rotation = 30;
        for(Sprite sprite : birdAnimation.getFrames()){
            sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
            sprite.setRotation(30);
        }
        flap.play(0.2f);
    }

    public Rectangle getBounds(){
        return  bounds;
    }

    public void dispose(){
        spriteSheet.dispose();
        flap.dispose();
    }
}
