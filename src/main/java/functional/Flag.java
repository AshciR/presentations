package functional;

public enum Flag {

    // Could make an argument to remove this,
    // but then we'd have to treat a NONE case as a null flag
    // and we HATE nulls and NPEs!
    NONE,
    GREEN,
    YELLOW,
    RED,
    PURPLE

}
