import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Parser {
    private List<String> tokens;
    private List<String> definedName;
//    private List<Integer> num;
    private int index;

//    public Parser(List<String> tokens, List<String> name, List<Integer> num) {
//        this.tokens = tokens;
//        this.name = name;
//        this.num = num;
//        this.index = 0;
//    }

    public Parser(List<String> tokens,List<String> definedName) {
        this.tokens = tokens;
        this.definedName=definedName;
        this.index = 0;
    }

//    public boolean checkName(String Name){
//        boolean exist = false;
//        for(int i = 0; i<=name.size();i++){
//            if(name.get(i).equals(Name)){
//                exist = true;
//                return exist;
//            }
//        }
//        return exist;
//    }
    public boolean definedName(String Name){
        boolean exist = false;
        for(int i = 0; i<=definedName.size()-1;i++){
            if(definedName.get(i).equals(Name)){
                exist = true;
                return exist;
            }
        }
        return exist;
    }
    public void parse() {
        try {
            projectDeclaration();
            match(".");
            System.out.println("\nParsing successful.");
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage() +" <==> index: "+index);
        }
    }

    private void projectDeclaration() {
        projectDef();
    }

    private void projectDef() {
        projectHeading();
        declarations();
        compoundStmt();
    }

    private void projectHeading() {
        match("project");
        matchName("name");
        match(";");
    }

    private void declarations() {
        constDecl();
        varDecl();
       subroutineDecl();
    }

    private void constDecl() {
        if (tokens.get(index).equals("const")) {

            while (tokens.get(index).equals("const")) {
                match("const");
                constItem();
                match(";");
            }
        } else {
            nullRule();
        }
    }

    private void constItem() {
        matchName("name");
        match("=");
        matchInteger("integer-value");
    }

    private void varDecl() {
        if (tokens.get(index).equals("var")) {
            while (tokens.get(index).equals("var")) {
                match("var");
                varItem();
                match(";");
            }
        } else {
            nullRule();
        }
    }

    private void varItem() {
        nameList();
        match(":");
        match("int");
    }

    private void nameList() {
        matchName("name");
        while (tokens.get(index).equals(",")) {
            match(",");
            matchName("name");
        }
    }

    private void subroutineDecl() {
        if (tokens.get(index).equals("routine")) {
            subroutineHeading();
            declarations();
            compoundStmt();
            match(";");
        } else {
            nullRule();
        }
    }

    private void subroutineHeading() {
        match("routine");
        matchName("name");
        match(";");
    }

    private void compoundStmt() {
        match("start");
        stmtList();
        match("end");
    }

    private void stmtList() {
        while (!tokens.get(index).equals("end") && !tokens.get(index).equals("else")) {
            statement();
            match(";");
        }
    }

    private void statement() {
        if (!definedName(tokens.get(index))) {
            assStmt();
        } else if (tokens.get(index).equals("input") || tokens.get(index).equals("output")) {
            inoutStmt();
        } else if (tokens.get(index).equals("if")) {
            ifStmt();
        } else if (tokens.get(index).equals("loop")) {
            loopStmt();
        } else if (tokens.get(index).equals("start")){
            compoundStmt();
        }else {
            nullRule();
        }
    }

    private void assStmt() {
        matchName("name");
        match(":=");
        arithExp();
    }

    private void arithExp() {
        term();
        while (tokens.get(index).equals("+") || tokens.get(index).equals("-")) {
            match(tokens.get(index));
            term();
        }
    }

    private void term() {
        factor();
        while (tokens.get(index).equals("*") || tokens.get(index).equals("/") || tokens.get(index).equals("%")) {
            match(tokens.get(index));
            factor();
        }
    }

    private void factor() {
//        System.out.println(definedName(tokens.get(index)));
        if (tokens.get(index).equals("(")) {
            match("(");
            arithExp();
            match(")");
        } else if (isString(tokens.get(index)) || isInteger(tokens.get(index))) {
            nameValue();
        } else {
            error("Invalid factor: " + tokens.get(index));
        }
    }

    private void nameValue() {
        if (isString(tokens.get(index))) {
            matchName("name");
        } else if (isInteger(tokens.get(index))) {
            matchInteger("integer-value");
        } else {
            error("Invalid name-value: " + tokens.get(index));
        }
    }

    private void inoutStmt() {
        if (tokens.get(index).equals("input")) {
            match("input");
            match("(");
            matchName("name");
            match(")");
        } else if (tokens.get(index).equals("output")) {
            match("output");
            match("(");
            nameValue();
//            match("integer-value");
            match(")");
        } else {
            error("Invalid inout statement: " + tokens.get(index));
        }
    }

    private void ifStmt() {
        match("if");
        match("(");
        boolExp();
        match(")");
        match("then");
        statement();
        elsePart();
        match("endif");
    }

    private void elsePart() {
        if (tokens.get(index).equals("else")) {
            match("else");
            statement();
        } else {
            nullRule();
        }
    }

    private void loopStmt() {
        match("loop");
        match("(");
        boolExp();
        match(")");
        match("do");
        statement();
    }

    private void boolExp() {
        nameValue();
        relationalOper();
        nameValue();
    }

    private void relationalOper() {
        if (tokens.get(index).equals("=") || tokens.get(index).equals("<>") || tokens.get(index).equals("<") ||
                tokens.get(index).equals("<=") || tokens.get(index).equals(">") || tokens.get(index).equals(">=")) {
            match(tokens.get(index));
        } else {
            error("Invalid relational operator: " + tokens.get(index));
        }
    }

//    private void matchName(String expectedToken){
//        if(expectedToken.equals("name")){
//            if(definedName(tokens.get(index)))
//            index++;
//        }else{
//            error("Undefined : " + expectedToken);
//        }
//    }
    public static boolean isString(Object value) {
         return value instanceof String;
    }
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void matchInteger(String expectedToken){
        if (index < tokens.size() && isInteger(tokens.get(index))) {
            index++;
        } else {
            error("Undefined token: " + tokens.get(index));
        }
    }
    private void matchName(String expectedToken){
        if (index < tokens.size() && isString(tokens.get(index))) {
            index++;
        } else {
            error("Undefined token: " + tokens.get(index));
        }
    }
    private void match(String expectedToken) {
        if (index < tokens.size() && tokens.get(index).equals(expectedToken)) {
            index++;
        }else {
            error("Expected token: " + expectedToken);
        }
    }

    private void nullRule() {
        // Do nothing
    }

    private void error(String message) {
        throw new RuntimeException("Error at line " + tokens.get(index) + ": " + message);
    }
}
