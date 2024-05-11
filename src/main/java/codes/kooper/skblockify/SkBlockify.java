package codes.kooper.skblockify;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

@Getter
public final class SkBlockify extends JavaPlugin {

    public static SkBlockify instance;
    private SkriptAddon addon;

    @Override
    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("codes.kooper.skblockify", "elements");
        } catch (IOException e) {
            getLogger().severe("SkBlockify failed to load classes!");
        }
        getLogger().info("SkBlockify has been enabled!");
    }
}
