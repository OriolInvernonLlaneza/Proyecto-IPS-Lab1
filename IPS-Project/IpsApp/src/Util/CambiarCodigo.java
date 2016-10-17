package Util;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

	
public class CambiarCodigo implements TableModelListener{

	List<String> codigos;
	private ModeloEditableUnaCelda modelo;
	
	public CambiarCodigo(List<String> codigos,ModeloEditableUnaCelda modelo) {
		this.codigos=codigos;
		this.modelo=modelo;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		if(e.UPDATE==e.getType() && !(modelo.getValueAt(e.getFirstRow(), e.getColumn()).toString().equals(""))&& !(modelo.getValueAt(e.getFirstRow(), e.getColumn()).toString().equals("Completado"))&& e.getColumn()==4){
			if(e.getFirstRow()!=-1 || e.getColumn()!=-1){
				//String First=codigos.get(e.getFirstRow());
				//String second=modelo.getValueAt(e.getFirstRow(), e.getColumn()).toString();
				if(codigos.get(e.getFirstRow()).equals(modelo.getValueAt(e.getFirstRow(), e.getColumn()).toString())){
					int nuevaCantidad=Integer.parseInt(modelo.getValueAt(e.getFirstRow(), e.getColumn()-1).toString())-1;
					modelo.setValueAt(nuevaCantidad, e.getFirstRow(), e.getColumn()-1);
					if(nuevaCantidad<=0){
						modelo.setValueAt("Completado", e.getFirstRow(), e.getColumn());
						modelo.setCellEditable(e.getFirstRow(),e.getColumn(), false);
						return;
					}
					//Si coincide se reduce en uno la cantidad de productos y si llega a 0 se vuelve no editable(o se intenta).
				}
				else{
					JOptionPane.showMessageDialog(null,"El código no coincide.Por favor, use el producto adecuado.","Error codigo de barras", JOptionPane.OK_OPTION);
				}
				modelo.setValueAt("", e.getFirstRow(), e.getColumn());
			}
		}
			
	}
		
}

