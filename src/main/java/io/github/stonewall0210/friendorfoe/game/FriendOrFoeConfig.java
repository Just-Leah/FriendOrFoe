package io.github.stonewall0210.friendorfoe.game;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import xyz.nucleoid.plasmid.game.config.PlayerConfig;
import io.github.stonewall0210.friendorfoe.game.map.FriendOrFoeMapConfig;

public class FriendOrFoeConfig {
    public static final Codec<FriendOrFoeConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PlayerConfig.CODEC.fieldOf("players").forGetter(config -> config.playerConfig),
            FriendOrFoeMapConfig.CODEC.fieldOf("map").forGetter(config -> config.mapConfig),
            Codec.INT.fieldOf("time_limit_secs").forGetter(config -> config.timeLimitSecs)
    ).apply(instance, FriendOrFoeConfig::new));

    public final PlayerConfig playerConfig;
    public final FriendOrFoeMapConfig mapConfig;
    public final int timeLimitSecs;

    public FriendOrFoeConfig(PlayerConfig players, FriendOrFoeMapConfig mapConfig, int timeLimitSecs) {
        this.playerConfig = players;
        this.mapConfig = mapConfig;
        this.timeLimitSecs = timeLimitSecs;
    }
}
