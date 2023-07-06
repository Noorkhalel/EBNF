import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        //project,const,var,int,routine,start,end,input,output,if,then,endif,else,do,loop
        List<String> definedName = new ArrayList<>(Arrays.asList("project","const","var","int","routine","start","end","input"
        ,"output","if","then","endif","else","do","loop"));
        List<String> checkTokens = new ArrayList<>(Arrays.asList("input","output"));

        List<String> tokens = new ArrayList<>(Arrays.asList(
                "project", "project", ";",
                    "const", "len", "=","100",";",
                //
                    "var","total", ":","int",";"
                //
                    ,"routine", "compute",";"
                    ,"var","num", ":","int",";"
                    ,"start"
                        ,"input","(","x",")",";"
                        ,"num",":","=","n","+","10",";"
                        ,"output","(","num",")",";"
                    ,"end",";"

                     ,"start"
                        ,"input","(","i",")",";"
                        ,"if","(","i",">","100",")","then"
                             ,"j",":","=","5"
//                        ,"else"
//                        ,"j",":","=","5"
                        ,"endif",";"
//                        ,"start"
//                        ,"end",";"
                        ,"loop","(","i",">","=","min",")","do"
                            ,"start"
                                ,"i",":","=","i","+","1",";"
                                ,"output","(","i",")",";"
                                ,"total",":","=","total","+","i",";"
                            ,"end",";"
                        ,"output","(","total",")",";"
                ,"end","."
        ));
//        System.out.print(tokens.size());
       // The token = [project,const,var,int,routine,start,end,input,output,if,then,endif,else,do,loop]
//        List<symbolTable> Sy = new ArrayList<>();
//        for(int i=0;i<=tokens.size()-1;i++){
//            if(tokens.get(i).equals("project")||tokens.get(i).equals("var")||tokens.get(i).equals("start")||tokens.get(i).equals("routine")){
//                name.add(tokens.get(i+1));
////                System.out.print(tokens.get(i+1)+" ");
//            }
//
//        }
//        Sy.add(new symbolTable("class","A"));
//        List<symbolTable> Sy = new ArrayList<>();
//        for(int i=0;i<=tokens.size()-1;i++){
//            if(tokens.get(i).equals("project")
//                    ||tokens.get(i).equals("start")|| tokens.get(i).equals("routine")||tokens.get(i).equals(",")
//                    ||tokens.get(i).equals(":=")||tokens.get(i).equals("*")||tokens.get(i).equals("+")
//                    ||tokens.get(i).equals("/")||tokens.get(i).equals("-")||tokens.get(i).equals("%")
//                    ||tokens.get(i).equals("const")||tokens.get(i).equals("var")){
//
//                    name.add(tokens.get(i+1));
//
//
//
////                System.out.print(tokens.get(i+1)+" ");
//            }

 //       }
//        Sy.add(new symbolTable("class","A"));





        ////////////////////////////////////////////////////////////////////////////////////////////////////
        List<String> listCheck = Arrays.asList("project", "const", "var", "int", "routine", "start", "end",
                "input", "output", "if", "then", "endif", "else", "do", "loop", "(", ")","=",";","+",".",":"
                ,",","-","*","/","%","<",">");
        List<String> resultList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\SUNLIGHT COMPUTER\\IdeaProjects\\EBNF\\src\\input.txt"))) {
            int currentChar;
            StringBuilder currentToken = new StringBuilder();

            while ((currentChar = reader.read()) != -1) {
                char currentCharValue = (char) currentChar;

                if (Character.isLetterOrDigit(currentCharValue)) {
                    currentToken.append(currentCharValue);
                } else {
                    if (currentToken.length() > 0) {
                        String token = currentToken.toString().trim();
                        currentToken.setLength(0);

                        if (listCheck.contains(token)) {
                            resultList.add(token);
                        }else{
                            resultList.add(token);
                        }

                    }

                    if (!Character.isWhitespace(currentCharValue) && listCheck.contains(String.valueOf(currentCharValue))) {
                        resultList.add(String.valueOf(currentCharValue));
                    }
                }
            }

            // Add the last token if any
            if (currentToken.length() > 0) {
                String token = currentToken.toString().trim();
                if (!token.isEmpty() && listCheck.contains(token)) {
                    resultList.add(token);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        Parser parser = new Parser(resultList,definedName);
        parser.parse();
    }

}