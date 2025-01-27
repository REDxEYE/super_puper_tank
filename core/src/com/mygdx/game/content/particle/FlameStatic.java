package com.mygdx.game.content.particle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.data.Colors;
import com.mygdx.game.main.Main;
import com.mygdx.game.method.rand;
import com.mygdx.game.particle.ParticleV1;


public class FlameStatic extends ParticleV1 {
    public FlameStatic(float x, float y) {
        super(new Vector2(x,y));
        this.radius = 14 + rand.rand(10);
        this.size_render = (int) (radius * Main.Zoom);
        this.originalSize = this.radius / 2;
        this.time_delete = 60;
        this.time_spawn_max = 60;
        this.time_spawn = this.time_spawn_max;
        int z = rand.rand(3);
        this.color = switch (z) {
            case 0 -> Colors.Flame;
            case 1 -> Colors.Flame2;
            case 2 -> Colors.Flame3;
            default -> throw new IllegalStateException("Unexpected value: " + z);
        };
    }

    public void allAction(int i) {
        createFlameParticle(Main.FlameParticleList);
        timer(i, Main.FlameStaticList);

    }

    @Override
    public void render() {
        centerRender();
        Main.Render.setColor(color);
        Main.Render.circle(this.x_rend, this.y_rend, size_render, size_render);
    }
}
