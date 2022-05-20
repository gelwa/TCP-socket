package sheet3;
import java.util.*;

public class Team {
    
    private String name;
    HashSet <Player> player_list = new HashSet<Player>();
    
    Team() { name = "default"; }
    public Team(String _name) {  name = _name; }
    
    //Method to add player
    public void add_player(String _name, String _position, int _age) {
        Player one = new Player(_name, _position, _age);
        player_list.add(one);  
    }
    
    /*
    void add_player(Player _player) {
        player_list.add(_player);
    }*/
    
    //Method to search player
    Player search_player(String _name) {
       Iterator <Player> iterator = player_list.iterator();
       
       while (iterator.hasNext()) {
           Player current = iterator.next();
           if(current.get_name() ==  _name) {
               System.out.println("Player found");
               return current;}
       }
       
       System.out.println("Player not found");
       return null;
    }
    
    //Method to display objects
    void return_set() {
       Iterator <Player> iterator = player_list.iterator();
       while (iterator.hasNext()) {
           Player current = iterator.next();
           System.out.println(current.get_name());
       }
    }
    
    String get_name() {
        return name;
    }
    
    //return set of players
    HashSet <Player> get_players() {
        return player_list;
    }
    
    //creates string array of player names
    public List<String> get_names(){
    List<String> names = new ArrayList<>();
    //iterates through Players -> adds names to array
    for (Player player: get_players()) {
        names.add(player.get_name());
    }
    return names;
}
    
}
            