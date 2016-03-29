import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.*;

public class Form implements ActionListener{

	JTextField jt1,jt2,jt3,jt4,jt5,jt6,jt7;
	JLabel sonuc_xor, sonuc_fulladder;
	JButton islem_xor,islem_fulladder,egit_xor,egit_fulladder,testEt_xor,testEt_fulladder;
	DefaultListModel<String> lm0;

	public Form(){
		JFrame jfrm = new JFrame("Yapay Sinir Ağları - Nurullah IŞIK");
		jfrm.setSize(800,700);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setLayout(new GridLayout(3,1));

		JPanel jpn = new JPanel();
		islem_xor = new JButton("XOR Problemi");
		islem_fulladder = new JButton("Full Adder Problemi");
		islem_xor.addActionListener(this);
		islem_fulladder.addActionListener(this);
		jpn.add(new JLabel("İşlem Secin : "));
		jpn.add(islem_xor);
		jpn.add(islem_fulladder);
		jfrm.add(jpn);

		JPanel jpn1 = new JPanel();
		jpn1.setLayout(new GridLayout(4,2));
		JPanel jpn2 = new JPanel();
		jpn2.setLayout(new GridLayout(1,1));


		JLabel jl1 = new JLabel("Girdiler : ");
		JLabel jl2 = new JLabel("Çıktılar : ");

		jt1 = new JTextField("");
		jt2 = new JTextField("");

		jt3 = new JTextField(10);
		jt4 = new JTextField(10);
		jt5 = new JTextField(10);
		jt6 = new JTextField(10);
		jt7 = new JTextField(10);

		egit_xor = new JButton("Egit(XOR)");
		egit_fulladder = new JButton("Egit(FullAdder)");
		testEt_xor = new JButton("Test Et(XOR)");
		testEt_fulladder = new JButton("Test Et(FullAdder)");

		egit_xor.addActionListener(this);
		testEt_xor.addActionListener(this);
		egit_fulladder.addActionListener(this);
		testEt_fulladder.addActionListener(this);


		lm0 = new DefaultListModel<String>();
		JList<String> jlist0 = new JList<String>(lm0);
		JScrollPane jscp0 = new JScrollPane(jlist0);
		jscp0.setPreferredSize(new Dimension(100,300));


		jpn1.add(jl1);
		jpn1.add(jt1);

		jpn1.add(jl2);
		jpn1.add(jt2);

		JPanel jtest = new JPanel();
		sonuc_xor = new JLabel("Sonuc : ");
		jtest.add(jt3);
		jtest.add(jt4);
		jtest.add(sonuc_xor);
		jpn1.add(jtest);

		JPanel jtest1 = new JPanel();
		sonuc_fulladder = new JLabel("Sonuc : ");
		jtest1.add(jt5);
		jtest1.add(jt6);
		jtest1.add(jt7);
		jtest1.add(sonuc_fulladder);
		jpn1.add(jtest1);


		JPanel jpna = new JPanel();
		JPanel jpnb = new JPanel();
		jpna.add(egit_xor);
		jpna.add(testEt_xor);
		jpnb.add(egit_fulladder);
		jpnb.add(testEt_fulladder);

		jpn1.add(jpna);
		jpn1.add(jpnb);

		jpn2.add(jscp0);

		jfrm.add(jpn1);
		jfrm.add(jpn2);


		jfrm.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("XOR Problemi")){
			jt1.setText("0,0,1,1;0,1,0,1");
			jt2.setText("0,1,1,0");
		}else if(e.getActionCommand().equals("Full Adder Problemi")){
			jt1.setText("0,0,0,0,1,1,1,1;0,0,1,1,0,0,1,1;0,1,0,1,0,1,0,1");
			jt2.setText("0,1,1,0,1,0,0,1;0,0,0,1,0,1,1,1");
		}
		else if(e.getActionCommand().equals("Egit(XOR)")){
			Connection conn = new Connection("egit");

		}else if(e.getActionCommand().equals("Egit(FullAdder)")){
			FullAdder fa = new FullAdder("egit_fulladder");

		}else if(e.getActionCommand().equals("Test Et(XOR)")){

			Connection conn = new Connection("testEt");

		}else if(e.getActionCommand().equals("Test Et(FullAdder)")){

			FullAdder fa = new FullAdder("testEt_fulladder");

		}

	}

	public static void main(String [] asfasda){
		Form frm = new Form();
	}

}
