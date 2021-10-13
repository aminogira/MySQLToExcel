package amino.ui.util;
//
import java.awt.Color;
import java.awt.Component;

//import javax.swing.JLabel;

import amino.ui.PanelFlow;
import javax.swing.BorderFactory;

public class LabledComponent extends PanelFlow {

	private static final long serialVersionUID = 200911111111L;

	ALabel jl;
	@ Deprecated
	public LabledComponent(String lable,Component com){
		super(LEFT);
		jl=new ALabel(lable);
		add(jl);
		if(com instanceof ALabel)
			((ALabel)com).setBorder(BorderFactory.createLineBorder(Color.GRAY));
		add(com);
	}
	
	public LabledComponent(String lable,Component com,boolean isdbfont){
		super(LEFT);
		jl=new ALabel(lable,isdbfont);
		add(jl);
		if(com instanceof ALabel)
			((ALabel)com).setBorder(BorderFactory.createLineBorder(Color.GRAY));
		add(com);
	}
	
	
	
	public LabledComponent(String lable,Component coma,Component comb){
		this(lable,coma);
		add(comb);
	}
	
}
