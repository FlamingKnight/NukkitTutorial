package com.flaming.tutorial.ranks;

public class PlayerRank {

    public final String prefix;
    public final String rankID;
    public final int permissionLevel;

    public PlayerRank(String rankID, String prefix, int permissionLevel) {
        this.prefix = prefix;
        this.rankID = rankID;
        this.permissionLevel = permissionLevel;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getRankID() {
        return rankID;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }
}
