package Util;

import java.util.List;

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
		if(e.UPDATE==e.getType()){
			if(e.getFirstRow()!=-1 || e.getColumn()!=-1){
			if(codigos.get(e.getFirstRow())==modelo.getValueAt(e.getFirstRow(), e.getColumn())){
				System.out.println("funciona");
				//Añadir aqui, si no coincide-->Error y ventana de error
				//Si coincide se reduce en uno la cantidad de productos y si llega a 0 se vuelve no editable(o se intenta).
			}
			}
		}
			
	}
		
}

