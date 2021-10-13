package amino.print;
/**
 * @author amila giragama
 ljj
 */
public class PrintRow {
	Object[] data;
	public PrintRow(Object[] obj){
		data=obj;
	}
	public Object[] getData(){
		return data;
	}
	public Object getData(int index){
		return data[index];
	}
	public String getString(int index){
		return (String) data[index];
	}
	public Integer getInteger(int index){
		return (Integer) data[index];
	}
	public int getInt(int index){
		return ((Integer)data[index]).intValue();
	}
}
