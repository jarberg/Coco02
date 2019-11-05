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
    Start(List<DataTypeDef> datatypedefs){
	this.datatypedefs=datatypedefs;
    }
    public String translate(){
	return "";
	// You may start here by replacing this with something else
    }
}

class DataTypeDef extends AST{
    public String dataTypeName;
    public String functionHead;
    public List<Alternative> alternatives;
    DataTypeDef(String dataTypeName, String functionHead, List<Alternative> alternatives){
	this.dataTypeName=dataTypeName;
	this.functionHead=functionHead;
	this.alternatives=alternatives;
    }
}

class Alternative extends AST{
    public String constructor;
    public List<Argument> arguments;
    public String code;
    Alternative(String constructor, List<Argument> arguments,  String code){
	this.constructor=constructor;
	this.arguments=arguments;
	this.code=code;
    }
}

class Argument extends AST{
    public String type;
    public String name;
    Argument(String type, String name){this.type=type; this.name=name;}
}
