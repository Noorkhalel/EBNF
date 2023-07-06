import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>(Arrays.asList());
        List<Integer> num = new ArrayList<>(Arrays.asList(1,2,3));
        List<String> checkTokens = new ArrayList<>(Arrays.asList("input","output"));

        List<String> tokens = new ArrayList<>(Arrays.asList(
                "project", "name", ";",
                    "const", "name", "=","integer-value",";",
                //
                    "var","name", ":","int",";"
                //
                    ,"routine", "name",";"
                    ,"var","name", ":","int",";"
                    ,"start"
                        ,"input","(","name",")",";"
                        ,"name",":=","name","+","name",";"
                        ,"output","(","integer-value",")",";"
                    ,"end",";"

                     ,"start"
                        ,"input","(","name",")",";"
                        ,"if","(","name","<","name",")","then"
                             ,"name",":=","name"
                        ,"endif",";"
//                        ,"start"
//                        ,"end",";"
                        ,"loop","(","name","<>","name",")","do"
                            ,"start"
                                ,"name",":=","name","+","name",";"
                                ,"output","(","integer-value",")",";"
                                ,"name",":=","name","+","name",";"
                            ,"end",";"
                        ,"input","(","name",")",";"
                        ,"output","(","integer-value",")",";"
                ,"end","."
        ));
//        List<symbolTable> Sy = new ArrayList<>();
//        for(int i=0;i<=tokens.size()-1;i++){
//            if(tokens.get(i).equals("project")||tokens.get(i).equals("var")||tokens.get(i).equals("start")||tokens.get(i).equals("routine")){
//                name.add(tokens.get(i+1));
////                System.out.print(tokens.get(i+1)+" ");
//            }
//
//        }
//        Sy.add(new symbolTable("class","A"));
        List<symbolTable> Sy = new ArrayList<>();
        for(int i=0;i<=tokens.size()-1;i++){
            if(tokens.get(i).equals("project")
                    ||tokens.get(i).equals("start")|| tokens.get(i).equals("routine")||tokens.get(i).equals(",")
                    ||tokens.get(i).equals(":=")||tokens.get(i).equals("*")||tokens.get(i).equals("+")
                    ||tokens.get(i).equals("/")||tokens.get(i).equals("-")||tokens.get(i).equals("%")
                    ||tokens.get(i).equals("const")||tokens.get(i).equals("var")){

                    name.add(tokens.get(i+1));



//                System.out.print(tokens.get(i+1)+" ");
            }

        }
//        Sy.add(new symbolTable("class","A"));
        Parser parser = new Parser(tokens);
        parser.parse();
    }

}