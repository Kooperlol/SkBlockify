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
import codes.kooper.blockify.models.View;
import codes.kooper.blockify.types.BlockifyPosition;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

@Name("Set Blocks in View")
@Description("Set blocks in a view with a given material.")
@Examples("set blocks at {_locations::*} to stone in view {view}")
@Since("1.0.0")
public class EffSetBlocks extends Effect {
    private Expression<Location> locations;
    private Expression<View> view;
    private Expression<BlockData> blockData;

    static {
        Skript.registerEffect(EffSetBlocks.class, "set blocks at %locations% to %blockdata% in view %view%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Set<BlockifyPosition> positions = new HashSet<>();
        for (Location location : this.locations.getAll(event)) {
            positions.add(BlockifyPosition.fromLocation(location));
        }
        View view = this.view.getSingle(event);
        BlockData blockData = this.blockData.getSingle(event);
        if (positions.isEmpty() || view == null || blockData == null) {
            return;
        }
        view.setBlocks(positions, blockData);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Add blocks to view with expression location(s): " + locations.toString(event, debug) + " and view: " + view.toString(event, debug) + " and blockdata: " + blockData.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        locations = (Expression<Location>) expressions[0];
        blockData = (Expression<BlockData>) expressions[1];
        view = (Expression<View>) expressions[2];
        return (locations != null && blockData != null && view != null);
    }

}
