<?php
//generated automatically
class Location
{
	var $locationId;
	var $content;
	var $firstVarient;
	var $secondVarient;
	var $correctVarient;
	var $x;
	var $y;
	var $creationTime;

	function GetLocationId()
	{
		return $this->locationId;
	}
	function SetLocationId($value)
	{
		$this->locationId = $value;
	}
	
	function GetContent()
	{
		return $this->content;
	}
	function SetContent($value)
	{
		$this->content = $value;
	}
	
	function GetFirstVarient()
	{
		return $this->firstVarient;
	}
	function SetFirstVarient($value)
	{
		$this->firstVarient = $value;
	}
	
	function GetSecondVarient()
	{
		return $this->secondVarient;
	}
	function SetSecondVarient($value)
	{
		$this->secondVarient = $value;
	}
	
	function GetCorrectVarient()
	{
		return $this->correctVarient;
	}
	function SetCorrectVarient($value)
	{
		$this->correctVarient = $value;
	}
	
	function GetX()
	{
		return $this->x;
	}
	function SetX($value)
	{
		$this->x = $value;
	}
	
	function GetY()
	{
		return $this->y;
	}
	function SetY($value)
	{
		$this->y = $value;
	}
	
	function GetCreationTime()
	{
		return $this->creationTime;
	}
	function SetCreationTime($value)
	{
		$this->creationTime = $value;
	}
	

	function Location($Content, $FirstVarient, $SecondVarient, $CorrectVarient, $X, $Y)
	{
		$this->locationId = 0;
	
		$this->content = $Content;
		$this->firstVarient = $FirstVarient;
		$this->secondVarient = $SecondVarient;
		$this->correctVarient = $CorrectVarient;
		$this->x = $X;
		$this->y = $Y;
	}

}
?>
