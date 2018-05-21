package examples.RMIShape;

import java.awt.Rectangle;
import java.awt.Color;
import java.io.Serializable;

public class GraphicalObject implements Serializable{
    public String type;
    
    //	constructors
    public GraphicalObject(String aType) {
    	type = aType;
    }
    
    public String getType() {
    	return type;
    }
    
    public void print(){
		System.out.print(type + "\n");
	}
}
