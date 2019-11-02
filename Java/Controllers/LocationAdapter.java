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
public class LocationAdapter extends BaseAdapter
{
	List<Location> locations;
	Context context;
	
	@Override
	public int getCount()
	{
		return locations.size();
	
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Location location;
		TextView locationIdTextBox;
		TextView xTextBox;
		TextView yTextBox;
		TextView creationTimeTextBox;
		
		location = getItem(position);
		
		if(null == convertView)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.location_view, parent, false);
		}
		
		locationIdTextBox = (TextView) convertView.findViewById(R.id.locationIdTextBox);
		xTextBox = (TextView) convertView.findViewById(R.id.xTextBox);
		yTextBox = (TextView) convertView.findViewById(R.id.yTextBox);
		creationTimeTextBox = (TextView) convertView.findViewById(R.id.creationTimeTextBox);
		
		locationIdTextBox.setText(location.getLocationId().toString());
		xTextBox.setText(location.getX().toString());
		yTextBox.setText(location.getY().toString());
		creationTimeTextBox.setText(location.getCreationTime().toString());
		
		return convertView;
	
	}
	
	@Override
	public Location getItem(int position)
	{
		return locations.get(position);
	
	}
	
	@Override
	public long getItemId(int position)
	{
		return locations.get(position).getLocationId();
	
	}
	
	public LocationAdapter(List<Location> locations, Context context)
	{
		this.locations = locations;
		this.context = context;
	
	}
	

}
