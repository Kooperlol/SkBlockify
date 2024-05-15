package codes.kooper.skblockify.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.types.BlockifyPosition;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashSet;

@Name("Refresh Blocks To Audience")
@Description("Refreshes blocks to audience in a stage at location(s). Should be called asynchronously if you're refreshing a large amount of blocks.")
@Examples("refresh blocks to audience in stage {_stage} at {_locations::*}")
@Since("1.0.0")
public class EffRefreshBlocksToAudience extends Effect {
    private Expression<Location> locations;
    private Expression<Stage> stage;

    static {
        Skript.registerEffect(EffRefreshBlocksToAudience.class, "refresh block[s] to audience in stage %stage% at %locations%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Stage stageSingle = stage.getSingle(event);
        if (stageSingle == null) return;
        HashSet<BlockifyPosition> positions = new HashSet<>();
        for (Location location : locations.getAll(event)) {
            positions.add(new BlockifyPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        }
        stageSingle.refreshBlocksToAudience(positions);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "send blocks to audience in stage" + stage.toString(event, b) + " at " + locations.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[0];
        locations = (Expression<Location>) expressions[1];
        return (locations != null && stage != null);
    }
}
