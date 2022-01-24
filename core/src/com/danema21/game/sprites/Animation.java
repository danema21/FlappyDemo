package com.danema21.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<Sprite> frames;
    public float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(Texture spriteSheet, int frameCount, float cycleTime){
        frames = new Array<Sprite>();
        int frameWidth = spriteSheet.getWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            frames.add(new Sprite(spriteSheet, i * frameWidth, 0, frameWidth, spriteSheet.getHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount){
            frame = 0;
        }
    }

    public Sprite getFrame(){
        return frames.get(frame);
    }

    public Array<Sprite> getFrames(){ return  frames;}
}
