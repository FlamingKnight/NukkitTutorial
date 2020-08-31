package com.flaming.tutorial.tasks;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import com.flaming.tutorial.MagmaCore;

public class Announcements implements Runnable {

    @Override
    public void run() {
        for(Player player : MagmaCore.getPlugin().getServer().getOnlinePlayers().values()) {
            player.sendMessage(TextFormat.GREEN + "Announcement: We have a list/plan of tutorials");
        }
    }
}
