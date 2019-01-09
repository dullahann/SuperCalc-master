package com.rootlu.calculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity_result extends Activity {

	private TextView tv_paytotalshow;
	private TextView tv_interestshow;
	private TextView tv_monthshow;
	private TextView tv_loantotalshow;
	private TextView tv_monthnumshow;
	private Button btn_end;

	
	String loantype;
	String paytype;
	String year;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_result);
		
		tv_paytotalshow=(TextView)findViewById(R.id.tv_paytotalshow);
		tv_interestshow=(TextView)findViewById(R.id.tv_interestshow);
		tv_monthshow=(TextView)findViewById(R.id.tv_monthshow);
		tv_loantotalshow=(TextView)findViewById(R.id.tv_loantotalshow);
		tv_monthnumshow=(TextView)findViewById(R.id.tv_monthnumshow);
		btn_end=(Button)findViewById(R.id.btn_end);

		// ȡ�ô� Activity03 ���д��ݹ����� Intent ����   
        Intent intent = getIntent(); 
        // �� Intent ���и��� key ȡ�� value 
        
        //���ʽ
        String paytype=intent.getStringExtra("paytype_key");
        int paytype_id=Integer.valueOf(paytype).intValue();

		//�����ܶ�
		String busloan= intent.getStringExtra("busloan_key");
		double busloan_double=Double.valueOf(busloan).doubleValue();	
		String publoan=intent.getStringExtra("publoan_key");
		double publoan_double=Double.valueOf(publoan).doubleValue();
		double loantotal_double=busloan_double+publoan_double;
//		tv_loantotalshow.setText(loantotal_double+"");
        //��������
        String year=intent.getStringExtra("year_key");
        int year_id=Integer.valueOf(year).intValue();

		//������
        String busratemonth=intent.getStringExtra("busrate_key");
		double busratemonth_double=Double.valueOf(busratemonth).doubleValue();
        String pubratemonth=intent.getStringExtra("pubrate_key");
		double pubratemonth_double=Double.valueOf(pubratemonth).doubleValue();	
//		tv_interestshow.setText(busratemonth+"@"+pubratemonth);
		//�����ܶ�
		double totalpay=0;
		double buspay=0;
		double pubpay=0;
		
		//�»����ܶ�
		double monthpay=0;
		double busmonthpay=0;
		double pubmonthpay=0;

		//�ȶϢ
		if(paytype_id==0)
		{
			//�����ܶ��������
			tv_loantotalshow.setText(loantotal_double+"");
			tv_monthnumshow.setText(Integer.toString((12*(year_id+1))));
			//��ҵ����
			if(busloan_double!=0&&publoan_double==0)
			{
				busmonthpay=(busloan_double*busratemonth_double/100*Math.pow((1+busratemonth_double/100), (12*(year_id+1))))
						 /(Math.pow((1+busratemonth_double/100), (12*(year_id+1)))-1);
				pubmonthpay=0;
			}
			//���������
			if(busloan_double==0&&publoan_double!=0)
			{
				busmonthpay=0;  
				pubmonthpay=(publoan_double*pubratemonth_double/100*Math.pow((1+pubratemonth_double/100), (12*(year_id+1))))
						 /(Math.pow((1+pubratemonth_double/100), (12*(year_id+1)))-1);
			}
			//�����
			if(busloan_double!=0&&publoan_double!=0)
			{
				busmonthpay=(busloan_double*busratemonth_double/100*Math.pow((1+busratemonth_double/100), (12*(year_id+1))))
						 /(Math.pow((1+busratemonth_double/100), (12*(year_id+1)))-1);
				pubmonthpay=(publoan_double*pubratemonth_double/100*Math.pow((1+pubratemonth_double/100), (12*(year_id+1))))
						 /(Math.pow((1+pubratemonth_double/100), (12*(year_id+1)))-1);
			}
			monthpay=busmonthpay+pubmonthpay;
			//�趨�����ʽ������С�������λ
			DecimalFormat df = new DecimalFormat("#0.0000");
			tv_monthshow.setText(df.format(monthpay));
			tv_paytotalshow.setText(df.format(monthpay*12*(year_id+1)));
			tv_interestshow.setText(df.format(monthpay*12*(year_id+1)-loantotal_double));
		}
		//�ȶ��
		else if(paytype_id==1)
		{
			//�����ܶ��������
			tv_loantotalshow.setText(loantotal_double+"");
			tv_monthnumshow.setText(Integer.toString((12*(year_id+1))));
			//��ҵ����
			if(busloan_double!=0&&publoan_double==0)
			{ 
				//�趨�����ʽ������С�������λ
				DecimalFormat df = new DecimalFormat("#.0000");
				buspay=(12*(year_id+1)+1)*busloan_double*busratemonth_double/200+busloan_double;
				pubpay=0;
				
				//�»���
				double[] arr_monthpay=new double[6];
				arr_monthpay[0]=(busloan_double/(12*(year_id+1)))+(busloan_double-0)*busratemonth_double/100;
				tv_monthshow.setText("��1�£�"+df.format(arr_monthpay[0])+"\n");
				 
				for(int i=1;i<6;i++)
				{
				 arr_monthpay[i]=(busloan_double/(12*(year_id+1)))+busratemonth_double/100*(busloan_double-((i*((12*(year_id+1))/6)-1)*(busloan_double/(12*(year_id+1)))));
				 tv_monthshow.append("��"+i*((12*(year_id+1))/6)+"�£�"+df.format(arr_monthpay[i])+"\n");
				}	 
			}
			//���������
			if(busloan_double==0&&publoan_double!=0)
			{
				//�趨�����ʽ������С�������λ
				DecimalFormat df = new DecimalFormat("#.0000");
				buspay=0;
				pubpay=(12*(year_id+1)+1)*publoan_double*pubratemonth_double/200+publoan_double;
				//�»���
				double[] arr_monthpay=new double[6];
				arr_monthpay[0]=(publoan_double/(12*(year_id+1)))+(publoan_double-0)*pubratemonth_double/100;
				tv_monthshow.setText("��1�£�"+df.format(arr_monthpay[0])+"\n");
				 
				for(int i=1;i<6;i++)
				{
					arr_monthpay[i]=(publoan_double/(12*(year_id+1)))+pubratemonth_double/100*(publoan_double-((i*((12*(year_id+1))/6)-1)*(publoan_double/(12*(year_id+1)))));
					tv_monthshow.append("��"+i*((12*(year_id+1))/6)+"�£�"+df.format(arr_monthpay[i])+"\n");
				}	 
			}
			//�����
			if(busloan_double!=0&&publoan_double!=0)
			{
				//�趨�����ʽ������С�������λ
				DecimalFormat df = new DecimalFormat("#.0000");
				buspay=(12*(year_id+1)+1)*busloan_double*busratemonth_double/200+busloan_double;
				pubpay=(12*(year_id+1)+1)*publoan_double*pubratemonth_double/200+publoan_double;
				//�»���
				double[] arr_monthpay=new double[6];
				 arr_monthpay[0]=((busloan_double/(12*(year_id+1)))+(busloan_double-0)*busratemonth_double/100)
						 +((publoan_double/(12*(year_id+1)))+(publoan_double-0)*pubratemonth_double/100);
				 tv_monthshow.setText("��1�£�"+df.format(arr_monthpay[0])+"\n");
				 
				 for(int i=1;i<6;i++)
				 {
					 arr_monthpay[i]=((busloan_double/(12*(year_id+1)))+busratemonth_double/100*(busloan_double-((i*((12*(year_id+1))/6)-1)*(busloan_double/(12*(year_id+1))))))
							 +((publoan_double/(12*(year_id+1)))+pubratemonth_double/100*(publoan_double-((i*((12*(year_id+1))/6)-1)*(publoan_double/(12*(year_id+1))))));
					 tv_monthshow.append("��"+i*((12*(year_id+1))/6)+"�£�"+df.format(arr_monthpay[i])+"\n");
				 }	 
			}
			//�趨�����ʽ������С�������λ
			DecimalFormat df = new DecimalFormat("#.0000");
			//�����ܶ�
			totalpay=buspay+pubpay;
			tv_paytotalshow.setText(df.format(totalpay));
			//֧����Ϣ��
			tv_interestshow.setText(df.format(totalpay-loantotal_double));
			
		}
		
		btn_end.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//����
				tv_loantotalshow.setText("");
				tv_paytotalshow.setText("");
				tv_interestshow.setText("");
				tv_monthnumshow.setText("");
				tv_monthshow.setText("");
				//������д��Ϣ����
				Intent intent=new Intent();
				// ���� Intent ����Ҫ������ Activity 
            	intent.setClass(Activity_result.this, Activity_loan.class); 
              	// ͨ�� Intent ������������һ�� Activity 
            	startActivity(intent);
			}
			
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}

}
