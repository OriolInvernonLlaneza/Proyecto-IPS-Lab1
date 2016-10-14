package Util;

import javax.swing.table.DefaultTableModel;

public class ModeloEditableUnaCelda extends DefaultTableModel {

	int celda;
	@Override
	public boolean isCellEditable(int row, int col) {
	    return col==celda;
	}
	
	public ModeloEditableUnaCelda(Object [] columnNames, int rowCount, int celda){
		super(columnNames, rowCount);
		this.celda=celda;
	}
}
