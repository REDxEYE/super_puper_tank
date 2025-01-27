package com.mygdx.game.content.particle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.main.Main;
import com.mygdx.game.method.rand;
import com.mygdx.game.particle.ParticleV1;
import com.mygdx.game.utils.ImutColor;


public class Bang extends ParticleV1 {
    public Bang(float x, float y, float size) {
        super(new Vector2(x,y));
        this.radius = size;
        this.size_render = (int) (size * Main.Zoom);
        this.interval_rise_size = 12;
        this.time_delete = 30;
        this.color = new ImutColor((160 + rand.rand(70)) / 255.f, (90 + rand.rand(60)) / 255.f, (20 + rand.rand(3)) / 255.f);
    }

    public void allAction(int i) {
        grow();
        timer(i, Main.BangList);
    }

    @Override
    public void render() {
        float[] xy = Main.RC.render_objZoom(this.position.x, this.position.y);
        Main.Render.setColor(color.withAlpha(0.4f));
        Main.Render.circle(xy[0], xy[1], (int) (radius * Main.Zoom), (int) (radius * Main.Zoom));
    }
}
