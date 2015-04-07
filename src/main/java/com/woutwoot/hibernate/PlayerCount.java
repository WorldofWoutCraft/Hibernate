package com.woutwoot.hibernate;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author woutwoot
 *         Created by on 7/04/2015 - 22:51.
 */
public class PlayerCount {

    private static int pcount = 0;
    private static Object players;

    public static int getOnline() {
        try {
            if (players == null) {
                Method method = Bukkit.class.getMethod("getOnlinePlayers");
                players = method.invoke(null);
            }

            if (pcount > 0) {
                if (pcount == 2) {
                    Collection<Player> newPlayers = (Collection<Player>) players;
                    return newPlayers.size();
                } else {
                    Player[] oldPlayers = (Player[]) players;
                    return oldPlayers.length;
                }
            }

            if (players instanceof Player[]) {
                pcount = 1;
                Player[] oldPlayers = (Player[]) players;
                return oldPlayers.length;
            } else {
                pcount = 2;
                Collection<Player> newPlayers = (Collection<Player>) players;
                return newPlayers.size();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return 0;
    }

}
