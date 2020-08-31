package com.flaming.tutorial.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.TextFormat;
import sun.text.resources.cldr.ka.FormatData_ka;

public class JoinActions implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(TextFormat.GRAY + "Welcome to your tutorial server " + TextFormat.GREEN + player.getName() + TextFormat.GRAY + "!");
    }
}
