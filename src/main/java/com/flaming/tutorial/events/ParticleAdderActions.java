package com.flaming.tutorial.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.level.ParticleEffect;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.TextFormat;

public class ParticleAdderActions implements Listener {

    @EventHandler
    public void onFormRespond(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        if(event.getWindow().wasClosed()) return;
        if(event.getWindow() instanceof FormWindowCustom) {
            FormWindowCustom formWindowCustom = (FormWindowCustom) event.getWindow();
            if(formWindowCustom.getTitle().equals("Particle Selector")) {
                String particleType = formWindowCustom.getResponse().getDropdownResponse(0).getElementContent();
                String amountOfParticleString = formWindowCustom.getResponse().getInputResponse(1);
                int amountOfParticles = 0;
                try {
                    amountOfParticles = Integer.parseInt(amountOfParticleString);
                } catch (Exception ignored) {
                    player.sendMessage(TextFormat.RED + "Please enter a valid number!");
                    return;
                }
                if(particleType.equals(TextFormat.GREEN + "Happy Villager")) {
                    for(int i = 0; i < amountOfParticles; i++) {
                        player.getLevel().addParticleEffect(getRandomishLocationForParticle(player), ParticleEffect.VILLAGER_HAPPY);
                    }
                } else {
                    for(int i = 0; i < amountOfParticles; i++) {
                        player.getLevel().addParticleEffect(getRandomishLocationForParticle(player), ParticleEffect.VILLAGER_ANGRY);
                    }
                }
            }
        }
    }

    public Vector3 getRandomishLocationForParticle(Player player) {
        double x = 0d;
        double y = 0d;
        double z = 0d;
        x += Math.random()*2-1d;
        y += Math.random()*2-1d;
        z += Math.random()*2-1d;
        return new Vector3(player.getX()+x, player.getY()+y, player.getZ()+z);
    }
}
