//generated automatically
package com.example.biabe.DatabaseFunctionsGenerator;
import com.example.biabe.DatabaseFunctionsGenerator.Models.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
public class QuestionAdapter extends BaseAdapter
{
	List<Question> questions;
	Context context;
	
	@Override
	public int getCount()
	{
		return questions.size();
	
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Question question;
		TextView questionIdTextBox;
		TextView contentTextBox;
		TextView firstVarientTextBox;
		TextView secondVarientTextBox;
		TextView correctVarientTextBox;
		TextView creationTimeTextBox;
		
		question = getItem(position);
		
		if(null == convertView)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.question_view, parent, false);
		}
		
		questionIdTextBox = (TextView) convertView.findViewById(R.id.questionIdTextBox);
		contentTextBox = (TextView) convertView.findViewById(R.id.contentTextBox);
		firstVarientTextBox = (TextView) convertView.findViewById(R.id.firstVarientTextBox);
		secondVarientTextBox = (TextView) convertView.findViewById(R.id.secondVarientTextBox);
		correctVarientTextBox = (TextView) convertView.findViewById(R.id.correctVarientTextBox);
		creationTimeTextBox = (TextView) convertView.findViewById(R.id.creationTimeTextBox);
		
		questionIdTextBox.setText(question.getQuestionId().toString());
		contentTextBox.setText(question.getContent());
		firstVarientTextBox.setText(question.getFirstVarient());
		secondVarientTextBox.setText(question.getSecondVarient());
		correctVarientTextBox.setText(question.getCorrectVarient().toString());
		creationTimeTextBox.setText(question.getCreationTime().toString());
		
		return convertView;
	
	}
	
	@Override
	public Question getItem(int position)
	{
		return questions.get(position);
	
	}
	
	@Override
	public long getItemId(int position)
	{
		return questions.get(position).getQuestionId();
	
	}
	
	public QuestionAdapter(List<Question> questions, Context context)
	{
		this.questions = questions;
		this.context = context;
	
	}
	

}
