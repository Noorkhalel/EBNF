# EBNF
```markdown
 project-declaration --> project-def     "."
project-def  -->   project-heading        declarations          compound-stmt
project-heading   --> project      "name"       ";"
declarations   --> const-decl       var-decl       subroutine-decl
const-decl   --> const      ( const-item      ";" )+        |     null
const-item   -->  "name"   =   "integer-value"
var-decl   --> var    (var-item     ";" )+         |     null
var-item   -->  name-list       ":"       int
name-list  -->  "name"    ( ","     "name" )* 
subroutine-decl --> subroutine-heading      declarations      compound-stmt    “;”   |     null
subroutine-heading   --> routine      "name"       ";"
compund-stmt --> start       stmt-list       end
stmt-list -->   ( statement    ";" )* 
statement --> ass-stmt  |  inout-stmt  |  if-stmt  |  loop-stmt   |   compound-stmt    |     null
ass-stmt --> "name"     ":="      arith-exp
arith-exp --> term    ( add-sign      term )*
term --> factor    ( mul-sign       factor  )*
factor -->  "("   arith-exp  ")"   |     name-value
name-value -->  "name"      |        "integer-value"
add-sign -->  "+"    |     "-"
mul-sign --> "*"    |      "/"     |        “%”
inout-stmt --> input "("    "name"     ")"    |    output  "("   name-value   ")"
if-stmt --> if     “(“    bool-exp    “)”    then     statement     else-part       endif
else-part -->  else     statement   |  null
loop-stmt --> loop   “(“    bool-exp   “)”  do      statement
bool-exp --> name-value       relational-oper        name-value 
relational-oper -->      "="     |     "<>"     |     "<"    |     "<="     |     ">"    |     ">="
```
