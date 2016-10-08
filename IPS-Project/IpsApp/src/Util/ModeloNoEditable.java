package Util;

import javax.swing.table.DefaultTableModel;

//Una tabla no editable para usar tablas.

public class ModeloNoEditable extends DefaultTableModel {

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public ModeloNoEditable(Object [] columnNames, int rowCount){
		super(columnNames, rowCount);
	}
}
