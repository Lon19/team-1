//generated automatically
package com.example.biabe.DatabaseFunctionsGenerator.Models;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
import java.util.Date;

public class Question
{
	private Integer  questionId;
	private String content;
	private String firstVarient;
	private String secondVarient;
	private Integer  correctVarient;
	private Date creationTime;
	
	public Integer  getQuestionId()
	{
		return this.questionId;
	}
	
	public void setQuestionId(Integer  questionId)
	{
		this.questionId = questionId;
	}
	
	public String getContent()
	{
		return this.content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getFirstVarient()
	{
		return this.firstVarient;
	}
	
	public void setFirstVarient(String firstVarient)
	{
		this.firstVarient = firstVarient;
	}
	
	public String getSecondVarient()
	{
		return this.secondVarient;
	}
	
	public void setSecondVarient(String secondVarient)
	{
		this.secondVarient = secondVarient;
	}
	
	public Integer  getCorrectVarient()
	{
		return this.correctVarient;
	}
	
	public void setCorrectVarient(Integer  correctVarient)
	{
		this.correctVarient = correctVarient;
	}
	
	public Date getCreationTime()
	{
		return this.creationTime;
	}
	
	public void setCreationTime(Date creationTime)
	{
		this.creationTime = creationTime;
	}
	
	
	public Question(String content, String firstVarient, String secondVarient, Integer  correctVarient)
	{
		this.content = content;
		this.firstVarient = firstVarient;
		this.secondVarient = secondVarient;
		this.correctVarient = correctVarient;
	}
	
	public Question()
	{
		this(
			"Test", //Content
			"Test", //FirstVarient
			"Test", //SecondVarient
			0 //CorrectVarient
		);
		this.questionId = 0;
		this.creationTime = new Date(0);
	
	}
	

}
