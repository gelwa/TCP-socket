package sheet3;


public class Player {
    
    private String name;
    private String position;
    private int age;
    
    Player() {
        name = "default";
        position = "default";
        age = 0;
    }
    
    Player(String _name, String _position, int _age) {
        name=_name;
        position=_position;
        age=_age;
    }
    
    String get_name() { return name; }
    String get_position() {return position; }
    int get_age() { return age; }
    
    void change_age(int _age) {
        age = _age;
    }
    
    
}
