package com.mygdx.game.particle;

import com.mygdx.game.utils.Alive;

public interface IParticle extends Alive {
    void render();
    void update(float dt);
}
