package com.flaming.tutorial.events;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.event.server.DataPacketSendEvent;
import com.flaming.tutorial.MagmaCore;

public class ChatEvent implements Listener {
    @EventHandler
    public void onSpeak(PlayerChatEvent event) {
        event.setCancelled();
        MagmaCore.getPlugin().getServer().broadcastMessage(
                MagmaCore.getPlugin().playerRanksHashmaps.get(event.getPlayer().getUniqueId()).getPrefix()
                        + " " + event.getPlayer().getName() +
                        ": " + event.getMessage());
    }
}
