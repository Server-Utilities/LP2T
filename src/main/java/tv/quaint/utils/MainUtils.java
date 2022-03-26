package tv.quaint.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import tv.quaint.LuckPermsToTeams;

import java.util.List;

public class MainUtils {
    public static void sync(ServerPlayerEntity player) {
        LuckPerms api = LuckPermsProvider.get();

        User user = api.getUserManager().getUser(player.getUuid());
        if (user == null) return;

        Group group = api.getGroupManager().getGroup(user.getPrimaryGroup());
        if (group == null) return;

        Team team = LuckPermsToTeams.SERVER.getScoreboard().getTeam(group.getName());
        if (team == null) return;

        LuckPermsToTeams.SERVER.getScoreboard().addPlayerToTeam(player.getName().getString(), team);
    }

    public static List<ServerPlayerEntity> getOnlinePlayers() {
        return LuckPermsToTeams.SERVER.getPlayerManager().getPlayerList();
    }
}
