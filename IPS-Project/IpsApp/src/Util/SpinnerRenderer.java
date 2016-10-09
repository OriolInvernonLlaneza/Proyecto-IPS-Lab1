package Util;

import java.awt.Component;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellRenderer;

public class SpinnerRenderer extends JSpinner implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSpinner spinner;

	public SpinnerRenderer() {
		setOpaque(true);
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		add(spinner);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
        
		if (value == null) {
			spinner.setValue(0);
		}else{
			int intValue = ((Integer) value).intValue();
			spinner.setValue(intValue);
		}
		return spinner;
	}
	
	@Override
    public void setValue(Object value) {
		if(value != null){
			spinner.setValue(value);
		}
    }

}
