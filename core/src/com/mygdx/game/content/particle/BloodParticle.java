package com.mygdx.game.content.particle;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.main.Main;
import com.mygdx.game.particle.ParticleV1;
import com.mygdx.game.utils.Alive;

import static com.mygdx.game.data.Colors.Blood;


public class BloodParticle extends ParticleV1 implements Alive {
    private static final float decaySpeed = 18.f;
    private float timeToLive;

    public BloodParticle(Vector2 position, float radius, float timeToLive) {
        super(position);
        this.radius = radius;
        this.timeToLive = timeToLive;
        this.color = Blood;
        this.interval_rise_size = 0.02f;
    }

    @Override
    public void render() {
        // float[] xy = Main.RC.render_objZoom(this.position.x, this.position.y);
        // Main.Render.setColor(color.withAlpha(0.1f));
        // Main.Render.circle(xy[0], xy[1], (int) (radius * Main.Zoom), (int) radius);
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
