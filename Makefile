###
# This Makefile can be used to make a parser for the CFlat language
# (parser.class) and to make a program (compiler.class) that generates
# MIPS codes
#
# make clean removes all generated files.
#
###

JC = javac

Compiler.class: Compiler.java parser.class Yylex.class ASTnode.class
	$(JC) -g Compiler.java

parser.class: parser.java ASTnode.class Yylex.class ErrMsg.class
	$(JC) parser.java

parser.java: CFlat.cup
	java java_cup.Main < CFlat.cup

Yylex.class: CFlat.jlex.java sym.class ErrMsg.class
	$(JC) CFlat.jlex.java

ASTnode.class: ast.java Type.java
	$(JC) -g ast.java

CFlat.jlex.java: CFlat.jlex sym.class
	java JLex.Main CFlat.jlex

sym.class: sym.java
	$(JC) -g sym.java

sym.java: CFlat.cup
	java java_cup.Main < CFlat.cup

ErrMsg.class: ErrMsg.java
	$(JC) ErrMsg.java

SemSym.class: SemSym.java Type.java ast.java
	$(JC) -g SemSym.java
	
SymTable.class: SymTable.java Sym.java DuplicateSymException.java EmptySymTableException.java
	$(JC) -g SymTable.java
	
Type.class: Type.java
	$(JC) -g Type.java

DuplicateSymException.class: DuplicateSymException.java
	$(JC) -g DuplicateSymException.java
	
EmptySymTableException.class: EmptySymTableException.java
	$(JC) -g EmptySymTableException.java

Codegen.class: Codegen.java
	$(JC) -g Codegen.java

##test
test:
	java   Compiler test.cf test.s

###
# clean
###
clean:
	rm -f *~ *.class parser.java CFlat.jlex.java sym.java *.s
