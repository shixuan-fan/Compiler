/**
 * Type class and its subclasses: 
 * ErrorType, IntType, BoolType, VoidType, StringType, FnType, StructType,
 */
abstract public class Type {

    /**
     * default constructor
     */
    public Type() {
    }

    /**
     * every subclass must provide a toString method and an equals method
     */
    abstract public String toString();
    abstract public boolean equals(Type t);

    /**
     * default methods for "isXXXType"
     */
    public boolean isErrorType() {
        return false;
    }

    public boolean isIntType() {
        return false;
    }

    public boolean isBoolType() {
        return false;
    }

    public boolean isVoidType() {
        return false;
    }
    
    public boolean isStringType() {
        return false;
    }

    public boolean isFnType() {
        return false;
    }

    public boolean isStructType() {
        return false;
    }
    
    public boolean isStructDefType() {
        return false;
    }
}

// **********************************************************************
// ErrorType
// **********************************************************************
class ErrorType extends Type {

    public boolean isErrorType() {
        return true;
    }

    public boolean equals(Type t) {
        return t.isErrorType();
    }

    public String toString() {
        return "error";
    }
}

// **********************************************************************
// IntType
// **********************************************************************
class IntType extends Type {

    public boolean isIntType() {
        return true;
    }

    public boolean equals(Type t) {
        return t.isIntType();
    }

    public String toString() {
        return "int";
    }
}

// **********************************************************************
// BoolType
// **********************************************************************
class BoolType extends Type {

    public boolean isBoolType() {
        return true;
    }

    public boolean equals(Type t) {
        return t.isBoolType();
    }

    public String toString() {
        return "bool";
    }
}

// **********************************************************************
// VoidType
// **********************************************************************
class VoidType extends Type {

    public boolean isVoidType() {
        return true;
    }

    public boolean equals(Type t) {
        return t.isVoidType();
    }

    public String toString() {
        return "void";
    }
}

// **********************************************************************
// StringType
// **********************************************************************
class StringType extends Type {

    public boolean isStringType() {
        return true;
    }

    public boolean equals(Type t) {
        return t.isStringType();
    }

    public String toString() {
        return "String";
    }
}

// **********************************************************************
// FnType
// **********************************************************************
class FnType extends Type {

    public boolean isFnType() {
        return true;
    }

    public boolean equals(Type t) {
        return t.isFnType();
    }

    public String toString() {
        return "function";
    }
}

// **********************************************************************
// StructType
// **********************************************************************
class StructType extends Type {
    private IdNode myId;
    
    public StructType(IdNode id) {
        myId = id;
    }
    
    public boolean isStructType() {
        return true;
    }

    public boolean equals(Type t) {
        return t.isStructType();
    }

    public String toString() {
        return myId.name();
    }
}

// **********************************************************************
// StructDefType
// **********************************************************************
class StructDefType extends Type {

    public boolean isStructDefType() {
        return true;
    }

    public boolean equals(Type t) {
        return t.isStructDefType();
    }

    public String toString() {
        return "struct";
    }
}
