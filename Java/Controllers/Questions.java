//generated automatically
package com.example.biabe.DatabaseFunctionsGenerator;
import com.example.biabe.DatabaseFunctionsGenerator.Models.*;
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
interface QuestionService
{
	@GET("Questions.php?cmd=getQuestions")
	Call<List<Question>> getQuestions();
	
	@GET("Questions.php?cmd=getQuestionsByQuestionId")
	Call<List<Question>> getQuestionsByQuestionId(@Query("questionId")Integer  questionId);
	
	@POST("Questions.php?cmd=addQuestion")
	Call<Question> addQuestion(@Body Question question);

}

public class Questions
{
	public static List<Question> getQuestions(Call<List<Question>> call)
	{
		List<Question> questions;
		
		questions = null;
		
		try
		{
			questions = call.execute().body();
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
		return questions;
	
	}
	public static List<Question> getQuestions()
	{
		List<Question> questions;
		QuestionService service;
		Call<List<Question>> call;
		
		questions = null;
		
		service = RetrofitInstance.GetRetrofitInstance().create(QuestionService.class);
		try
		{
			call = service.getQuestions();
			questions = getQuestions(call);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
		return questions;
	
	}
	
	public static List<Question> getQuestionsByQuestionId(Integer  questionId)
	{
		List<Question> questions;
		QuestionService service;
		Call<List<Question>> call;
		
		questions = null;
		
		service = RetrofitInstance.GetRetrofitInstance().create(QuestionService.class);
		try
		{
			call = service.getQuestionsByQuestionId(questionId);
			questions = getQuestions(call);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
		return questions;
	
	}
	
	
	public static void getQuestions(Call<List<Question>> call, Callback<List<Question>> callback)
	{
		try
		{
			call.enqueue(callback);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
	
	}
	public static void getQuestions(Callback<List<Question>> callback)
	{
		List<Question> questions;
		QuestionService service;
		Call<List<Question>> call;
		
		questions = null;
		
		service = RetrofitInstance.GetRetrofitInstance().create(QuestionService.class);
		try
		{
			call = service.getQuestions();
			getQuestions(call, callback);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
	
	}
	
	public static void getQuestionsByQuestionId(Integer  questionId, Callback<List<Question>> callback)
	{
		List<Question> questions;
		QuestionService service;
		Call<List<Question>> call;
		
		questions = null;
		
		service = RetrofitInstance.GetRetrofitInstance().create(QuestionService.class);
		try
		{
			call = service.getQuestionsByQuestionId(questionId);
			getQuestions(call, callback);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
	
	}
	
	
	public static Question addQuestion(Question question)
	{
		QuestionService service;
		Call<Question> call;
		
		
		service = RetrofitInstance.GetRetrofitInstance().create(QuestionService.class);
		try
		{
			call = service.addQuestion(question);
			question = call.execute().body();
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
		return question;
	
	}
	
	public static void addQuestion(Question question, Callback<Question> callback)
	{
		QuestionService service;
		Call<Question> call;
		
		
		service = RetrofitInstance.GetRetrofitInstance().create(QuestionService.class);
		try
		{
			call = service.addQuestion(question);
			call.enqueue(callback);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
	
	}
	

}
