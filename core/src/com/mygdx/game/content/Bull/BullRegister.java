package com.mygdx.game.content.Bull;

import com.mygdx.game.bullet.Bullet;

import java.util.ArrayList;

public class BullRegister {
    public static ArrayList<Bullet> register_bullet = new ArrayList<>();
    public static ArrayList<BullPacket> PacketBull = new ArrayList<>();
    public BullRegister() {
        register_bullet.add(new BulletFlame(0,0,0,0,0,0, (byte) 0, (byte) 0));
        register_bullet.add(new BulletAcid(0,0,0,0,0,(byte)0,(byte)0));
        register_bullet.add(new BulletTank(0,0,0,0,0,(byte)0,(byte)0));
        register_bullet.add(new BulletFragment(0,0,0,0,(byte)0));
        register_bullet.add(new BulletMortar(0,0,0,0,0,0,0,(byte)0,(byte)0));



    }

}
