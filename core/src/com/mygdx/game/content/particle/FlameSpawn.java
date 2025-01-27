package com.mygdx.game.content.particle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.block.Block;
import com.mygdx.game.main.Main;
import com.mygdx.game.particle.ParticleV1;

import static com.mygdx.game.data.Colors.RGBFlame;

public class FlameSpawn extends ParticleV1 {
    public FlameSpawn(float x, float y) {
        super(new Vector2(x, y));
        this.time_delete = 400;
        grassDelete();
        this.rgb = new float[]{RGBFlame.r, RGBFlame.g, RGBFlame.b};


    }

    public void allAction(int i) {
        spawnFlame();
        soundPlay();
        centerRender();
        Block.LightingAir(x_rend, y_rend, rgb);
        timer(i, Main.FlameSpawnList);
    }

    @Override
    public void render() {

    }
}