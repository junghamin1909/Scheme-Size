package mindustry.scheme;

import arc.math.geom.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.game.*;
import mindustry.content.*;

import static arc.Core.*;
import static mindustry.Vars.*;

// all the helper functions here ...
// also this class makes it easy to add admin`s commands
public class SchemeUtils{

	public static void history(){
		if(settings.getBool("adminssecret")) Call.sendChatMessage("/history");
	}

	public static void toggleCoreItems(){
		settings.put("coreitems", !settings.getBool("coreitems"));
	}

	public static void switchTeam(){
        var team = new Seq(Team.baseTeams).indexOf(player.team());
        player.team(Team.baseTeams[++team < 6 ? team : 0]);
        if(settings.getBool("adminssecret")) Call.sendChatMessage("/team " + player.team().name);
    }

    public static void switchTeamBtw(){
        player.team(player.team() != Team.sharded ? Team.sharded : Team.crux);
        if(settings.getBool("adminssecret")) Call.sendChatMessage("/team " + player.team().name);
    }

	public static void placeCore(){
        var tile = world.tiles.get(player.tileX(), player.tileY());
        if(tile != null){
            tile.setNet(tile.block() != Blocks.coreShard ? Blocks.coreShard : Blocks.air, player.team(), 0);
            if(settings.getBool("adminssecret")) Call.sendChatMessage("/core small");
        }
    }

    public static void lookAt(){
    	player.unit().lookAt(input.mouseWorld());
    }

    public static void teleport(Vec2 pos){
    	player.unit().set(pos);
    }

    public static void selfDest(){
    	player.unit().kill();
    	SchemeSize.hudfrag.updateShield(player.unit());
    }
}