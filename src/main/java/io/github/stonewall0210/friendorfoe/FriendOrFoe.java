package io.github.stonewall0210.friendorfoe;

import net.fabricmc.api.ModInitializer;
import xyz.nucleoid.plasmid.game.GameType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.stonewall0210.friendorfoe.game.FriendOrFoeConfig;
import io.github.stonewall0210.friendorfoe.game.FriendOrFoeWaiting;

public class FriendOrFoe implements ModInitializer {

    public static final String ID = "friendorfoe";
    public static final Logger LOGGER = LogManager.getLogger(ID);

    public static final GameType<FriendOrFoeConfig> TYPE = GameType.register(
            new Identifier(ID, "friendorfoe"),
            FriendOrFoeWaiting::open,
            FriendOrFoeConfig.CODEC
    );

    @Override
    public void onInitialize() {}
}
