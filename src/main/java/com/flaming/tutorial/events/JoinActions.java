package com.flaming.tutorial.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.TextFormat;
import com.flaming.tutorial.MagmaCore;
import sun.text.resources.cldr.ka.FormatData_ka;

public class JoinActions implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        MagmaCore.getPlugin().playerRanksHashmaps.putIfAbsent(player.getUniqueId(), MagmaCore.getPlugin().getRankFromID("member"));
        event.setJoinMessage(String.format(MagmaCore.getPlugin().welcomeMessage, event.getPlayer().getName()));
    }
}
