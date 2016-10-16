package Util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class ModeloEditableUnaCelda extends DefaultTableModel{

	
	 private boolean[] editable_cells; // 2d array to represent rows and columns

	    public ModeloEditableUnaCelda(Object[] names, int cols, int maxRow) { // constructor
	        super(names, cols);
	        this.editable_cells = new boolean[maxRow];
	        for(int i=0; i<maxRow;i++){
	        	editable_cells[i]=true;
	        }
	    }

	    @Override
	    public boolean isCellEditable(int row, int col) {
	    	if(col==4){// custom isCellEditable function
	        return this.editable_cells[row];
	    	}
	    	return false;
	    }

	    public void setCellEditable(int row,int col, boolean value) {
	        this.editable_cells[row] = value; // set cell true/false
	        //this.fireTableCellUpdated(row, col);
	    
	    }
	    
	    public void restart(){
	    	for(int i=0; i<editable_cells.length;i++){
	        	editable_cells[i]=true;
	        }
	    }


}


