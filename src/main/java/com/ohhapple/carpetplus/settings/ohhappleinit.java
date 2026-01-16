package com.ohhapple.carpetplus.settings;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import com.ohhapple.carpetplus.CarpetPlus;
import com.ohhapple.carpetplus.mycommand.ChunkStatsCommand;
import com.ohhapple.carpetplus.mycommand.PlayerChunkCommand;
import com.ohhapple.carpetplus.utils.CarpetPlusTranslations;
import com.ohhapple.carpetplus.utils.PlayerChunkLoader;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.ohhapple.carpetplus.settings.CarpetPlusSettings.playerSpecificChunks;

public class ohhappleinit implements CarpetExtension {
    private static final ohhappleinit INSTANCE = new ohhappleinit();

    public static final Logger LOGGER = LoggerFactory.getLogger(CarpetPlus.MOD_ID);
    private static PlayerChunkLoader globalLoader;
    public static PlayerChunkLoader getLoader() {
        return globalLoader;
    }

    public static ohhappleinit getInstance() {
        return INSTANCE;
    }

    public static void open() {
        CarpetServer.manageExtension(INSTANCE);
    }

    @Override
    public void onGameStarted() {
        // 让我们用 /carpet 来处理我们为数不多的简单设置
        LOGGER.info("CarpetPlus loaded! Made By ohhapple");
        LOGGER.info("Open Source: https://github.com/ohhapple");
//        LOGGER.info("Issues: ");
//        LOGGER.info("Wiki: ");
        CarpetServer.settingsManager.parseSettingsClass(CarpetPlusSettings.class);
    }

    @Override
    public String version() {
        return CarpetPlus.getModId();
    }
    @Override
    public void onTick(MinecraftServer server) {
        // 这里可以放需要每刻执行的代码
        // 例如：定期检查、计时器、持续效果等
        if (server.getTickCount() % 100 == 0 && globalLoader != null) {
            globalLoader.checkRulesUpdate();
        }
    }
    @Override
    public void registerLoggers() {
        // 注册自定义日志记录器  注册可以通过 /log 命令使用的日志记录器
        // CarpetModLoggerRegistry.registerLogger("my_logger", MyLogger.class)
    }
    @Override
    public void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess)
    {
        // 放额外的自定义命令 注册自己的命令
//        PingCommand.register(dispatcher);
        PlayerChunkCommand.register(dispatcher);
        ChunkStatsCommand.register(dispatcher);
    }
    @Override
    public void onPlayerLoggedIn(ServerPlayer player) {
        // 玩家进入游戏时执行
        if (globalLoader != null && playerSpecificChunks) {
            globalLoader.onPlayerJoin(player);
            LOGGER.debug("玩家 {} 加入，应用区块加载设置", player.getName().getString());
        }
    }
    @Override
    public void onPlayerLoggedOut(ServerPlayer player) {
        // 玩家退出游戏时执行
        if (globalLoader != null) {
            globalLoader.onPlayerLeave(player);
            LOGGER.debug("玩家 {} 离开，清理设置", player.getName().getString());
        }
    }
    @Override
    public void onServerLoaded(MinecraftServer server) {
        // 服务器完全加载，可以开始游戏时执行 调用时机：服务器启动完成，世界还未加载
        LOGGER.info("服务器启动，初始化玩家区块加载管理器");
        globalLoader = new PlayerChunkLoader(server);
    }
    @Override
    public void onServerClosed(MinecraftServer server) {
        // 服务器关闭时执行
        LOGGER.info("服务器关闭，清理玩家区块加载设置");
        globalLoader = null;
    }
    @Override
    public void onServerLoadedWorlds(MinecraftServer server) {
        // 所有世界加载完成时执行
    }
    @Override
    public Map<String, String> canHasTranslations(String lang)
    {
        return CarpetPlusTranslations.getTranslationFromResourcePath(lang);
    }
}
