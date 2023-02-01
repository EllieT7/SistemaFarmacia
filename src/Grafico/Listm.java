package Grafico;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class Listm extends JTextArea implements ListCellRenderer<Object> {
	  protected Listm(int rows, int cols, Color color) {
		super(rows,cols);
	    setBorder(BorderFactory.createLineBorder(color));
	  }

	  public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value,
	      int index, boolean isSelected, boolean cellHasFocus) {

	    this.setFont(new Font("Monospaced", Font.PLAIN, 14));
	  
	    this.setText(value.toString());
	    
	    if (cellHasFocus) {
	      setBackground(Color.LIGHT_GRAY);
	      
	    } else if (isSelected) {
	      setBackground(Color.LIGHT_GRAY);
	    } else {
	      setBackground(null);
	    }
	    return this;
	  }
}