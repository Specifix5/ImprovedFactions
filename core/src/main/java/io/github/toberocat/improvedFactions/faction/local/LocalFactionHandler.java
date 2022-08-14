package io.github.toberocat.improvedFactions.faction.local;

import io.github.toberocat.improvedFactions.exceptions.faction.FactionNotInStorage;
import io.github.toberocat.improvedFactions.faction.components.rank.members.FactionRank;
import io.github.toberocat.improvedFactions.faction.handler.FactionHandlerInterface;
import io.github.toberocat.improvedFactions.handler.ImprovedFactions;
import io.github.toberocat.improvedFactions.player.FactionPlayer;
import io.github.toberocat.improvedFactions.player.OfflineFactionPlayer;
import io.github.toberocat.improvedFactions.utils.FileAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

// ToDo: Implement everything
public class LocalFactionHandler implements FactionHandlerInterface<LocalFaction> {
    private final Map<String, LocalFaction> factions;
    private final FileAccess access;

    public LocalFactionHandler() {
        this.factions = new HashMap<>();
        access = new FileAccess(ImprovedFactions.api().getDataFolder());
    }

    @Override
    public @NotNull LocalFaction create(@NotNull String display, @NotNull FactionPlayer<?> owner) {
        LocalFaction faction = new LocalFaction(display, owner);
        factions.put(faction.getRegistry(), faction);

        return faction;
    }

    @Override
    public @NotNull LocalFaction load(@NotNull String registry) throws FactionNotInStorage {
        if (!access.has(FileAccess.FACTION_FOLDER, registry + ".json")) throw
                new FactionNotInStorage(registry, FactionNotInStorage.StorageType.LOCAL_FILE);

        try {
            return access.read(LocalFaction.class, FileAccess.FACTION_FOLDER, registry + ".json");
        } catch (IOException e) {
            throw new FactionNotInStorage(registry, FactionNotInStorage.StorageType.LOCAL_FILE);
        }
    }

    @Override
    public boolean isLoaded(@NotNull String registry) {
        return factions.containsKey(registry);
    }

    @Override
    public boolean exists(@NotNull String registry) {
        return access.has(FileAccess.FACTION_FOLDER, registry);
    }

    @Override
    public @NotNull Map<String, LocalFaction> getLoadedFactions() {
        return factions;
    }

    @Override
    public @NotNull Stream<String> getAllFactions() {
        return Arrays.stream(access.list(FileAccess.FACTION_FOLDER));
    }

    @Override
    public void deleteCache(@NotNull String registry) {
        factions.remove(registry);
    }

    @Override
    public @NotNull FactionRank getSavedRank(@NotNull OfflineFactionPlayer<?> player) {
        return null;
    }

    @Override
    public @Nullable String getPlayerFaction(@NotNull OfflineFactionPlayer<?> player) {
        return null;
    }

    @Override
    public @Nullable String getPlayerFaction(@NotNull FactionPlayer<?> player) {
        return null;
    }

    @Override
    public boolean isInFaction(@NotNull OfflineFactionPlayer<?> player) {
        return false;
    }

    @Override
    public boolean isInFaction(@NotNull FactionPlayer<?> player) {
        return false;
    }

    /**
     * The faction cache is responsible for quick access of factions for players.
     * But if the faction gets deleted, this cache needs to get removed, else it will
     * wrongly display commands and crash the system trying to load the not existing faction
     *
     * @param player The player that should get the faction cache removed
     */
    @Override
    public void removeFactionCache(@NotNull FactionPlayer<?> player) {

    }
}
