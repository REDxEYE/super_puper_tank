package com.mygdx.game.content.particle;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.data.Colors;
import com.mygdx.game.method.Move;
import com.mygdx.game.method.rand;
import com.mygdx.game.particle.IParticle;
import com.mygdx.game.particle.IParticleController;
import com.mygdx.game.utils.ObjectPool;

import static java.lang.Math.atan2;

public class AcidParticleController implements IParticleController {
    private final ObjectPool<DecayingParticle> particles = new ObjectPool<>(1000);

    @Override
    public IParticle createParticle(Vector2 position) {
        float radius = 24 + rand.rand(8);
        for (DecayingParticle particle : particles) {
            if (particle.position.dst(position) < radius + particle.radius) {
                {
                    var diff = position.cpy().sub(particle.position).add(new Vector2(rand.random(-0.1f, 0.1f), rand.random(-0.1f, 0.1f)));
                    var r = (float) (atan2(diff.y, diff.x) / 3.14 * 180);
                    position.x += Move.move_sin2(14, r);
                    position.y += Move.move_cos2(14, r);
                }
            }
        }
        DecayingParticle newParticle = new DecayingParticle(position, radius, 8 + rand.random(-2, 2),16.f, Colors.Acid);
        particles.add(newParticle);
        return newParticle;
    }

    @Override
    public void update(float dt) {
        for (DecayingParticle particle : particles) {
            if (particle.isAlive()) {
                particle.update(dt);
            }
        }
    }

    @Override
    public void render(ShapeRenderer renderer) {
        for (DecayingParticle particle : particles) {
            if(particle.isAlive()) {
                particle.render(renderer);
            };
        }
    }


}
