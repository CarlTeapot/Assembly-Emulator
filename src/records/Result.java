package records;

public record Result(int register, int pointer){
    public int pointer() {
        return pointer;
    }
    public int register() {
        return register;
    }
};

;

