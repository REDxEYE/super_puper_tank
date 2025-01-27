package com.mygdx.game.transport.SpawnPlayer;

import com.mygdx.game.content.Transport.Transport.PlayerCannonBullTank;
import com.mygdx.game.main.Main;

public class SpawnPlayerCannonBull extends PlayerSpawnData{
    public void SpawnPlayer(boolean host){
        Main.PlayerList.add(new PlayerCannonBullTank(200,200,Main.PlayerList,host));
    }
}
