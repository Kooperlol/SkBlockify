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

@Name("Remove Blocks")
@Description("Removes blocks from a view.")
@Examples("on blockify break: remove blocks from (locations of diamond block in player's chunk) from view event-view")
@Since("1.0.0")
public class EffRemoveBlocks extends AsyncEffect {
    private Expression<Location> locations;
    private Expression<View> view;

    static {
        Skript.registerEffect(EffRemoveBlocks.class, "remove blocks [from] %locations% from [view] %view%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Set<BlockifyPosition> positions = new HashSet<>();
        for (Location location : this.locations.getAll(event)) {
            positions.add(BlockifyPosition.fromLocation(location));
        }
        View view = this.view.getSingle(event);
        if (positions.isEmpty() || view == null) {
            return;
        }
        view.removeBlocks(positions);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Remove blocks to view with expression location(s): " + locations.toString(event, debug) + " and view: " + view.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        locations = (Expression<Location>) expressions[0];
        view = (Expression<View>) expressions[1];
        return true;
    }

}
