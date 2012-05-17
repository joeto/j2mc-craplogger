package to.joe.j2mc.logger;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class J2MC_Logger extends JavaPlugin implements Listener{
    
    Logger craplogger;
    
    public void onEnable(){
        Handler handler = null;
        try {
            handler = new FileHandler("secondary.log");
        } catch (Exception e) {
            this.getLogger().severe("Could not enable logger, shutting down!");
            e.printStackTrace();
            this.getServer().getPluginManager().disablePlugin(this);
        }
        this.craplogger.addHandler(handler);
        this.getServer().getPluginManager().registerEvents(this, this);
        
        this.getLogger().info("Crap logger enabled");
        this.craplogger.info("Server started up, crap logger enabled");
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDrop(PlayerDropItemEvent event){
        int x = (int) event.getItemDrop().getLocation().getX();
        int y = (int) event.getItemDrop().getLocation().getY();
        int z = (int) event.getItemDrop().getLocation().getZ();
        StringBuilder sb = new StringBuilder();
        sb.append("[Item Drop] ");
        sb.append(event.getPlayer().getName());
        sb.append(" dropped ");
        sb.append(event.getItemDrop().getItemStack().getAmount());
        sb.append(" ");
        sb.append(event.getItemDrop().getItemStack().getType().toString());
        sb.append(" at ");
        sb.append(x + ", " + y + ", " + z);
        if(event.isCancelled()){
            sb.append( " (Event cancelled)");
        }
        this.craplogger.info(sb.toString());
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPickUp(PlayerPickupItemEvent event){
        int x = (int) event.getItem().getLocation().getX();
        int y = (int) event.getItem().getLocation().getY();
        int z = (int) event.getItem().getLocation().getZ();
        StringBuilder sb = new StringBuilder();
        sb.append("[Item Pickup] ");
        sb.append(event.getPlayer().getName());
        sb.append(" picked up ");
        sb.append(event.getItem().getItemStack().getAmount());
        sb.append(" ");
        sb.append(event.getItem().getItemStack().getType().toString());
        sb.append(" at ");
        sb.append(x + ", " + y + ", " + z);
        if(event.isCancelled()){
            sb.append( " (Event cancelled)");
        }
        this.craplogger.info(sb.toString());
    }
    
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){
        StringBuilder sb = new StringBuilder();
        sb.append("[Inventory Open] ");
        sb.append(event.getPlayer().getName());
        sb.append(" opened ");
        sb.append(event.getInventory().getType().toString());
        if(event.isCancelled()){
            sb.append( " (Event cancelled)");
        }
        this.craplogger.info(sb.toString());
    }

}
