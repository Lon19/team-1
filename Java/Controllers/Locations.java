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
interface LocationService
{
	@GET("Locations.php?cmd=getLocations")
	Call<List<Location>> getLocations();
	
	@GET("Locations.php?cmd=getLocationsByLocationId")
	Call<List<Location>> getLocationsByLocationId(@Query("locationId")Integer  locationId);
	
	@POST("Locations.php?cmd=addLocation")
	Call<Location> addLocation(@Body Location location);

}

public class Locations
{
	public static List<Location> getLocations(Call<List<Location>> call)
	{
		List<Location> locations;
		
		locations = null;
		
		try
		{
			locations = call.execute().body();
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
		return locations;
	
	}
	public static List<Location> getLocations()
	{
		List<Location> locations;
		LocationService service;
		Call<List<Location>> call;
		
		locations = null;
		
		service = RetrofitInstance.GetRetrofitInstance().create(LocationService.class);
		try
		{
			call = service.getLocations();
			locations = getLocations(call);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
		return locations;
	
	}
	
	public static List<Location> getLocationsByLocationId(Integer  locationId)
	{
		List<Location> locations;
		LocationService service;
		Call<List<Location>> call;
		
		locations = null;
		
		service = RetrofitInstance.GetRetrofitInstance().create(LocationService.class);
		try
		{
			call = service.getLocationsByLocationId(locationId);
			locations = getLocations(call);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
		return locations;
	
	}
	
	
	public static void getLocations(Call<List<Location>> call, Callback<List<Location>> callback)
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
	public static void getLocations(Callback<List<Location>> callback)
	{
		List<Location> locations;
		LocationService service;
		Call<List<Location>> call;
		
		locations = null;
		
		service = RetrofitInstance.GetRetrofitInstance().create(LocationService.class);
		try
		{
			call = service.getLocations();
			getLocations(call, callback);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
	
	}
	
	public static void getLocationsByLocationId(Integer  locationId, Callback<List<Location>> callback)
	{
		List<Location> locations;
		LocationService service;
		Call<List<Location>> call;
		
		locations = null;
		
		service = RetrofitInstance.GetRetrofitInstance().create(LocationService.class);
		try
		{
			call = service.getLocationsByLocationId(locationId);
			getLocations(call, callback);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
	
	}
	
	
	public static Location addLocation(Location location)
	{
		LocationService service;
		Call<Location> call;
		
		
		service = RetrofitInstance.GetRetrofitInstance().create(LocationService.class);
		try
		{
			call = service.addLocation(location);
			location = call.execute().body();
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
		
		return location;
	
	}
	
	public static void addLocation(Location location, Callback<Location> callback)
	{
		LocationService service;
		Call<Location> call;
		
		
		service = RetrofitInstance.GetRetrofitInstance().create(LocationService.class);
		try
		{
			call = service.addLocation(location);
			call.enqueue(callback);
		}
		catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
	
	}
	

}
