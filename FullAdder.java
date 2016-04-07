// inputs : 0,0,0,0,1,1,1,1;0,0,1,1,0,0,1,1;0,1,0,1,0,1,0,1
// outputs : 0,1,1,0,1,0,0,1;0,0,0,1,0,1,1,1
//import java.io.*;

public class FullAdder{

	static Form frm;
	static float [] weights;
	public FullAdder(String asd){//  throws IOException
		if(asd.equals("egit_fulladder")){
			Hesapla();
		}else{
			testEt();
		}
	}

	public void testEt(){
		int x1 = Integer.parseInt(frm.jt5.getText());
		int x2 = Integer.parseInt(frm.jt6.getText());
		int x3 = Integer.parseInt(frm.jt7.getText());

		float net1 = x1*weights[0]+x2*weights[1]+x3*weights[2]+1*weights[3];
		float net2 = x1*weights[4]+x2*weights[5]+x3*weights[6]+1*weights[7];
		float sigmoid1 = 1.0f / (1.0f + (float) Math.exp(-net1));
		float sigmoid2 = 1.0f / (1.0f + (float) Math.exp(-net2));

		float net3 = sigmoid1*weights[8]+sigmoid2*weights[9]+1*weights[10];
		float net4 = sigmoid1*weights[11]+sigmoid2*weights[12]+1*weights[13];
		float sigmoid3 = 1.0f / (1.0f + (float) Math.exp(-net3));
		float sigmoid4 = 1.0f / (1.0f + (float) Math.exp(-net4));

		frm.sonuc_fulladder.setText("Sonuc : 1.cikti : "+sigmoid3+" 2. cikti : "+sigmoid4);


	}

	public void Hesapla(){//  throws IOException
		String girdiler = frm.jt1.getText(); //string parcala
		String ciktilar = frm.jt2.getText(); //string parcala
		int [][] inputs = new int[3][8];
		int [][] outputs = new int[2][8];
		weights = new float[14];
		float [] weights1 = new float[14];
		float alfa = 0.25f;
		float hata = 0.0f, hata2 = 0.0f;
		int bias = 1;
		float Delta1_1 = 0.0f, Delta2_1 = 0.0f, Delta3_1 = 0.0f, Delta4_1 = 0.0f,
		Delta1_2= 0.0f, Delta2_2 = 0.0f, Delta3_2 = 0.0f, Delta4_2 = 0.0f,
		Delta5_1 = 0.0f, Delta6_1 = 0.0f, Delta7_1 = 0.0f,
		Delta8_1 = 0.0f, Delta9_1 = 0.0f, Delta10_1 = 0.0f;

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
		String [] cikti_satir = ciktilar.split(";");
		for(int i=0;i<cikti_satir.length;i++){
			String [] cikti_sutun = cikti_satir[i].split(",");
			for(int k=0;k<cikti_sutun.length;k++){
				outputs[i][k] = Integer.parseInt(cikti_sutun[k]);
				//System.out.println("inputs["+i+"]["+k+"] = "+inputs[i][k]);
			}
		}

		//agirliklari hesapla
		for(int k=0;k<14;k++){
			weights[k] = (-1)+(float)(Math.random()*2);
			System.out.println("weigths["+k+"] = "+weights[k]);
		}

		for(int i=0;i<14;i++){
			weights1[i] = 0.0f;
		}

		for (int i=0;i<2000;i++){
			for(int t=0;t<8;t++){
				//feedforward
				float net1 = inputs[0][t]*weights[0]+inputs[1][t]*weights[1]+inputs[2][t]*weights[2]+bias*weights[3];
				float net2 = inputs[0][t]*weights[4]+inputs[1][t]*weights[5]+inputs[2][t]*weights[6]+bias*weights[7];
				float sigmoid1 = 1.0f / (1.0f + (float) Math.exp(-net1));
				float sigmoid2 = 1.0f / (1.0f + (float) Math.exp(-net2));

				float net3 = sigmoid1*weights[8]+sigmoid2*weights[9]+bias*weights[10];
				float net4 = sigmoid1*weights[11]+sigmoid2*weights[12]+bias*weights[13];
				float sigmoid3 = 1.0f / (1.0f + (float) Math.exp(-net3));
				float sigmoid4 = 1.0f / (1.0f + (float) Math.exp(-net4));
				//System.out.println("sigmoid1 : "+sigmoid1+" sigmoid2 : "+sigmoid2+" sigmoid3 : "+sigmoid3);

				System.out.println(i+") istenen2 : "+outputs[0][t]+" istenen2 : "+outputs[1][t]+"\t hesaplanan1 : "+sigmoid3+" hesaplanan2 : "+sigmoid4);

				//backpropagition
				//cikti katmani hesaplama
				hata = (outputs[0][t]-sigmoid3)*(sigmoid3)*(1-sigmoid3);
				hata2 = (outputs[1][t]-sigmoid4)*(sigmoid4)*(1-sigmoid4);

				//System.out.println("hata : "+hata);
				//System.out.println("1) "+Delta1_1+" 2) "+Delta2_1+" 3) "+Delta3_1);
				//System.out.println("1) "+Delta1_2+" 2) "+Delta2_2+" 3) "+Delta3_2);
				//System.out.println(" + "+hata+" "+Delta6_1);

				Delta10_1 = alfa*hata2*1+0.6f*Delta10_1;
				Delta9_1 = alfa*hata2*sigmoid2+0.6f*Delta9_1;
				Delta8_1 = alfa*hata2*sigmoid1+0.6f*Delta8_1;

				Delta7_1 = alfa*hata*1+0.6f*Delta7_1;
				Delta6_1 = alfa*hata*sigmoid2+0.6f*Delta6_1;
				Delta5_1 = alfa*hata*sigmoid1+0.6f*Delta5_1;

				//ara katman hesaplama
				float hata4 = hata*weights[6]*sigmoid1*(1.0f-(sigmoid1));
				float hata5 = hata*weights[7]*sigmoid2*(1.0f-(sigmoid2));
				//System.out.println("* "+hata4+" * "+hata5+" *weigths[6] : "+weights[6]+" *weigths[7] : "+weights[7]);

				Delta1_1 = alfa*hata4*inputs[0][t]+0.6f*Delta1_1;//net1 icin
				Delta2_1 = alfa*hata4*inputs[1][t]+0.6f*Delta2_1;//net1 icin
				Delta3_1 = alfa*hata4*inputs[2][t]+0.6f*Delta3_1;//net1 icin
				Delta4_1 = alfa*hata4*1+0.6f*Delta4_1;//net1 icin

				Delta1_2 = alfa*hata5*inputs[0][t]+0.6f*Delta1_2;//net2 icin
				Delta2_2 = alfa*hata5*inputs[1][t]+0.6f*Delta2_2;//net2 icin
				Delta3_2 = alfa*hata5*inputs[2][t]+0.6f*Delta3_2;//net2 icin
				Delta4_2 = alfa*hata5*1+0.6f*Delta4_2;//net2 icin

				weights[0] += Delta1_1;
				weights[1] += Delta2_1;
				weights[2] += Delta3_1;
				weights[3] += Delta4_1;
				weights[4] += Delta1_2;
				weights[5] += Delta2_2;
				weights[6] += Delta3_2;
				weights[7] += Delta4_2;

				weights[8] += Delta5_1;
				weights[9] += Delta6_1;
				weights[10] += Delta7_1;
				weights[11] += Delta8_1;
				weights[12] += Delta9_1;
				weights[13] += Delta10_1;

				for(int x=0;x<14;x++){
					weights1[x]= weights[x];
					//System.out.println(x+". "+weights[x]);
				}

				frm.lm0.addElement(i+") "+weights[0]+weights[1]+weights[2]+weights[3]+weights[4]+weights[5]+weights[6]
				+weights[7]+weights[8]+weights[9]+weights[10]+weights[11]+weights[12]+weights[13]);

			}
			/*System.out.println("-----------------------------------");
			for(int a = 0;a<14;a++){
				str += weights[a];
				if(a<13){
					str += ",";
				}
			}
			str += ";\n";*/
		}

		/*FileWriter fileWriter = new FileWriter("weights.txt", false);
		BufferedWriter bWriter = new BufferedWriter(fileWriter);
		bWriter.write(str);
		bWriter.close();*/

		for(int a = 0; a<14;a++){
			System.out.println(a+". Ağırlık : "+weights[a]);
		}


		return ;

	}


	public static void main(String [] asdada){
		frm = new Form();
	}



}
