package Util;

import javax.swing.table.DefaultTableModel;

//Una tabla no editable para usar tablas.

public class ModeloNoEditableSpinner extends DefaultTableModel {

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 2)
			return true;
		else
			return false;
	}

	public ModeloNoEditableSpinner(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		 if (column == 2) {
			 super.setValueAt(aValue, row, column);
             this.fireTableCellUpdated(row, column);
         }
	}
}
