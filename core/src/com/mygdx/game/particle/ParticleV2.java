package com.mygdx.game.particle;

import com.badlogic.gdx.math.Vector2;

public abstract class ParticleV2 implements IParticle {
    protected Vector2 position;
    protected Vector2 velocity;
    protected float radius;

    public ParticleV2(Vector2 position, float radius) {
        this.position = position;
        this.radius = radius;
        this.velocity = new Vector2(0.f, 0.f);
    }

}
