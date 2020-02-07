package swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import database.datab;
import main_classes.Client;
import main_classes.UserList;

public class chat extends JFrame {

	private JTextField WriteMessageZone;
	private JTextArea ReadMessagesZone;
	private JPanel middlePanel;
	private JScrollPane scroll ; 

	public chat(String UserName, Client main_user) {
		String uuid = UserList.listUserId.get(UserList.listUserName.indexOf(UserName)) ; 

		//Pannel where messages are displayed
		middlePanel=new JPanel();
		this.setTitle(UserName);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Messages" ) );

		//Text Area of the Pannel
		ReadMessagesZone = new JTextArea ( 50, 130 );
		ReadMessagesZone.setEditable ( false ); 

		//Scroll Pane in case of too many messages
		scroll = new JScrollPane ( ReadMessagesZone );
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		middlePanel.add ( scroll );
		this.add ( middlePanel );

		//SOUTH : write text zone
		JPanel panelWrite = new JPanel();
		panelWrite.setMaximumSize(new Dimension(70, 20));
		this.getContentPane().add(panelWrite, BorderLayout.SOUTH);

		//Implementation of the Message zone
		JLabel lblMessage = new JLabel("Message :");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		panelWrite.add(lblMessage);

		WriteMessageZone = new JTextField();
		WriteMessageZone.setMaximumSize(new Dimension(45, 20));
		panelWrite.add(WriteMessageZone);
		WriteMessageZone.setColumns(45);

		JButton btnSend = new JButton("Send");
		btnSend.setHorizontalAlignment(SwingConstants.RIGHT);
		panelWrite.add(btnSend);

		//Show the historised message when first open
		if(uuid.compareTo(main_user.getUserId())<0) 
			refresh(main_user.getUserId(),uuid) ; 
		else 
			refresh(uuid,main_user.getUserId()) ; 		
		this.getRootPane().setDefaultButton(btnSend);
		this.setVisible(true);

		//Send the Message when Enter or when Send is clicked
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnSend) {

					//Sending Message
					main_user.Send_Message(WriteMessageZone.getText(), UserName, UserList.listUserAddress.get(UserList.listUserName.indexOf(UserName))) ;  

					//add the message to the history :
					if(uuid.compareTo(main_user.getUserId())<0) {
						datab.Historise(main_user.getUserId(),uuid,true,WriteMessageZone.getText());
						refresh(main_user.getUserId(),uuid) ; 
					}
					else {
						datab.Historise(uuid,main_user.getUserId(),false,WriteMessageZone.getText());
						refresh(uuid,main_user.getUserId()) ; 
					}

					//reset writing zone
					WriteMessageZone.setText("");
				}
			}
		});
	}

	public void refresh(String Id1, String Id2){

		String DB_URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tpservlet_05";
		//ReadMessagesZone.invalidate();
		// Database credentials
		String USER = "tpservlet_05";
		String PASS = "uinohG6e";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//STEP 4: Execute a query
			conn.createStatement();
			if (!datab.TableExists(Id1,Id2)) {
				datab.create_table(Id1,Id2) ; 
			}	
			String tablename = "History" + Id1 + Id2 ; 
			String query = "SELECT * FROM "+ tablename;

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			String Contenu_conv="";

			while (rs.next())
			{
				String User1 = UserList.listUserName.get(UserList.listUserId.indexOf((rs.getString("uid"))));
				String User2 = UserList.listUserName.get(UserList.listUserId.indexOf((rs.getString("other_uid"))));
				boolean direct_sense = rs.getBoolean("direct_sense");
				String dateSent = rs.getString("date");
				String Msg = rs.getString("line_text");

				// print the results
				if (direct_sense) {
					Contenu_conv += dateSent + " " + User1 + " : \n" + Msg + "\n\n"; 
				}
				else { 
					Contenu_conv += dateSent + " " + User2 + " : \n" + Msg + "\n\n"; 
				} 
			}
			ReadMessagesZone.setText(Contenu_conv);
			st.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();	
		}  
		SwingUtilities.updateComponentTreeUI(ReadMessagesZone);
	}

	public int chatCloseOperation(String foreign_user, Client user) {
		int index = UserList.listUserName.indexOf(foreign_user) ; 
		if (index != -1) {
			user.getChatList().remove(foreign_user) ; 
			//UserList.listUserChatOpenned.set(index,false) ; 
			list_users.setFalseStateButton(index) ;
		}
		return JFrame.DISPOSE_ON_CLOSE ; 
	}

	public JTextArea getTextArea() {
		return this.ReadMessagesZone ; 
	}

	public JPanel getPanel() {
		return this.middlePanel;
	}

	public JScrollPane getScrollPane() {
		return this.scroll;
	}
}