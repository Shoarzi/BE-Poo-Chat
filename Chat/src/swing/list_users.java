package swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import main_classes.Client;
import main_classes.UserList;

public class list_users extends JFrame implements ActionListener{

	public static ArrayList<Boolean> ListStateButton=new ArrayList<Boolean>();
	public ArrayList<JButton> list_button= new ArrayList<JButton>();
	private JScrollPane scroll;
	private JPanel panellist;
	private int nbUser;

	public list_users(Client user) {
		try { 
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); 
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.setTitle("Users connected");
		this.setBounds(100, 100, 325, 354);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));

		panellist=new JPanel();
		scroll = new JScrollPane(panellist, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.getContentPane().add(scroll);		

		JPanel panel=new JPanel();
		panel.setBorder ( new TitledBorder ( new EtchedBorder ()) );
		
		/*TODO : change ActionListener to make a correct Refresh
		JButton refreshButton=new JButton("Refresh");
		refreshButton.addActionListener(new refreshListener(this,user));
		panel.add(refreshButton);*/
		
		JButton changeButton=new JButton("Change Login");
		changeButton.addActionListener(new changeListener(this,user));

		JButton disconnectButton= new JButton("Disconnect");
		disconnectButton.addActionListener(new disconnectListener(this,user ));

		panel.add(changeButton);
		panel.add(disconnectButton);
		this.getContentPane().add(panel, BorderLayout.SOUTH);		

		changeButtonList() ; 

		this.setVisible(true);
	}
	
	public void changeButtonList() {
		nbUser=UserList.nb_user;
		int height;
		if(nbUser<5)
			height=5;
		else 
			height=nbUser/2;
		panellist.setLayout(new GridLayout(height+1,2));
		for(int i=0;i<nbUser;i++) {
			JButton Button= new JButton((String) UserList.listUserName.get(i));
			list_button.add(Button);
			ListStateButton.add(false);
			Button.addActionListener(new buttonListener(i));
			panellist.add(Button);
		}
	}

	//generic button listener to open a chatbox
	class buttonListener implements ActionListener{
		int index;
		public buttonListener(int i) {
			this.index=i;
		}
		public void actionPerformed(ActionEvent e) {
			setTrueStateButton(index);
		}
	}

	//button listener for the disconnect button
	class disconnectListener implements ActionListener{
		list_users frame_to_close ; 
		Client this_user ; 
		public disconnectListener(list_users fr, Client tu) {
			frame_to_close = fr ; 
			this_user = tu ; 
		}
		public void actionPerformed(ActionEvent e) {
			this_user.Broadcast(this_user.getHost()+">"+this_user.getPseudo()+">"+this_user.getUserId()+">") ; 
			frame_to_close.dispose();
			UserList.setDisconnect(true);
		}
	}

	//button listener for the refresh button
	/*class refreshListener implements ActionListener{
		list_users frame ;
		Client thisUser;
		public refreshListener(list_users fr, Client user) {
			frame = fr; 
			thisUser = user;
		}
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			new list_users(thisUser);
		}
	}*/

	//Button Listener for the "change name" button
	class changeListener implements ActionListener{
		list_users frame ;
		Client thisUser;

		public changeListener(list_users fr,Client user) {
			frame= fr; 
			thisUser=user;

		}
		public void actionPerformed(ActionEvent e) {
			new changeLoginWindow(thisUser, "New Login :");
			frame.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Auto-generated method stub
	}

	public static void setTrueStateButton(int index) {
		ListStateButton.set(index, true) ; 
	}

	public static void setFalseStateButton(int index) {
		ListStateButton.set(index, false) ; 
	}

	public JScrollPane getJScrollPane() { 
		return scroll ; 
	}
}