public class Field {

    public boolean hidden = true;
    public boolean mine;
    public int neighbors = 0;

    public Field(boolean mine) {
        this.mine = mine;
    }
}
