grammar PP;

type returns [Type value]:
	c1 = 'integer' {$value = new Int();}
	| 'boolean' {$value = new Bool();}
	| 'array of' c2 = type { $value = new Array($c2.value); } ;

constExpr returns [PPExpr value]:
	c = Number {$value = new PPCte(Integer.parseInt($c.text));}
	| 'true' {$value = new PPTrue();}
	| 'false' {$value = new PPFalse();}
	| c2 = Text {$value = new PPVar($c2.text);};
	
uop returns [PPUnOp value]:
	e1 = '-' e2 = expr {$value = new PPInv($e2.value);}
	| 'not' e3 = expr {$value = new PPNot($e3.value);} ;
	
bop returns [PPExpr value]:
	e = logicOp {$value = $e.value;} ;
	
logicOp returns [PPExpr value]:
	e1 = compOp {$value = $e1.value;}
	('and' e2 = compOp {$value = new PPAnd($value, $e2.value);}
	| 'or' e2 = compOp {$value = new PPOr($value, $e2.value);})* ;
	
compOp returns [PPExpr value]:
	e1 = additionOp {$value = $e1.value;}
	('<' e2 = additionOp {$value = new PPLt($e1.value, $e2.value);}
	| '<=' e2 = additionOp {$value = new PPLe($value, $e2.value);}
	| '=' e2 = additionOp {$value = new PPEq($value, $e2.value);}
	| '!=' e2 = additionOp {$value = new PPNe($value, $e2.value);}
	| '>' e2 = additionOp {$value = new PPGt($value, $e2.value);}
	| '>=' e2 = additionOp {$value = new PPGe($value, $e2.value);})* ;
	
additionOp returns [PPExpr value]:
	e1 = multiplyOp {$value = $e1.value;}
	('+' e2 = multiplyOp {$value = new PPAdd($value, $e2.value);}
	| '-' e2 = multiplyOp {$value = new PPSub($value, $e2.value);})* ;
	
multiplyOp returns [PPExpr value]:
	e1 = atomExpr {$value = $e1.value;}
	('*' e2 = atomExpr {$value = new PPMul($value, $e2.value);}
	| '/' e2 = atomExpr {$value = new PPDiv($value, $e2.value);})* ;
	
atomExpr returns [PPExpr value]:
	e = constExpr {$value = $e.value;}
	| e7 = uop {$value = $e7.value;}
	| e9 = callee '(' e2 = listExpr ')' {$value = new PPFunCall($e9.value, $e2.value);}
	| e3 = atomExpr '[' e4 = expr ']' {$value = new PPArrayGet($e3.value, $e4.value);}
	| 'new array of' e5 = type '[' e6 = expr ']' {$value = new PPArrayAlloc($e5.value, $e6.value);} ;

callee returns [Callee value] :
	c = 'read' {$value = new Read();}
	| 'write' {$value = new Write();}
	| c2 = Text {$value = new User($c2.text);} ;
	
listExpr returns [ArrayList<PPExpr> value]
@init{$value = new ArrayList<PPExpr>();} :
	(e = expr {$value.add($e.value);})* ;
	
expr returns [PPExpr value]:
	e = atomExpr {$value = $e.value;}
	| e2 = bop {$value = $e2.value;} ;
	
inst returns [PPInst value]:
	i = Text ':=' i2 = expr {$value = new PPAssign($i.text, $i2.value);}
	| i12 = expr '[' i3 = expr ']' ':=' i4 = expr {$value = new PPArraySet($i12.value, $i3.value, $i4.value);}
	| 'if' i5 = expr 'then' i6 = inst 'else' i7 = inst {$value = new PPCond($i5.value, $i6.value, $i7.value);}
	| 'while' i8 = expr 'do' i9 = inst {$value = new PPWhile($i8.value, $i9.value);}
	| 'skip' {$value = new PPSkip();} 
	| i14 = callee '(' i10 = listExpr ')' {$value = new PPProcCall($i14.value, $i10.value);}
	| i15 = inst ';' i11 = inst {$value = new PPSeq($i15.value, $i11.value);} ;
	
paramList returns [ArrayList<Pair<String, Type>> value]
@init{$value = new ArrayList<Pair<String, Type>>();} :
	(p1 = Text ':' p2 = type {$value.add(new Pair<String, Type>($p1.text, $p2.value));})* ;

localList returns [ArrayList<Pair<String, Type>> value]
@init{$value = new ArrayList<Pair<String, Type>>();} :
	('var' l1 = Text ':' l2 = type {$value.add(new Pair<String, Type>($l1.text, $l2.value));})* ;

def returns [PPDef value]:
	d = func {$value = $d.value;}
	| d2 = proc {$value = $d2.value;} ;
	
func returns [PPFun value]:
	f = Text '(' f2 = paramList ')' ':' f3 = type f4 = localList f5 = inst {$value = new PPFun($f.text, $f2.value, $f4.value, $f5.value, $f3.value);} ;
	
proc returns [PPProc value]:
	f = Text '(' f2 = paramList ')' f4 = localList f5 = inst {$value = new PPProc($f.text, $f2.value, $f4.value, $f5.value);} ;
	
defList returns [ArrayList<PPDef> value]
@init{$value = new ArrayList<PPDef>();} :
	(d = def {$value.add($d.value);})* ;
	
prog returns [PPProg value]:
	p1 = localList p2 = defList p3 = inst {$value = new PPProg($p1.value, $p2.value, $p3.value);} ;

Number : ('0'..'9')+ ;
Text : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

WS : [ \t\r\n]+ -> skip ;
