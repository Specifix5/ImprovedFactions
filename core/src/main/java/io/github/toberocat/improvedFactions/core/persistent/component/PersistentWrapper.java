package io.github.toberocat.improvedFactions.core.persistent.component;

import io.github.toberocat.improvedFactions.core.persistent.PersistentData;
import io.github.toberocat.improvedFactions.core.persistent.PersistentHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PersistentWrapper {

    private final UUID id;
    private final PersistentData handle;

    public PersistentWrapper(@NotNull UUID id) {
        this.id = id;
        this.handle = PersistentHandler.api();
    }

    public @Nullable String set(@NotNull String key, @NotNull String value) {
        return handle.set(id, key, value);
    }

    public @Nullable String remove(@NotNull String key) {
        return handle.remove(id, key);
    }

    public @Nullable String get(@NotNull String key) {
        return handle.get(id, key);
    }

    public boolean has(@NotNull String key) {
        return handle.has(id, key);
    }
}