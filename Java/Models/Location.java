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

public class Location
{
	private Integer  locationId;
	private double x;
	private double y;
	private Date creationTime;
	
	public Integer  getLocationId()
	{
		return this.locationId;
	}
	
	public void setLocationId(Integer  locationId)
	{
		this.locationId = locationId;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public Date getCreationTime()
	{
		return this.creationTime;
	}
	
	public void setCreationTime(Date creationTime)
	{
		this.creationTime = creationTime;
	}
	
	
	public Location(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Location()
	{
		this(
			0, //X
			0 //Y
		);
		this.locationId = 0;
		this.creationTime = new Date(0);
	
	}
	

}
