package codes.kooper.skblockify.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.AsyncEffect;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.View;
import codes.kooper.blockify.types.BlockifyPosition;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

@Name("Reset Blocks in View")
@Description("Reset certain blocks in a view to a block from its pattern.")
@Examples("reset {_locations::*} in view {_view}")
@Since("1.0.0")
public class EffResetBlocks extends AsyncEffect {
    private Expression<View> view;
    private Expression<Location> location;

    static {
        Skript.registerEffect(EffResetBlocks.class, "reset %locations% in view %view%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        View view = this.view.getSingle(event);
        Location[] locations = this.location.getAll(event);
        if (view == null || locations.length == 0) {
            return;
        }
        Set<BlockifyPosition> positions = new HashSet<>();
        for (Location location : locations) {
            positions.add(new BlockifyPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        }
        view.resetBlocks(positions);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Reset blocks in view with expression view: " + view.toString(event, debug) + " and expression location: " + location.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        location = (Expression<Location>) expressions[0];
        view = (Expression<View>) expressions[1];
        return (location != null && view != null);
    }

}
