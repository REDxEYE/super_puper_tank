package com.mygdx.game.content.particle;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.data.Colors;
import com.mygdx.game.main.Main;
import com.mygdx.game.particle.ParticleV1;
import com.mygdx.game.utils.Alive;


public class AcidParticle extends ParticleV1 implements Alive {
    private static final float decaySpeed = 18.f;

    private float timeToLive;

    public AcidParticle(Vector2 position, float radius, float timeToLive) {
        super(position);
        this.radius = radius;
        this.timeToLive = timeToLive;
        this.color = Colors.Acid;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        this.timeToLive -= dt;
        if (timeToLive <= 0) {
            this.timeToLive = 0;
            this.radius = Math.max(this.radius - decaySpeed * dt, 0);
        }
    }

    @Override
    public void render() {
    }

    public void render(ShapeRenderer renderer) {
        var xy = Main.RC.render_objZoom(this.position);
        renderer.setColor(color.withAlpha(0.1f));
        renderer.circle(xy.x, xy.y, (int) (radius * Main.Zoom), 16);
    }

    @Override
    public boolean isAlive() {
        return radius > 0 || timeToLive > 0;
    }
}
