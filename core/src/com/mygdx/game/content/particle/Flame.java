package com.mygdx.game.content.particle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.data.Colors;
import com.mygdx.game.main.Main;
import com.mygdx.game.method.rand;
import com.mygdx.game.particle.ParticleV1;

public class Flame extends ParticleV1 {
    public Flame(float x, float y) {
        super(new Vector2(x, y));
        this.radius = 14 + rand.rand(10);
        this.size_render = (int) (radius * Main.Zoom);
        time_spawn_max = 60;
        this.time_delete = 60;
        super.sizeUpdate();
        int z = rand.rand(3);
        this.color = switch (z) {
            case 0 -> Colors.Flame;
            case 1 -> Colors.Flame2;
            case 2 -> Colors.Flame3;
            default -> throw new IllegalStateException("Unexpected value: " + z);
        };

    }

    public void allAction(int i) {
        // super.flame_physic(i, Main.flame_obj);
        createFlameParticle(Main.FlameParticleList);
        timer(i, Main.FlameList);

    }

    @Override
    public void render() {
        float[] xy = Main.RC.render_objZoom(this.position.x, this.position.y);
        Main.Render.setColor(color);
        Main.Render.circle(xy[0], xy[1], size_render, size_render);
    }
}
