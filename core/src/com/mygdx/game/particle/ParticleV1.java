package com.mygdx.game.particle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.block.UpdateRegister;
import com.mygdx.game.content.particle.Flame;
import com.mygdx.game.content.particle.FlameParticle;
import com.mygdx.game.main.Main;
import com.mygdx.game.method.Move;
import com.mygdx.game.method.SoundPlay;
import com.mygdx.game.method.pow2;
import com.mygdx.game.method.rand;
import com.mygdx.game.utils.ImutColor;

import java.util.LinkedList;

import static com.mygdx.game.method.Option.SoundConst;
import static com.mygdx.game.method.pow2.pow2;
import static java.lang.StrictMath.atan2;
import static java.lang.StrictMath.sqrt;

public abstract class ParticleV1 implements IParticle {
    public Vector2 position;
    public Vector2 velocity;
    public float radius;
    public float originalSize;
    public float interval_rise_size;
    protected ImutColor color;
    public static float rad;
    protected int time_delete;
    public static int brightness = 200;
    private int sound_time;
    private static final int sound_time_max = 300;
    public static final ImutColor colorDecay = new ImutColor(3 / 255.f, 3 / 255.f, 1 / 255.f);
    protected int time_spawn, time_spawn_max;
    public int x_rend;
    public int y_rend;
    public int size_render;
    public float[] rgb;

    public ParticleV1(Vector2 position) {
        this.position = position;
        this.velocity = new Vector2(0.f, 0.f);
    }

    protected void timer(int i, LinkedList<ParticleV1> obj) {
        this.time_delete -= 1;
        if (this.time_delete <= 0) {
            obj.remove(i);
        }
    }

    protected void spawnFlame() {
        if (Main.flame_spawn_time <= 0) {
            Flame flame = new Flame((int) ((this.position.x - 20 + rand.rand(40))), (int) ((this.position.y - 20 + rand.rand(40))));
            Main.FlameList.add(flame);
        }
    }

    protected void soundPlay() {
        sound_time += 1;
        if (sound_time_max == sound_time) {
            float[] xy = Main.RC.WindowSynchronization(this.position.x, this.position.y);
            rad = 1 - ((float) sqrt(pow2(xy[0]) + pow2(xy[1])) / SoundConst);
            sound_time = 0;
            if (rad > 0) {
                SoundPlay.sound(Main.ContentSound.flame_sound, rad);
            }
        }
    }

    protected final void grassDelete() {
        int ix = (int) (position.x / Main.width_block) - 1;
        int iy = (int) (position.y / Main.height_block) - 1;
        try {
            if (Main.BlockList2D.get(iy).get(ix).render_block == UpdateRegister.GrassUpdate) {
                Main.BlockList2D.get(iy).get(ix).render_block = UpdateRegister.DirtUpdate;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    protected void sizeUpdate() {
        this.originalSize = this.radius / 2;
    }

    protected void centerRender() {
        var xy = Main.RC.render_objZoom(this.position);
        this.x_rend = (int) xy.x;
        this.y_rend = (int) xy.y;
    }

    protected void grow() {
        this.radius += this.interval_rise_size;
        this.originalSize = this.radius / 2;
    }

    protected void shrink(int i) {
        this.radius -= this.interval_rise_size;
        if (this.radius < 4) {
            Main.LiquidList.remove(i);
        }
        this.originalSize = this.radius / 2;
    }

    protected void decayColor() {
        color = color.sub(colorDecay);
    }


    public void update(float dt) {
        this.position.add(velocity.cpy().scl(dt));
    }

    protected void createFlameParticle(LinkedList<ParticleV1> obj) {
        this.time_spawn -= 1;
        if (this.time_spawn <= 0) {
            obj.add(new FlameParticle(this.position.x, this.position.y));
            this.time_spawn = time_spawn_max;
        }
    }

    public void allAction(int i) {
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
