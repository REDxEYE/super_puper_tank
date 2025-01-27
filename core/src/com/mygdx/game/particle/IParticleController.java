package com.mygdx.game.particle;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public interface IParticleController {
    IParticle createParticle(Vector2 position);

    void update(float dt);

    void render(ShapeRenderer renderer);
}
