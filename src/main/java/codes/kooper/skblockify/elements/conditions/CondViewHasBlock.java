package codes.kooper.skblockify.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.View;
import codes.kooper.blockify.types.BlockifyPosition;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("View Has Block")
@Description("Check if a view has a block at a location.")
@Examples("if view {_view} has block at player's location:")
@Since("1.0.0")
public class CondViewHasBlock extends Condition {
    private Expression<View> view;
    private Expression<Location> location;

    static {
        Skript.registerCondition(CondViewHasBlock.class, "view %view% has block[s] [at] %locations%");
    }

    @Override
    public boolean check(@NotNull Event event) {
        return view.check(event, view -> {
            Location[] locations = location.getAll(event);
            for (Location loc : locations) {
                BlockifyPosition position = new BlockifyPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
                if (!view.hasBlock(position)) {
                    return false;
                }
            }
            return true;
        });
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Condition has view with view expression " + view.toString(event, debug) + " and location expression " + location.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        view = (Expression<View>) expressions[0];
        location = (Expression<Location>) expressions[1];
        return (view != null && location != null);
    }
}
