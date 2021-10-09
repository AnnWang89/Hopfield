package nn.hw3_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Controller  {
	@FXML
	Pane _main_pane;
	@FXML
	Slider _learning_times;
	@FXML
	Text _show_words_train;
	@FXML
	Text _show_words_test;
	@FXML
	Text _show_words_output;
	@FXML
	Text _show_words_train1;
	@FXML
	Text _show_words_test1;
	@FXML
	Text _show_words_output1;
	@FXML
	Text _show_words_train2;
	@FXML
	Text _show_words_test2;
	@FXML
	Text _show_words_output2;
	@FXML
	Text _file_name;
	@FXML
	TextArea _modify_test;
	@FXML
	CheckBox _modify_ornot;
	@FXML
	CheckBox _p_ornot;
	@FXML
	CheckBox _theta_zero;
	@FXML
	CheckBox _no_same;
	public static int p;
	public static int learning_time=5;
	public static char[][] input_x_train_char;//9*12 or 10*10
	public static int[][][] output_x;
	public static int [][] input_x_train_int;
	public static int [][] input_x_test_int;
	public static char[][] input_x_test_char;
	public static int index=0;
	public static int input_number=0;
	public static int matrix_length=0;
	public static int MAX_TERMS=500;
	public static double theta[];
	public static double W[][];
	FileInputStream Training;
	FileInputStream Testing;
	public static String[] print_out;
	public static String print_out2="";
	public void onSliderChanged_learning_times() throws Exception {//set learning times
		
	    int sliderValue_learning_times =  (int) _learning_times.getValue();
	    learning_time = sliderValue_learning_times;
	    System.out.println(sliderValue_learning_times + " ");
	    Training = new FileInputStream("Data/Bonus_Training.txt");
		Testing = new FileInputStream("Data/Bonus_Testing.txt");
		_file_name.setText("Bonus");
		calculate();

	}
	public void _Choose_Bonus(MouseEvent event) throws IOException {
		Training = new FileInputStream("Data/Bonus_Training.txt");
		Testing = new FileInputStream("Data/Bonus_Testing.txt");
		_file_name.setText("Bonus");
		calculate();
	}
	public void _Choose_Basic(MouseEvent event) throws IOException {
		Training = new FileInputStream("Data/Basic_Training.txt");
		Testing = new FileInputStream("Data/Basic_Testing.txt");
		_file_name.setText("Basic");
		calculate();
	}
	public void _show_modify(MouseEvent event) throws IOException {
		_modify_test.setVisible(true);
	}
	public void _modify_invisable(MouseEvent event) throws IOException {
		_modify_test.setVisible(false);
	}
	public void calculate() throws IOException{
		print_out=new String[3];
		
		byte[] allByte = Training.readAllBytes();
		input_x_train_char=new char[MAX_TERMS][MAX_TERMS];
		input_x_train_int=new int[MAX_TERMS][MAX_TERMS];
		String input = new String(allByte, "UTF-8");
		input_file(input,input_x_train_char,input_x_train_int);		
		/*
		for(int i=0;i<input_number;i++)
		{
			for(int j=0;j<index;j++)
			{
				System.out.print(input_x_train_char[i][j]);
				
			}
			System.out.println();
		}
		*/
		allByte = Testing.readAllBytes();
		input_x_test_char=new char[MAX_TERMS][MAX_TERMS];
		input_x_test_int=new int[MAX_TERMS][MAX_TERMS];
		String input_test = new String(allByte, "UTF-8");
		//train
		if(_modify_ornot.isSelected())
		{
			input_test=_modify_test.getText();
			_modify_test.setText(input_test);
		}else
		{
			_modify_test.setText(input);
		}
		
		input_file(input_test,input_x_test_char,input_x_test_int);	
		//System.out.println("index= " + index);
		//System.out.println("input_number= " + input_number);
		//print input
		/*
		for(int i=0;i<input_number;i++)
		{
			for(int j=0;j<index;j++)
			{
				System.out.print(input_x_train_char[i][j]);
				
			}
			System.out.println();
		}
		System.out.println("Test");
		
		for(int i=0;i<input_number;i++)
		{
			for(int j=0;j<index;j++)
			{
				System.out.print(input_x_test_char[i][j]);
				
			}
			System.out.println();
		}
		*/
		//_show_words.setText(input);
		
		
		/*
		 * learning
		 */
		
		//set matrix
		p=index;
		W=new double[p][p];
		for(int i=0;i<p;i++)
		{
			for(int j=0;j<p;j++)
			{
				W[i][j]=0;
				if(i!=j)
				{
					for(int k=0;k<input_number;k++)
					{
						boolean same=false;
						//W[i][j]=W[i][j]+input_x_train_int[k][i]*input_x_train_int[k][j];
						
						for(int m=0;m<k;m++)
						{
							if(check_same(k,m))
							{
								same=true;
								//System.out.println(m+" & "+k+" they are same");
								break;
							}
						}
						if(!same||!_no_same.isSelected())
						{
							W[i][j]=W[i][j]+input_x_train_int[k][i]*input_x_train_int[k][j];
						}
						
					}
					if(_p_ornot.isSelected())
						W[i][j]=W[i][j]/p;//1/p or not
				}
				
			}
		}
		//set theta
		theta = new double [p];
		for(int i=0;i<p;i++)
		{
			theta[i]=0;
			if(!_theta_zero.isSelected())
			{
				for(int j=0;j<p;j++)
				{
					theta[i]=theta[i]+W[i][j];
				}
			}
			
			
		}
		/*
		 * retrieval
		 */
		//input_x_test_int=input_x_train_int;
		output_x=new int  [learning_time+1][MAX_TERMS][p];
		output_x[0]=input_x_test_int;
		double u=0;
		for(int n=1;n<=learning_time;n++)
		{
			for(int k=0;k<input_number;k++)//need to change
			{
				for(int j=0;j<p;j++)
				{
					u=0;
					for(int i=0;i<p;i++)
					{
						u=u+W[j][i]*output_x[n-1][k][i];
					}
					int x=output_x[n-1][k][j];
					output_x[n][k][j]=sgn_function(u-theta[j],x);
				}
				//System.out.println();
			}
		}
		
		//System.out.println("Output:");
		print_char(output_x[learning_time]);
		_show_words_output.setText(print_out[0]);
		_show_words_output1.setText(print_out[1]);
		_show_words_output2.setText(print_out[2]);
		//System.out.println("input_x_test:");
		print_char(input_x_test_int);
		_show_words_test.setText(print_out[0]);
		_show_words_test1.setText(print_out[1]);
		_show_words_test2.setText(print_out[2]);
		//System.out.println("input_x_train:");
		print_char(input_x_train_int);
		_show_words_train.setText(print_out[0]);
		_show_words_train1.setText(print_out[1]);
		_show_words_train2.setText(print_out[2]);
		
	}
	public static int sgn_function(double in , int X)
	{
		if(in < 0)
			return -1;
		else if(in>0)
			return 1;
		else 
			return X;
	}
	public static void input_file(String in,char[][] input_char,int[][] input_int)
	{
		//input file
		index=0;
		input_number=0;
		Boolean count=true;
		matrix_length=0;
		for(int i=0;i<in.length();i++)
		{
			char a=in.charAt(i);
			
			if(a=='1' || a==' ')
			{
				if(count)
					matrix_length++;
				if(a=='1') 
				{
					input_int[input_number][index]=1;
		
				}else
				{
					input_int[input_number][index]=-1;
				}
				input_char[input_number][index++] = a;
			}else if(in.charAt(i+1)!='1'&&in.charAt(i+1)!=' '&&in.charAt(i+2)!='1'&&in.charAt(i+2)!=' '&&in.charAt(i+3)!='1'&&in.charAt(i+3)!=' ')
			{
				input_number++;
				index=0;
			}else
			{
				count=false;
			}

			
			//System.out.println("index="+index);
		}
		input_number++;
		//System.out.println("matrix_length="+matrix_length);
	}
	public static boolean check_same(int a,int b)
	{
		boolean same=false;
		for(int i=0;i<index;i++)
		{
			if(input_x_train_int[a][i]==input_x_train_int[b][i])
			{
				same=true;
				//System.out.println("i:"+i);
			}else
			{
				same=false;
				break;
			}
			
		}
		//System.out.println("same:"+same);
		//System.out.println("b:"+input_x_train_int[b]);
		return same;
	}
	public static void print_char (int[][] want_to_print)
	{
		print_out[0]="";
		print_out[1]="";
		print_out[2]="";
		print_out2="";
		for(int i=0;i<input_number;i++)
		{

			for(int j=0;j<p;j++)
			{
				if(want_to_print[i][j]>0)
				{
					print_out[i/5]= print_out[i/5]+want_to_print[i][j];
					//System.out.print( want_to_print[i][j]);
				}else
				{
					print_out[i/5]= print_out[i/5]+" ";
					//System.out.print(" ");
				}
				
				if((j+1)%matrix_length==0)
				{
					//System.out.println();
					print_out[i/5]= print_out[i/5]+"\n";
				}				
			}
			print_out[i/5]= print_out[i/5]+"\n";
			//System.out.println();
			
		}
		
	}
}
