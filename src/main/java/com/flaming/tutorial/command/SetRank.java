package com.flaming.tutorial.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import com.flaming.tutorial.MagmaCore;
import com.flaming.tutorial.ranks.PlayerRank;

public class SetRank extends Command {

    public SetRank() {
        super("setrank");
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.TARGET),
                CommandParameter.newType("rank", CommandParamType.STRING)
        });
        commandParameters.put("byString", new CommandParameter[]{
                CommandParameter.newType("player", CommandParamType.TARGET),
                CommandParameter.newEnum("rank", MagmaCore.getPlugin().getRankIDs())
        });
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage("No perms");
            return false;
        }

        if(args.length ==0) {
            commandSender.sendMessage("No player parameter");
            return false;
        }

        Player toGiveRankTo = MagmaCore.getPlugin().getServer().getPlayer(args[0]);
        if(toGiveRankTo == null) {
            commandSender.sendMessage("That isn't a player!");
            return false;
        }

        if(args.length == 1) {
            commandSender.sendMessage("There is no rank parameter");
            return false;
        }

        for(PlayerRank playerRank : MagmaCore.getPlugin().playerRanks) {
            if(args[1].toLowerCase().equals(playerRank.getRankID())) {
                MagmaCore.getPlugin().playerRanksHashmaps.put(toGiveRankTo.getUniqueId(), playerRank);
                commandSender.sendMessage("Gave player that rank!");
                return true;
            }
        }
        return false;
    }
}
