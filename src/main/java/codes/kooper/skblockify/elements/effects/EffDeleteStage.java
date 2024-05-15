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
import codes.kooper.blockify.Blockify;
import codes.kooper.blockify.models.Stage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Delete Stage")
@Description("Deletes a stage")
@Examples("delete stage {_stage}")
@Since("1.0.3")
public class EffDeleteStage extends Effect {
    private Expression<Stage> stage;

    static {
        Skript.registerEffect(EffDeleteStage.class, "(delete|remove) [blockify] stage %stage%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Stage stage = this.stage.getSingle(event);
        if (stage == null) return;
        Blockify.getInstance().getStageManager().deleteStage(stage.getName());
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "Delete stage " + stage.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[0];
        return (stage != null);
    }
}
