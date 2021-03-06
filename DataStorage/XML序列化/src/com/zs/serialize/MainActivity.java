package com.zs.serialize;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private List<PersonInfo> userData; //保存数据的集合
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//创建保存数据的集合，模拟假数据
		userData = new ArrayList<PersonInfo>();
		for(int i =0;i<3;i++){
			userData.add(new PersonInfo("王"+i,100-i, 80-i));
		}
	}
	//将Person对象保存为xml格式
	public void Serializer(View view) {
		try {
			XmlSerializer serializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(),"person.xml");
			FileOutputStream os = new FileOutputStream(file);
			serializer.setOutput(os,"UTF-8");
			serializer.startDocument("UTF-8", true);
			serializer.startTag(null, "persons");
			int count =0;
			for(PersonInfo person:userData){
				serializer.startTag(null, "person");
				serializer.attribute(null, "id", count+"");
				//将Person对象的name属性写入xml文件
				serializer.startTag(null, "name");
				serializer.text(person.getName());
				serializer.endTag(null, "name");
				//将Person对象的age属性写入xml文件
				serializer.startTag(null, "age");
				serializer.text(String.valueOf(person.getAge()));
				serializer.endTag(null, "age");
				//将Person对象的score对象写入xml文件
				serializer.startTag(null,"score");
				serializer.text(String.valueOf(person.getScore()));
				serializer.endTag(null, "score");
				serializer.endTag(null, "person");
				count++;
			}
			serializer.endTag(null, "persons");
			serializer.endDocument();
			serializer.flush();
			os.close();
			Toast.makeText(this, "操作成功！", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "操作失败！", 0).show();
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
