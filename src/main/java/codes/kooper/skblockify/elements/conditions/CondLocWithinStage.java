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
import codes.kooper.blockify.models.Stage;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Location Within Stage")
@Description("Checks if location(s) are within a stage")
@Examples("if location of player is within stage \"lobby\": send \"You're in the lobby!\" to player")
@Since("1.0.0")
public class CondLocWithinStage extends Condition {
    private Expression<Location> location;
    private Expression<Stage> stage;

    static {
        Skript.registerCondition(CondLocWithinStage.class, "location %locations% is within stage %stage%");
    }

    @Override
    public boolean check(@NotNull Event event) {
        return stage.check(event, stage -> {
            for (Location loc : location.getAll(event)) {
                if (!stage.isLocationWithin(loc)) {
                    return false;
                }
            }
            return true;
        });
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "location " + location.toString(event, b) + " is within stage " + stage.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        location = (Expression<Location>) expressions[0];
        stage = (Expression<Stage>) expressions[1];
        return (location != null && stage != null);
    }

}
