package amino.ui.util;
////
import javax.swing.tree.DefaultMutableTreeNode;

//import java.io.Serializable;
/**
 * @author amila giragama
 *
 */
public class FoundItem extends DefaultMutableTreeNode  {//implements Cloneable, MutableTreeNode, Serializable {

	public static final String showCode="code", showName="name",showId="id"; 
	
	private static final long serialVersionUID = 20090429L;
	
	String name,code;
	int id;
	Object obj;
	String showingData;
	
	public FoundItem(int id,String code,String name,String showingData){
		super();
		this.id=id;
		this.code=code;
		this.name=name;
		this.showingData=showingData;
	}
	public FoundItem(int id,String code,String name){
		this(id,code,name,"");
	}
	
	public String toString(){
		if (showingData.equals(showCode))
			return code;
		else if (showingData.equals(showName))
			return name;
		else
			return String.valueOf(id);
	}
	public String getCode(){
		return code;
	}
	public String getName(){
		return name;
	}
	public int getID(){
		return id;
	}
	public void setOtherData(Object obj){
		this.obj=obj;
	}
	public Object getOtherData(){
		return obj;
	}
}
