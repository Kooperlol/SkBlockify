package codes.kooper.skblockify.elements.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.Stage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Hide Audience")
@Description("Hides or shows an audience within a stage")
@Examples({"set audience hidden of {_stage} to true"})
@Since("1.0.4")
public class EffSetAudienceHidden extends Effect {
    private Expression<Stage> stage;
    private Expression<Boolean> hidden;

    @Override
    protected void execute(@NotNull Event event) {
        Stage stage = this.stage.getSingle(event);
        Boolean hidden = this.hidden.getSingle(event);
        if (stage == null || hidden == null) {
            return;
        }
        stage.getAudience().setArePlayersHidden(hidden);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Set audience hidden of stage expression: " + stage.toString(event, debug) + " to hidden expression: " + hidden.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[0];
        hidden = (Expression<Boolean>) expressions[1];
        return (stage != null && hidden != null);
    }

}
