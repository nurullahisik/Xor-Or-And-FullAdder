//import java.io.*;

public class Connection{
	static Form frm;
	static float [] weights;
	public Connection(String asd){//  throws IOException
		if(asd.equals("egit")){
			Hesapla();
		}else{
			testEt();
		}
	}

	public void testEt(){
		int x1 = Integer.parseInt(frm.jt3.getText());
		int x2 = Integer.parseInt(frm.jt4.getText());

		float net1 = x1*weights[0]+x2*weights[1]+1*weights[2];
		float net2 = x1*weights[3]+x2*weights[4]+1*weights[5];
		float sigmoid1 = 1.0f / (1.0f + (float) Math.exp(-net1));
		float sigmoid2 = 1.0f / (1.0f + (float) Math.exp(-net2));

		float net3 = sigmoid1*weights[6]+sigmoid2*weights[7]+1*weights[8];
		float sigmoid3 = 1.0f / (1.0f + (float) Math.exp(-net3));

		frm.sonuc_xor.setText("Sonuc : "+sigmoid3);


	}

	public void Hesapla(){// throws IOException

		String girdiler = frm.jt1.getText(); //string parcala
		String ciktilar = frm.jt2.getText(); //string parcala
		int [][] inputs = new int[2][4];
		int [] outputs = new int[4];
		weights = new float[9];
		float [] weights1 = new float[9];
		float alfa = 0.25f;
		int bias = 1;
		float Delta1_1 = 0.0f, Delta2_1 = 0.0f, Delta3_1 = 0.0f, Delta4_1 = 0.0f,
		Delta1_2= 0.0f, Delta2_2 = 0.0f, Delta3_2 = 0.0f,  Delta5_1 = 0.0f, Delta6_1 = 0.0f;

		//String str = "";

		// girdileri al
		String [] satir = girdiler.split(";");
		for(int i=0;i<satir.length;i++){
			String [] sutun = satir[i].split(",");
			for(int k=0;k<sutun.length;k++){
				inputs[i][k] = Integer.parseInt(sutun[k]);
				//System.out.println("inputs["+i+"]["+k+"] = "+inputs[i][k]);
			}
		}

		//cıktıları al
		String [] cikti = ciktilar.split(",");

		for(int k=0;k<cikti.length;k++){
			outputs[k] = Integer.parseInt(cikti[k]);
			//System.out.println("outputs["+k+"] = "+outputs[k]);
		}

		//agirliklari hesapla
		for(int k=0;k<9;k++){
			weights[k] = (-1)+(float)(Math.random()*2);
			//System.out.println("weigths["+k+"] = "+weights[k]);
		}
	/*	weights[0] = -0.3409f;// net1 icin
		weights[1] = -0.1180f;// net1 icin
		weights[2] = -0.3647f;//net1 icin
		weights[3] = 0.4497f;//net2 icin
		weights[4] = -0.4795f;//net2 icin
		weights[5] = 0.7010f;//net2 icin
		weights[6] = -0.1530f;//net3 icin
		weights[7] = -0.6892f;//net3 icin
		weights[8] = 0.5990f;//net3 icin
*/
		for(int i=0;i<9;i++){
			weights1[i] = 0.0f;
		}

		for(int i=0;i<2000;i++){
			for(int t=0;t<4;t++){
				//feedforward
				float net1 = inputs[0][t]*weights[0]+inputs[1][t]*weights[1]+bias*weights[2];
				float net2 = inputs[0][t]*weights[3]+inputs[1][t]*weights[4]+bias*weights[5];
				float sigmoid1 = 1.0f / (1.0f + (float) Math.exp(-net1));
				float sigmoid2 = 1.0f / (1.0f + (float) Math.exp(-net2));

				float net3 = sigmoid1*weights[6]+sigmoid2*weights[7]+bias*weights[8];
				float sigmoid3 = 1.0f / (1.0f + (float) Math.exp(-net3));
				//System.out.println("sigmoid1 : "+sigmoid1+" sigmoid2 : "+sigmoid2+" sigmoid3 : "+sigmoid3);

				System.out.println("islenilen cikti : "+outputs[t]+"\thesaplanan cikti : "+sigmoid3);

				//backpropagition
				//cikti katmani hesaplama
				float hata = (outputs[t]-sigmoid3)*(sigmoid3)*(1-sigmoid3);

				//System.out.println("hata : "+hata);
				//System.out.println("1) "+Delta1_1+" 2) "+Delta2_1+" 3) "+Delta3_1);
				//System.out.println("1) "+Delta1_2+" 2) "+Delta2_2+" 3) "+Delta3_2);
				//System.out.println(" + "+hata+" "+Delta6_1);

				Delta4_1 = alfa*hata*sigmoid1+0.6f*Delta4_1;
				Delta5_1 = alfa*hata*sigmoid2+0.6f*Delta5_1;
				Delta6_1 = alfa*hata*1+0.6f*Delta6_1;


				//ara katman hesaplama
				float hata4 = hata*weights[6]*sigmoid1*(1.0f-(sigmoid1));
				float hata5 = hata*weights[7]*sigmoid2*(1.0f-(sigmoid2));
				//System.out.println("* "+hata4+" * "+hata5+" *weigths[6] : "+weights[6]+" *weigths[7] : "+weights[7]);

				Delta1_1 = alfa*hata4*inputs[0][t]+0.6f*Delta1_1;//net1 icin
				Delta2_1 = alfa*hata4*inputs[1][t]+0.6f*Delta2_1;//net1 icin
				Delta3_1 = alfa*hata4*1+0.6f*Delta3_1;//net1 icin

				Delta1_2 = alfa*hata5*inputs[0][t]+0.6f*Delta1_2;//net2 icin
				Delta2_2 = alfa*hata5*inputs[1][t]+0.6f*Delta2_2;//net2 icin
				Delta3_2 = alfa*hata5*1+0.6f*Delta3_2;//net2 icin

				weights[0] += Delta1_1;
				weights[1] += Delta2_1;
				weights[2] += Delta3_1;
				weights[3] += Delta1_2;
				weights[4] += Delta2_2;
				weights[5] += Delta3_2;


				weights[8] += Delta6_1;
				weights[7] += Delta5_1;
				weights[6] += Delta4_1;

				for(int x=0;x<9;x++){
					weights1[x]= weights[x];
					//System.out.println(x+". "+weights[x]);
				}

				frm.lm0.addElement(i+") "+weights[0]+weights[1]+weights[2]+weights[3]+weights[4]+weights[5]+weights[6]
				+weights[7]+weights[8]);

			}
			/*System.out.println("-------------------------------------");
			for(int a = 0;a<9;a++){
				str += weights[a];
				if(a<8){
					str += ",";
				}
			}
			str += ";\n";*/
		}

		/*FileWriter fileWriter = new FileWriter("weights.txt", false);
		BufferedWriter bWriter = new BufferedWriter(fileWriter);
		bWriter.write(str);
		bWriter.close();*/

		for(int a = 0; a<9;a++){
			System.out.println(a+". Ağırlık : "+weights[a]);
		}


		return ;
	}

	public static void main(String [] asadsasd){
		frm = new Form();
	}

}
//17ef
//7e60
