package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class list_users extends JFrame implements ActionListener{
	
	public ArrayList<Boolean> ListStateButton=new ArrayList<Boolean>();
	public ArrayList<JButton> list_button= new ArrayList<JButton>();
	
	public list_users(ArrayList<String> ListUser, String User) {
		this.setTitle("Users connected");
		this.setBounds(100, 100, 346, 354);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Box verticalBox = Box.createVerticalBox();
		this.getContentPane().add(verticalBox, BorderLayout.CENTER);		
		
		int indexOfUser=ListUser.indexOf(User);
		int n=ListUser.size();
		for(int i=0;i<n;i++) {
			if(i!=indexOfUser) {
				JButton Button = new JButton( (String) ListUser.get(i));
				list_button.add(Button);
				ListStateButton.add(false);
				Button.addActionListener(new buttonListener(i));
				verticalBox.add(Button);
			}
			
		}
			
		this.setVisible(true);
	}
	
	//check ListStateButton and return the index of the button that has been clicked
	int windowToOpen()
	{
		int i=0;
		int window_to_open=-9000;
		boolean open=false;
		
		while(!open)
		{
			for(i=0;i< this.list_button.size();i++)
			{
				open |=ListStateButton.get(i);
				if(this.ListStateButton.get(i))
					window_to_open=i;
			}
		} 
		return window_to_open ;
		
	}
	
	class buttonListener implements ActionListener{
		int index;
		public buttonListener(int i) {
			this.index=i;
		}
		public void actionPerformed(ActionEvent e) {
			ListStateButton.set(index,true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
