package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DialogNotif extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 780190742580926309L;
	protected JLabel jl;
	protected JButton ok;
	protected JPanel jp;
	
	public DialogNotif(String msg) {
		
		jl = new JLabel("<html><div align='center'>" + msg + "</div></html>");
		ok = new JButton("OK");
		jp = new JPanel();
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setForeground(Color.red);
		jl.setFont((new Font("Arial", Font.PLAIN, 20)));
		jp.setLayout(new FlowLayout());
		jp.add(ok);
		add(jl);
		add(jp, BorderLayout.SOUTH);
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		setTitle("Notification");
		setMinimumSize(new Dimension(450, 200));
		setLocationRelativeTo(getOwner());
		this.setSize(500, 200);
		setVisible(true);
	}

}
