import java.util.*;

/**
 * The Sym class defines a symbol-table entry. 
 * Each Sym contains a type (a Type).
 */
public class SemSym {
    private Type type;
    private int offset;
    private int base;
    
    public SemSym(Type type, int offset) {
        this.type = type;
        this.offset = offset;
    }
    
    public Type getType() {
        return type;
    }

    public int getOffset() {
        return offset;
    }

    public int getNextOffset() {
        if (offset == 0) {
            return 0;
        }
        return offset - 4;
    }
    
    public String toString() {
        return type.toString();
    }
}

/**
 * The FnSym class is a subclass of the Sym class just for functions.
 * The returnType field holds the return type and there are fields to hold
 * information about the parameters.
 */
class FnSym extends SemSym {
    // new fields
    private Type returnType;
    private int numParams;
    private List<Type> paramTypes;
    private int size;
    
    public FnSym(Type type, int numparams, int size) {
        super(new FnType(), 0);
        returnType = type;
        numParams = numparams;
        this.size = size;
    }

    public void addFormals(List<Type> L) {
        paramTypes = L;
    }
    
    public Type getReturnType() {
        return returnType;
    }

    public int getNumParams() {
        return numParams;
    }

    public List<Type> getParamTypes() {
        return paramTypes;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        // make list of formals
        String str = "";
        boolean notfirst = false;
        for (Type type : paramTypes) {
            if (notfirst)
                str += ",";
            else
                notfirst = true;
            str += type.toString();
        }

        str += "->" + returnType.toString();
        return str;
    }
}

/**
 * The StructSym class is a subclass of the Sym class just for variables 
 * declared to be a struct type. 
 * Each StructSym contains a symbol table to hold information about its 
 * fields.
 */
class StructSym extends SemSym {
    // new fields
    private IdNode structType;  // name of the struct type
    private int size;
    
    public StructSym(int offset, IdNode id, int size) {
        super(new StructType(id), offset);
        structType = id;
        this.size = size;
    }

    public IdNode getStructType() {
        return structType;
    }    

    public int getNextOffset() {
        return getOffset() - size;
    }

    public int getSize() {
        return size;
    }
}

/**
 * The StructDefSym class is a subclass of the Sym class just for the 
 * definition of a struct type. 
 * Each StructDefSym contains a symbol table to hold information about its 
 * fields.
 */
class StructDefSym extends SemSym {
    // new fields
    private SymTable symTab;
    private int size;
    
    public StructDefSym(SymTable table, int size) {
        super(new StructDefType(), 4);
        symTab = table;
        this.size = size;
    }

    public SymTable getSymTable() {
        return symTab;
    }

    public int getSize() {
        return size;
    }
}
