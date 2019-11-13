import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;

class faux{ // collection of non-OO auxiliary functions (currently just error)
    public static void error(String msg){
	System.err.println("Interpreter error: "+msg);
	System.exit(-1);
    }
}

abstract class AST{
}

class Start extends AST{
    public List<DataTypeDef> datatypedefs;
    StringBuilder string = new StringBuilder();

    Start(List<DataTypeDef> datatypedefs){
        this.datatypedefs=datatypedefs;
        string.append("\n");
        string.append("import java.util.*;\n");
        string.append("abstract class AST{}\n\n");
    }

    public String translate(){

        for(DataTypeDef data : datatypedefs ){
            string.append(data.translate());
        }
        string.append("\n");

        return string.toString();
    }
}

class DataTypeDef extends AST{

    public String dataTypeName;
    public String functionHead;
    public List<Alternative> alternatives;
    public String type;
    DataTypeDef(String dataTypeName, String functionHead, List<Alternative> alternatives){
	this.dataTypeName=dataTypeName;
	this.functionHead=functionHead;
	this.alternatives=alternatives;
    }
    StringBuilder string = new StringBuilder();

    public String translate(){

        string.append("abstract class "+dataTypeName+" extends AST");
        string.append("{\n");
        string.append("public abstract");
        string.append(functionHead);
        string.append(";\n}");

        string.append("\n");
        for (Alternative alt : alternatives ) {
            string.append("\n");
            string.append("class ");
            alt.type = dataTypeName;
            alt.funchead = functionHead;
            string.append(alt.translate());
            string.append("\n");
        }
        string.append("\n");
        return string.toString();
    }
}

class Alternative extends AST{
    public String constructor;
    public List<Argument> arguments;
    public String code;
    public String type;
    public String funchead;
    Alternative(String constructor, List<Argument> arguments,  String code){
	this.constructor=constructor;
	this.arguments=arguments;
	this.code=code;
    }
    StringBuilder string = new StringBuilder();
    public String translate(){

        string.append(constructor);
        string.append(" extends "+type);
        string.append("{\n");
        for (Argument arg : arguments ) {
            string.append("   ");
            string.append(arg.translate());
        }
        string.append("   "+constructor);
        string.append("( ");

        for (Argument arg : arguments ) {
            string.append(arg.type+" "+arg.name+", ");
        }
        string.deleteCharAt(string.length()-2);
        string.append(")");
        string.append("{ \n");
        for (Argument arg : arguments ) {
            string.append("   this.").append(arg.name).append(" = ").append(arg.name).append(";\n");
        }
        string.append("   }");

        string.append("\n");
        string.append("\n");
        string.append("  public"+funchead);
        string.append(code);
        string.append("\n");
        string.append("}");
        return string.toString();
    }
}

class Argument extends AST{
    public String type;
    public String name;
    StringBuilder string = new StringBuilder();
    Argument(String type, String name){this.type=type; this.name=name;}
    public String translate(){
        string.append(type);
        string.append(" ");
        string.append(name+";");
        string.append("\n");
        return string.toString();
    }
}
