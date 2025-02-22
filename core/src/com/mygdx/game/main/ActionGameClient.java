package com.mygdx.game.main;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.content.particle.AcidParticle;
import com.mygdx.game.content.particle.FlameSpawn;
import com.mygdx.game.method.Keyboard;
import com.mygdx.game.method.rand;
import com.mygdx.game.particle.ParticleV1;

import static com.mygdx.game.main.ClientMain.Client;
import static com.mygdx.game.main.Main.*;


public class ActionGameClient extends com.mygdx.game.main.ActionGame {
    public ActionGameClient() {
        Client.sendTCP(Main.SpawnPlayer);

    }

    int i;
    private static int timer = 0;

    public void action() {
        Main.RC.method();
        if (Main.PlayerList.isEmpty()) {
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
                        Main.FlameSpawnList.add(new FlameSpawn(Keyboard.MouseX / Zoom + RC.x2, Keyboard.MouseY / Zoom + RC.y2));
                        timer = 60;


                    }
                    if (Keyboard.RightMouse) {
                        // main.Main.bang_obj.add(new particle.bang(mouse_x,mouse_y,new Color(236,124,38),12));
                        Main.LiquidList.add(new AcidParticle(new Vector2(Keyboard.MouseX / Zoom + RC.x2, Keyboard.MouseY / Zoom + RC.y2), 24 + rand.rand(8),8.f));
                        // main.Main.liquid_obj.add(new particle.acid(mouse_x/1.23,mouse_y/1.23));
                        // main.Main.liquid_obj.add(new particle.acid(mouse_x/1.23,mouse_y/1.23));
                        // main.Main.liquid_obj.add(new particle.acid(mouse_x/1.23,mouse_y/1.23));

                    }
                } else {
                    timer -= 1;
                }
            } catch (Exception ignored) {

            }

        }


        Batch.begin();
        Render.begin(ShapeRenderer.ShapeType.Filled);
        Main.RC.render_block();
        if (flame_spawn_time > 0) {
            flame_spawn_time -= 1;
        }
        Batch.end();
        for (i = 0; i < Main.LiquidList.size(); i++) {
            ParticleV1 particleV1 = LiquidList.get(i);
            particleV1.allAction(i);
            particleV1.update(1.f);
            particleV1.render();
        }
        for (i = 0; i < Main.FlameStaticList.size(); i++) {
            ParticleV1 particleV1 = FlameStaticList.get(i);
            particleV1.allAction(i);
            particleV1.update(1.f);
            particleV1.render();
        }
        for (i = 0; i < Main.FlameList.size(); i++) {
            ParticleV1 particleV1 = FlameList.get(i);
            particleV1.allAction(i);
            particleV1.update(1.f);
            particleV1.render();
        }
        for (i = 0; i < Main.FlameParticleList.size(); i++) {
            ParticleV1 particleV1 = FlameParticleList.get(i);
            particleV1.allAction(i);
            particleV1.update(1.f);
            particleV1.render();
        }
        for (i = 0; i < Main.bulletList.size(); i++) {
            if (Main.bulletList.get(i).height == 1) {
                Main.bulletList.get(i).all_action(i);
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
            if (PlayerList.get(i).host || PlayerList.get(i).nConnect != IDClient) {
                Main.PlayerList.get(i).all_action_client_2(i);
            } else {
                Main.PlayerList.get(i).all_action_client_1(i);
            }
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
                Main.bulletList.get(i).all_action(i);
            }
        }
        for (i = 0; i < PlayerList.size(); i++) {
            PlayerList.get(i).update();
        }
        for (i = 0; i < EnemyList.size(); i++) {
            EnemyList.get(i).update();
        }
        for (i = 0; i < AirList.size(); i++) {
            for (int i2 = 0; i2 < AirList.get(i).size(); i2++) {
                AirList.get(i).get(i2).all_action();
            }
        }
        for (i = 0; i < Main.BangList.size(); i++) {
            Main.BangList.get(i).allAction(i);
        }
        if (flame_spawn_time < 0) {
            flame_spawn_time = flame_spawn_time_max;
        }
        Render.end();
        Batch.end();
        client_packet();
    }

    public static void client_packet() {
        PacketClient.press_w = Keyboard.PressW;
        PacketClient.press_a = Keyboard.PressA;
        PacketClient.press_s = Keyboard.PressS;
        PacketClient.press_d = Keyboard.PressD;
        PacketClient.left_mouse = Keyboard.LeftMouse;
        PacketClient.right_mouse = Keyboard.RightMouse;
        PacketClient.mouse_x = Keyboard.MouseX;
        PacketClient.mouse_y = Keyboard.MouseY;
        PacketClient.IDClient = Main.IDClient;
        Client.sendUDP(PacketClient);
        PacketClient.rot_tower.clear();

    }
}
