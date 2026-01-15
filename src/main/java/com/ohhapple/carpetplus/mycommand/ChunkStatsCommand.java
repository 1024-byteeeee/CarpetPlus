package com.ohhapple.carpetplus.mycommand;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.ohhapple.carpetplus.settings.CarpetPlusSettings;
import com.ohhapple.carpetplus.settings.ohhappleinit;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class ChunkStatsCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("chunkstats")
                .requires(source -> source.hasPermission(2))
                .executes(ctx -> showChunkStats(ctx))
        );
    }

    private static int showChunkStats(CommandContext<CommandSourceStack> ctx) {
        var loader = ohhappleinit.getLoader();
        if (loader == null) {
            ctx.getSource().sendFailure(Component.literal("玩家区块加载器未初始化"));
            return 0;
        }

        ctx.getSource().sendSuccess(() -> Component.literal("=== 区块加载统计 ==="), false);

        // 获取服务器最大视距
        int serverMaxViewDistance = ctx.getSource().getServer().getPlayerList().getViewDistance();
        ctx.getSource().sendSuccess(() -> Component.literal(
                String.format("服务器最大视距: %d", serverMaxViewDistance)
        ), false);

        // 显示默认视距
        int defaultDistance = CarpetPlusSettings.defaultPlayerViewDistance;
        ctx.getSource().sendSuccess(() -> Component.literal(
                String.format("默认玩家视距: %d", defaultDistance)
        ), false);

        // 显示功能状态
        boolean enabled = CarpetPlusSettings.playerSpecificChunks;
        ctx.getSource().sendSuccess(() -> Component.literal(
                String.format("玩家独立区块加载: %s", enabled ? "启用" : "禁用")
        ), false);

        // 显示每个玩家的区块加载信息
        if (enabled && ctx.getSource().getPlayer() != null) {
            ServerPlayer player = ctx.getSource().getPlayer();
            ServerLevel level = (ServerLevel) player.level();

            int playerDistance = loader.getPlayerViewDistance(player);
            ctx.getSource().sendSuccess(() -> Component.literal(""), false);
            ctx.getSource().sendSuccess(() -> Component.literal("=== 当前玩家信息 ==="), false);
            ctx.getSource().sendSuccess(() -> Component.literal(
                    String.format("玩家: %s", player.getName().getString())
            ), false);
            ctx.getSource().sendSuccess(() -> Component.literal(
                    String.format("视距: %d", playerDistance)
            ), false);
            ctx.getSource().sendSuccess(() -> Component.literal(
                    String.format("维度: %s", level.dimension().location())
            ), false);

            // 计算区块加载数量（近似值）
            int chunkCount = (playerDistance * 2 + 1) * (playerDistance * 2 + 1);
            ctx.getSource().sendSuccess(() -> Component.literal(
                    String.format("加载区块数: ~%d", chunkCount)
            ), false);
        }

        // 显示在线玩家数量
        int onlinePlayers = ctx.getSource().getServer().getPlayerList().getPlayerCount();
        ctx.getSource().sendSuccess(() -> Component.literal(""), false);
        ctx.getSource().sendSuccess(() -> Component.literal(
                String.format("在线玩家: %d", onlinePlayers)
        ), false);

        return 1;
    }
}