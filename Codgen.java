import java.io.*;

// **********************************************************************
// The Codegen class provides constants and operations useful for code
// generation.
//
// The constants are:
//     Registers: FP, SP, RA, V0, V1, A0, T0, T1
//     Values: TRUE, FALSE
//
// The operations are include various "generate" methods to print nicely
// formatted assembly code:
//     generateWithComment
//     generate
//     generateIndexed
//     generateLabeled
//     genPush
//     genPop
//     genLabel
// and a method nextLabel to create and return a new label.
//
// **********************************************************************

public class Codegen {
    // file into which generated code is written
    public static PrintWriter p = null;    

    // values of true and false
    public static final String TRUE = "1";
    public static final String FALSE = "0";

    // registers
    public static final String FP = "$fp";
    public static final String SP = "$sp";
    public static final String RA = "$ra";
    public static final String V0 = "$v0";
    public static final String V1 = "$v1";
    public static final String A0 = "$a0";
    public static final String T0 = "$t0";
    public static final String T1 = "$t1";


    // for pretty printing generated code
    private static final int MAXLEN = 6;


    // for generating labels
    private static int currLabel = 0;


    // **********************************************************************
    // **********************************************************************
    // GENERATE OPERATIONS
    // **********************************************************************
    // **********************************************************************
    
    // **********************************************************************
    // generateWithComment
    //    given:  op code, comment, and 0 to 3 string args
    //    do:     write nicely formatted code (ending with new line)
    // **********************************************************************
    public static void generateWithComment(String opcode, String comment,
                                        String arg1, String arg2, String arg3) {
        int space = MAXLEN - opcode.length() + 2;
    
        p.print("\t" + opcode);
        if (arg1 != "") {
            for (int k = 1; k <= space; k++) 
                p.print(" ");
            p.print(arg1);
            if (arg2 != "") {
                p.print(", " + arg2);
                if (arg3 != "") 
                    p.print(", " + arg3);
            }
        }
        if (comment != "") 
            p.print("\t\t#" + comment);
        p.println();
    }

    public static void generateWithComment(String opcode, String comment,
                                           String arg1, String arg2) {
        generateWithComment(opcode, comment, arg1, arg2, "");
    }

    public static void generateWithComment(String opcode, String comment,
                                           String arg1) {
        generateWithComment(opcode, comment, arg1, "", "");
    }

    public static void generateWithComment(String opcode, String comment) {
        generateWithComment(opcode, comment, "", "", "");
    }

    // **********************************************************************
    // generate
    //    given:  op code, and 0 to 3 string args
    //    do:     write nicely formatted code (ending with new line)
    // **********************************************************************
    public static void generate(String opcode, String arg1, String arg2,
                                String arg3) {
        int space = MAXLEN - opcode.length() + 2;
    
        p.print("\t" + opcode);
        if (arg1 != "") {
            for (int k = 1; k <= space; k++) 
                p.print(" ");
            p.print(arg1);
            if (arg2 != "") {
                p.print(", " + arg2);
                if (arg3 != "") 
                    p.print(", " + arg3);
            }
        }
        p.println();
    }

    public static void generate(String opcode, String arg1, String arg2) {
        generate(opcode, arg1, arg2, "");
    }

    public static void generate(String opcode, String arg1) {
        generate(opcode, arg1, "", "");
    }

    public static void generate(String opcode) {
        generate(opcode, "", "", "");
    }

    // **********************************************************************
    // generate (two string args, one int)
    //    given:  op code and args
    //    do:     write nicely formatted code (ending with new line)
    // **********************************************************************
    public static void generate(String opcode, String arg1, String arg2,
                                int arg3) {
        int space = MAXLEN - opcode.length() + 2;
    
        p.print("\t" + opcode);
        for (int k = 1; k <= space; k++) 
            p.print(" ");
        p.println(arg1 + ", " + arg2 + ", " + arg3);
    }
    
    // **********************************************************************
    // generate (one string arg, one int)
    //    given:  op code and args
    //    do:     write nicely formatted code (ending with new line)
    // **********************************************************************
    public static void generate(String opcode, String arg1, int arg2) {
        int space = MAXLEN - opcode.length() + 2;
    
        p.print("\t" + opcode);
        for (int k = 1; k <= space; k++) 
            p.print(" ");
        p.println(arg1 + ", " + arg2);
    }
    
    // **********************************************************************
    // generateIndexed
    //    given:  op code, target register T1 (as string), indexed register T2
    //            (as string), - offset xx (int), and optional comment
    //    do:     write nicely formatted code (ending with new line):
    //                 op T1, xx(T2) #comment
    // **********************************************************************
    public static void generateIndexed(String opcode, String arg1, String arg2,
                                       int arg3, String comment) {
        int space = MAXLEN - opcode.length() + 2;
    
        p.print("\t" + opcode);
        for (int k = 1; k <= space; k++) 
            p.print(" ");
        p.print(arg1 + ", " + arg3 + "(" + arg2 + ")");
        if (comment != "") 
            p.print("\t#" + comment);
        p.println();
    }
    
    public static void generateIndexed(String opcode, String arg1, String arg2,
                                       int arg3) {
        generateIndexed(opcode, arg1, arg2, arg3, "");
    }

    // **********************************************************************
    // generateLabeled (string args -- perhaps empty)
    //    given:  label, op code, comment, and arg
    //    do:     write nicely formatted code (ending with new line)
    // **********************************************************************
    public static void generateLabeled(String label, String opcode,
                                       String comment, String arg1) {
        int space = MAXLEN - opcode.length() + 2;
    
        p.print(label + ":");
        p.print("\t" + opcode);
        if (arg1 != "") {
            for (int k = 1; k <= space; k++) 
                p.print(" ");
            p.print(arg1);
        }
        if (comment != "") 
            p.print("\t# " + comment);
        p.println();
    }

    public static void generateLabeled(String label, String opcode,
                                       String comment) {
        generateLabeled(label, opcode, comment, "");
    }

    // **********************************************************************
    // genPush
    //    generate code to push the given value onto the stack
    // **********************************************************************
    public static void genPush(String s) {
        generateIndexed("sw", s, SP, 0, "PUSH");
        generate("subu", SP, SP, 4);
    }

    // **********************************************************************
    // genPop
    //    generate code to pop into the given register
    // **********************************************************************
    public static void genPop(String s) {
        generateIndexed("lw", s, SP, 4, "POP");
        generate("addu", SP, SP, 4);
    }

    // **********************************************************************
    // genLabel
    //   given:    label L and comment (comment may be empty)
    //   generate: L:    # comment
    // **********************************************************************
    public static void genLabel(String label, String comment) {
        p.print(label + ":");
        if (comment != "") 
            p.print("\t\t" + "# " + comment);
        p.println();
    }
    
    public static void genLabel(String label) {
        genLabel(label, "");
    }
    
    // **********************************************************************
    // Return a different label each time:
    //        L0 L1 L2, etc.
    // **********************************************************************
    public static String nextLabel() {
        Integer k = new Integer(currLabel++);
        String tmp = ".L" + k;
        return(tmp);
    }
}
