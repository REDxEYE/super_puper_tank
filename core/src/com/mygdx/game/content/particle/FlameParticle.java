package com.mygdx.game.content.particle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.data.Colors;
import com.mygdx.game.main.Main;
import com.mygdx.game.method.rand;
import com.mygdx.game.particle.ParticleV1;

public class FlameParticle extends ParticleV1 {
    public FlameParticle(float x, float y) {
        super(new Vector2(x, y));
        this.time_delete = 25 + rand.rand(20);
        this.radius = 5 + rand.rand(5);
        this.size_render = (int) (radius * Main.Zoom);
        this.velocity = new Vector2(-6 + rand.rand(12), -6 + rand.rand(12));
        this.velocity.scl(60.f);
        this.color = Colors.Flame;
    }

    public void allAction(int i) {
        decayColor();
        timer(i, Main.FlameParticleList);
    }


    @Override
    public void render() {
        float[] xy = Main.RC.render_objZoom(this.position.x, this.position.y);
        Main.Render.setColor(color.withAlpha(0.4f));
        Main.Render.circle(xy[0], xy[1], size_render, size_render);
    }
}
