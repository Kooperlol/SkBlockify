package codes.kooper.skblockify.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.Blockify;
import codes.kooper.blockify.models.Stage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Stage")
@Description("Get a stage by name")
@Examples("set {_stage} to stage \"lobby\"")
@Since("1.0.0")
public class ExprStage extends SimpleExpression<Stage> {
    private Expression<String> stage;

    static {
        Skript.registerExpression(ExprStage.class, Stage.class, ExpressionType.SIMPLE, "stage %stage%");
    }

    @Override
    protected Stage @NotNull [] get(@NotNull Event event) {
        return new Stage[]{Blockify.getInstance().getStageManager().getStage(stage.getSingle(event))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Stage> getReturnType() {
        return Stage.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Stage with expression: " + stage.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<String>) expressions[0];
        return stage != null;
    }

}
