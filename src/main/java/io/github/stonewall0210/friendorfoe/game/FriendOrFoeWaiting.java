package io.github.stonewall0210.friendorfoe.game;

import net.minecraft.util.ActionResult;
import xyz.nucleoid.plasmid.game.*;
import xyz.nucleoid.plasmid.game.event.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import io.github.stonewall0210.friendorfoe.game.map.FriendOrFoeMap;
import io.github.stonewall0210.friendorfoe.game.map.FriendOrFoeMapGenerator;
import xyz.nucleoid.fantasy.BubbleWorldConfig;

public class FriendOrFoeWaiting {
    private final GameSpace gameSpace;
    private final FriendOrFoeMap map;
    private final FriendOrFoeConfig config;
    private final FriendOrFoeSpawnLogic spawnLogic;

    private FriendOrFoeWaiting(GameSpace gameSpace, FriendOrFoeMap map, FriendOrFoeConfig config) {
        this.gameSpace = gameSpace;
        this.map = map;
        this.config = config;
        this.spawnLogic = new FriendOrFoeSpawnLogic(gameSpace, map);
    }

    public static GameOpenProcedure open(GameOpenContext<FriendOrFoeConfig> context) {
        FriendOrFoeConfig config = context.getConfig();
        FriendOrFoeMapGenerator generator = new FriendOrFoeMapGenerator(config.mapConfig);
        FriendOrFoeMap map = generator.build();

        BubbleWorldConfig worldConfig = new BubbleWorldConfig()
                .setGenerator(map.asGenerator(context.getServer()))
                .setDefaultGameMode(GameMode.SPECTATOR);

        return context.createOpenProcedure(worldConfig, game -> {
            FriendOrFoeWaiting waiting = new FriendOrFoeWaiting(game.getSpace(), map, context.getConfig());

            GameWaitingLobby.applyTo(game, config.playerConfig);

            game.on(RequestStartListener.EVENT, waiting::requestStart);
            game.on(PlayerAddListener.EVENT, waiting::addPlayer);
            game.on(PlayerDeathListener.EVENT, waiting::onPlayerDeath);
        });
    }

    private StartResult requestStart() {
        FriendOrFoeActive.open(this.gameSpace, this.map, this.config);
        return StartResult.OK;
    }

    private void addPlayer(ServerPlayerEntity player) {
        this.spawnPlayer(player);
    }

    private ActionResult onPlayerDeath(ServerPlayerEntity player, DamageSource source) {
        player.setHealth(20.0f);
        this.spawnPlayer(player);
        return ActionResult.FAIL;
    }

    private void spawnPlayer(ServerPlayerEntity player) {
        this.spawnLogic.resetPlayer(player, GameMode.ADVENTURE);
        this.spawnLogic.spawnPlayer(player);
    }
}
