package com.flaming.tutorial.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;
import java.util.List;

public class ParticleCommand extends Command {
    public ParticleCommand() {
        super("particle");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            FormWindowCustom formWindowCustom = new FormWindowCustom("Particle Selector");
            List<String> particleOptions = new ArrayList<>();
            particleOptions.add(TextFormat.GREEN + "Happy Villager");
            particleOptions.add(TextFormat.RED + "Angry Villager");
            formWindowCustom.addElement(new ElementDropdown("Particle", particleOptions));
            formWindowCustom.addElement(new ElementInput("Amount of Particles"));
            player.showFormWindow(formWindowCustom);
        }
        return false;
    }
}
