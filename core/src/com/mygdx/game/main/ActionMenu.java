package com.mygdx.game.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.block.Block;
import com.mygdx.game.content.particle.FlameSpawn;
import com.mygdx.game.method.Keyboard;
import com.mygdx.game.particle.ParticleV1;

import static com.mygdx.game.main.Main.*;

public class ActionMenu extends ActionGame {
    private int i;
    private int timer = 0;

    @Override
    public void action() {
        Main.RC.method();
        if (Keyboard.PressW) {
            Main.RC.y += 10;
        }
        if (Keyboard.PressS) {
            Main.RC.y -= 10;
        }
        if (Keyboard.PressA) {
            Main.RC.x -= 10;
        }
        if (Keyboard.PressD) {
            Main.RC.x += 10;
        }
        try {
            if (timer <= 0) {

                if (Keyboard.LeftMouse) {
                    Main.FlameSpawnList.add(new FlameSpawn((float) (Keyboard.MouseX / Zoom + RC.x2), (float) (Keyboard.MouseY / Zoom + RC.y2)));
                    timer = 60;


                }
                if (Keyboard.RightMouse) {
                    Vector2 mousePos = new Vector2(Keyboard.MouseX / Zoom + RC.x2, Keyboard.MouseY / Zoom + RC.y2);
                    bloodParticleController.createParticle(mousePos);
                    // main.Main.bang_obj.add(new particle.bang(mouse_x,mouse_y,new Color(236,124,38),12));
                    // Main.LiquidList.add(new Acid(mousePos,24 + rand.rand(8)));
                    // main.Main.liquid_obj.add(new particle.acid(mouse_x/1.23,mouse_y/1.23));
                    // main.Main.liquid_obj.add(new particle.acid(mouse_x/1.23,mouse_y/1.23));
                    // main.Main.liquid_obj.add(new particle.acid(mouse_x/1.23,mouse_y/1.23));

                }
            } else {
                timer -= 1;
            }
        } catch (Exception ignored) {

        }
        if (flame_spawn_time > 0) {
            flame_spawn_time -= 1;
        }
        Batch.begin();
        Render.begin(ShapeRenderer.ShapeType.Filled);
        Main.RC.render_block();
        Batch.end();

        acidParticleController.update(Gdx.graphics.getDeltaTime());
        acidParticleController.render(Render);
        bloodParticleController.update(Gdx.graphics.getDeltaTime());
        bloodParticleController.render(Render);

        for (i = 0; i < Main.LiquidList.size(); i++) {
            ParticleV1 particleV1 = LiquidList.get(i);
            particleV1.allAction(i);
            particleV1.update(Gdx.graphics.getDeltaTime());
            particleV1.render();
        }
        for (i = 0; i < Main.FlameStaticList.size(); i++) {
            ParticleV1 particleV1 = FlameStaticList.get(i);
            particleV1.allAction(i);
            particleV1.update(Gdx.graphics.getDeltaTime());
            particleV1.render();
        }
        for (i = 0; i < Main.FlameList.size(); i++) {
            ParticleV1 particleV1 = FlameList.get(i);
            particleV1.allAction(i);
            particleV1.update(Gdx.graphics.getDeltaTime());
            particleV1.render();
        }
        for (i = 0; i < Main.FlameParticleList.size(); i++) {
            ParticleV1 particleV1 = FlameParticleList.get(i);
            particleV1.allAction(i);
            particleV1.update(Gdx.graphics.getDeltaTime());
            particleV1.render();
        }
        for (i = 0; i < Main.bulletList.size(); i++) {
            if (Main.bulletList.get(i).height == 1) {
                Main.bulletList.get(i).update();
            }
        }
        Render.end();
        Batch.begin();
        Render.begin(ShapeRenderer.ShapeType.Filled);
        for (i = 0; i < Main.SoldatList.size(); i++) {
            Main.SoldatList.get(i).all_action_client(i);
        }
        for (i = 0; i < Main.FlameSpawnList.size(); i++) {
            Main.FlameSpawnList.get(i).allAction(i);
        }
        for (i = 0; i < PlayerList.size(); i++) {
            Main.PlayerList.get(i).all_action_client_2(i);
        }


        for (i = 0; i < Main.DebrisList.size(); i++) {
            Main.DebrisList.get(i).all_action_client(i);
        }
        for (i = 0; i < Main.EnemyList.size(); i++) {
            Main.EnemyList.get(i).all_action_client(i);
        }
        RC.BuildingIteration();
        Batch.draw(textureBuffer, -20, 1, 1, 1);
        Render.end();
        Render.begin(ShapeRenderer.ShapeType.Filled);

        for (i = 0; i < Main.bulletList.size(); i++) {
            if (Main.bulletList.get(i).height == 2) {
                Main.bulletList.get(i).update();
            }
        }
        for (i = 0; i < PlayerList.size(); i++) {
            PlayerList.get(i).update();
        }
        for (i = 0; i < EnemyList.size(); i++) {
            EnemyList.get(i).update();
        }
        for (i = 0; i < ButtonList.size(); i++) {
            if (Main.ConfigMenu == ButtonList.get(i).ConfigMenu) {
                ButtonList.get(i).render(i);
            }
        }
        for (i = 0; i < AirList.size(); i++) {
            for (int i2 = 0; i2 < AirList.get(i).size(); i2++) {
                AirList.get(i).get(i2).all_action();
            }
        }
        for (i = 0; i < Main.BangList.size(); i++) {
            Main.BangList.get(i).allAction(i);
        }
        Render.end();
        Batch.end();
        Batch.begin();
        for (i = 0; i < ButtonList.size(); i++) {
            if (Main.ConfigMenu == ButtonList.get(i).ConfigMenu & !ButtonList.get(i).TypeFont) {
                ButtonList.get(i).TXTRender();
            }
        }
        for (i = 0; i < ButtonList.size(); i++) {
            if (Main.ConfigMenu == ButtonList.get(i).ConfigMenu & ButtonList.get(i).TypeFont) {
                ButtonList.get(i).TXTRender2();
            }
        }
        if (flame_spawn_time <= 0) {
            flame_spawn_time = flame_spawn_time_max;
        }
        Batch.end();
        if (GameStart) {
            PacketServer = new PackerServer();
            PacketClient = new Packet_client();
            if (GameHost) {
                try {
                    serverMain = new ServerMain();
                    serverMain.create();
                    ActionGame = new ActionGameHost();
                    Block.passability_detected();
                    Main.SpawnPlayer.SpawnPlayer(true);
                    KeyboardObj.zoom_const();


                } catch (Exception e) {
                    // throw new RuntimeException(e);
                }
            } else {
                try {
                    Main_client = new ClientMain();
                    Main_client.create();
                    ActionGame = new ActionGameClient();
                    KeyboardObj.zoom_const();
                } catch (Exception e) {
                    // throw new RuntimeException(e);
                }
            }
        }
        Keyboard.LeftMouseClick = false;
        CycleDayNight.WorkTime();
    }


}
