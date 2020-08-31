package com.flaming.tutorial.events;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;

public class BlockBreakActions implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled();
    }
}
