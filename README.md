# Compiler
A compiler for a simple C style language

1. This program is a compiler for a simple C style language. It reads the input codes and generate MIPS codes as assembly language. To run the program, you have to install the environment of JLex (scanner generator) and javaCUP (parser generator) in order to process the .jlex file and .cup file in the expected way. Also, of course, java environment is needed, though I bet every one has it set up already. 

2. To run the program, you should first set up the required environment (java, JLex and javaCUP). Then simply type "make" under the same folder in the terminal, and the program should be compiled automatically. After compilation, you could create a file "test.cf" containing the input codes, and the MIPS code will be generated in "test.s", given no grammar error and semantic error. The grammar for this language could be found in CFlat.grammar, which supports basic operation on integers, booleans and struct objects.

3. The whole program contains the following files:
  a) ast.java: This file contains the class definition for the AST structure, the name analysis method, the type 		              check method and the code generation method.<br>
  b) CFlat.cup: A javaCUP specification of this language, which could generate the parser and build up the syntax tree according to the given grammar.
  c) CFlat.jlex: A JLex specification of this language, which could generate the scanner and create the tokens for the parser.
  d) Codegen.java: A helper class for generating the MIPS code.
  e) Compiler.java: The main program to call the parser, name analyzer, type checker and code generator.
  f) DuplicateSymException.java: Defines exception of duplicate symbol exception in SymTable.java.
  g) EmptySymTableException.java: Defines exception of empty symbol table exception in SymTable.java.
  h) ErrMsg.java: A helper class for generating error messages.
  i) Makefile: Invokes compiling of the compiler program, generating MIPS code according to the input and cleaning what was compiled.
  j) SemSym.java: Defines the class of symbols.
  k) SymTable.java: defines the class of symbol tables.
  l) Type.java: Defines the class of types.
