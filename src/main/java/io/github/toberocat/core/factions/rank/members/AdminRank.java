package io.github.toberocat.core.factions.rank.members;

import io.github.toberocat.core.factions.rank.Rank;
import io.github.toberocat.core.utility.Utility;
import io.github.toberocat.core.utility.language.Language;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AdminRank extends Rank {
    public static final String registry = "Admin";

    public AdminRank(int priority) {
        super("Admin", registry,  priority,true);
    }

    @Override
    public String description(Player player) {
        return Language.getMessage("rank.admin.description", player);
    }

    @Override
    public ItemStack getItem(Player player) {
        return Utility.getSkull("https://textures.minecraft.net/texture/a890f37539e2d64fc9115ad05a4d2e9c76c10e5749ecdc2d4ad555fe46b63774", 1,
                Language.getMessage("rank.admin.title", player),
                Language.getLore("rank.admin.lore", player));
    }
}
