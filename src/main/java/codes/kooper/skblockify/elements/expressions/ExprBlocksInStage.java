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
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.models.View;
import codes.kooper.blockify.types.BlockifyPosition;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Name("Blocks in Stage")
@Description("Get all blocks in a stage as block data.")
@Examples("set {_blocks::*} to all blocks in stage {_stage}")
@Since("1.0.0")
public class ExprBlocksInStage extends SimpleExpression<BlockData> {
    private SimpleExpression<Stage> stage;

    static {
        Skript.registerExpression(ExprBlocksInStage.class, BlockData.class, ExpressionType.SIMPLE, "[get|get all|all] blocks in stage %stage%");
    }

    @Override
    protected BlockData @NotNull [] get(@NotNull Event event) {
        Stage stage = this.stage.getSingle(event);
        if (stage == null) return new BlockData[0];
        ArrayList<BlockData> blocks = new ArrayList<>();
        for (View view : stage.getViews()) {
            for (ConcurrentHashMap<BlockifyPosition, BlockData> values : view.getBlocks().values()) {
                blocks.addAll(values.values());
            }
        }
        return blocks.toArray(new BlockData[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends BlockData> getReturnType() {
        return BlockData.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "Blocks in view with stage expression " + stage.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (SimpleExpression<Stage>) expressions[0];
        return stage != null;
    }

}
