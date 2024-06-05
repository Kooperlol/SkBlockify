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
import codes.kooper.blockify.Blockify;
import codes.kooper.blockify.models.View;
import codes.kooper.blockify.types.BlockifyPosition;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@Name("Reset Block Stages")
@Description("Resets the block animation stages at the specified locations in the view.")
@Examples("reset block changes at {_locations::*} in view {_view}")
@Since("1.0.5")
public class EffResetBlockStages extends AsyncEffect {
    private Expression<View> view;
    private Expression<Location> location;

    static {
        Skript.registerEffect(EffResetBlockStages.class, "reset block change[s] at %locations% in view %view%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        View view = this.view.getSingle(event);
        Location[] locations = this.location.getAll(event);
        if (view == null || locations.length == 0) {
            return;
        }
        Blockify.getInstance().getMiningUtils().resetViewBlockAnimation(view, BlockifyPosition.fromLocations(Set.of(locations)));
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Reset block changes at locations: " + location.toString(event, debug) + " in view: " + view.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        location = (Expression<Location>) expressions[0];
        view = (Expression<View>) expressions[1];
        return (location != null && view != null);
    }
}
