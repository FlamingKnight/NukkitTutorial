package com.flaming.tutorial;

import cn.nukkit.block.BlockID;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.inventory.ShapedRecipe;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import com.flaming.tutorial.command.FirstCommand;
import com.flaming.tutorial.command.ParticleCommand;
import com.flaming.tutorial.command.SetRank;
import com.flaming.tutorial.events.BlockBreakActions;
import com.flaming.tutorial.events.ChatEvent;
import com.flaming.tutorial.events.JoinActions;
import com.flaming.tutorial.events.ParticleAdderActions;
import com.flaming.tutorial.ranks.PlayerRank;
import com.flaming.tutorial.tasks.Announcements;

import java.util.*;

public class MagmaCore extends PluginBase {

    public static MagmaCore plugin;
    public ArrayList<PlayerRank> playerRanks;
    public HashMap<UUID, PlayerRank> playerRanksHashmaps;
    public String welcomeMessage;

    @Override
    public void onLoad() {
        Item item = Item.get(ItemID.APPLE);
        item.setCustomName(TextFormat.AQUA + "Diamond Apple");
        item.setLore("May not look like much", "But definitely helpful in battles!");

        String[] shape = new String[]{"DDD", "DAD", "DDD"};
        Map<Character, Item> map = new HashMap<>();
        map.put('D', Item.get(BlockID.DIAMOND_BLOCK));
        map.put('A', Item.get(ItemID.APPLE));

        getServer().getCraftingManager().registerRecipe(new ShapedRecipe(item, shape, map, Collections.emptyList()));
        getServer().getCraftingManager().rebuildPacket();
    }

    @Override
    public void onEnable() {
        plugin = this;
        playerRanks = new ArrayList<>();
        playerRanks.add(new PlayerRank("owner", TextFormat.RED + "|OWNER|", 0));
        playerRanks.add(new PlayerRank("admin", TextFormat.RED + "|ADMIN|", 2));
        playerRanks.add(new PlayerRank("mod", TextFormat.GOLD + "|MOD|", 3));
        playerRanks.add(new PlayerRank("paidrank1", TextFormat.GREEN + "|PAID RANK 1|", 4));
        playerRanks.add(new PlayerRank("paidrank2", TextFormat.AQUA + "|PAID RANK 2|", 5));
        playerRanks.add(new PlayerRank("member", TextFormat.AQUA + "|MEMBER|", 6));
        playerRanksHashmaps = new HashMap<>();
        this.getLogger().info(TextFormat.GREEN + "\n\nTutorial Plugin is on!\n\n");
        registerCommands();
        registerEvents();
        registerTasks();

        saveDefaultConfig();
        if(getConfig().getString("WelcomeMessage").equals("")) {
            getConfig().set("WelcomeMessage", "Welcome %s to the tutorial server!");
            getConfig().save();
        }
        welcomeMessage = getConfig().getString("WelcomeMessage");
    }

    public String[] getRankIDs() {
        String[] ranks = new String[playerRanks.size()];
        for(int i = 0; i < playerRanks.size(); i++) {
            ranks[i] = playerRanks.get(i).getRankID();
        }
        return ranks;
    }

    public static MagmaCore getPlugin() {
        return plugin;
    }

    private void registerCommands() {
        SimpleCommandMap commandMap = getServer().getCommandMap();
        commandMap.register("push", new FirstCommand());
        commandMap.register("setrank", new SetRank());
        commandMap.register("particle", new ParticleCommand());
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new JoinActions(), this);
        pluginManager.registerEvents(new BlockBreakActions(), this);
        pluginManager.registerEvents(new ChatEvent(), this);
        pluginManager.registerEvents(new ParticleAdderActions(), this);
    }

    private void registerTasks() {
        final int second = 20;
        final int minute = second*60;
        getServer().getScheduler().scheduleRepeatingTask(this, new Announcements(), minute);
    }

    public PlayerRank getRankFromID(String id) {
        for(PlayerRank playerRank : MagmaCore.getPlugin().playerRanks) {
            if(id.toLowerCase().equals(playerRank.getRankID())) {
                return playerRank;
            }
        }
        return null;
    }

    @Override
    public void onDisable() {
        this.getLogger().info(TextFormat.RED + "\n\nTutorial plugin is off!\n\n");
    }
}
