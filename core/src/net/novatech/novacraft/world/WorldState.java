package net.novatech.novacraft.world;

public enum WorldState {
    WORLD_1(1),
    WORLD_2(2),
    WORLD_3(3),
    WORLD_4(4),
    WORLD_5(5);

    WorldState(int num){
        this.num = num;
    }

    private int num;

    public int getId(){
        return this.num;
    }

}
