package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextArea;

import Desktop.Desktop;

public class DesktopShowList extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Desktop> validDesktops;
	Desktop selectedDesk;

	
	public DesktopShowList(Map<String,Desktop> map,String invalids) {
		validDesktops=map;
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		String[] deskNames = new String[validDesktops.size()];
		int index=0;
		for(String s : validDesktops.keySet()){
			deskNames[index++]=s;
		}
		
		
		final JList deskList = new JList(validDesktops.keySet().toArray());
		
		add(deskList,BorderLayout.NORTH );
		
		JButton button = new JButton("OK");
		JTextArea inv=new JTextArea("Invalid boards: \n" + invalids);
		add(inv,BorderLayout.SOUTH);
		button.setPreferredSize(new Dimension(20,20));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedDesk=validDesktops.get(deskList.getSelectedValue());
				dispose();
				
			}});
		add(button, BorderLayout.CENTER);
		pack();
		setVisible(true); 
	}
	
	public Desktop getSelectedDesk(){
		return selectedDesk;
	}
	
}

