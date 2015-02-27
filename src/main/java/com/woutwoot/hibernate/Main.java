package com.woutwoot.hibernate;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author woutwoot
 *         Created by on 15/02/2015 - 11:35.
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable(){

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
        {
            boolean firstRun = true;

            public void run()
            {
                if (Bukkit.getOnlinePlayers().size() == 0) {
                    if(firstRun) {
                        //Unload all chunks to save RAM.
                        for (World w : Bukkit.getWorlds()) {
                            for (Chunk c : w.getLoadedChunks()) {
                                c.unload(true, false);
                            }
                        }
                        //Suggest garbage collection to reduce RAM usage.
                        System.gc();
                    }
                    try
                    {
                        Thread.sleep(1000L);
                        firstRun = false;
                    } catch (Exception e) {
                    }
                } else {
                    firstRun = true;
                }
            }
        }, 0L, 1L);
    }

    @Override
    public void onDisable(){
        Bukkit.getScheduler().cancelTasks(this);
    }

}
