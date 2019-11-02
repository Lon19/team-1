<?php
header('Access-Control-Allow-Origin: *'); 
header('Access-Control-Allow-Headers: *'); 
header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS');
$_POST = json_decode(file_get_contents('php://input'), true);
require_once 'Models/Location.php';
require_once 'DatabaseOperations.php';
require_once 'Helpers.php';
function ConvertListToLocations($data)
{
	$locations = [];
	
	foreach($data as $row)
	{
		$location = new Location(
		$row["Content"], 
		$row["FirstVarient"], 
		$row["SecondVarient"], 
		$row["CorrectVarient"], 
		$row["X"], 
		$row["Y"] 
		);
	
		$location->SetLocationId($row["LocationId"]);
		$location->SetCreationTime($row["CreationTime"]);
	
		$locations[count($locations)] = $location;
	}
	
	return $locations;
}

function GetLocations($database)
{
	$data = $database->ReadData("SELECT * FROM Locations");
	$locations = ConvertListToLocations($data);
	return $locations;
}

function GetLocationsByLocationId($database, $locationId)
{
	$data = $database->ReadData("SELECT * FROM Locations WHERE LocationId = $locationId");
	$locations = ConvertListToLocations($data);
	if(0== count($locations))
	{
		return [GetEmptyLocation()];
	}
	return $locations;
}

function CompleteLocations($database, $locations)
{
	$locationsList = GetLocations($database);
	foreach($locations as $location)
	{
		$start = 0;
		$end = count($locationsList) - 1;
		do
		{
	
			$mid = floor(($start + $end) / 2);
			if($location->GetLocationId() > $locationsList[$mid]->GetLocationId())
			{
				$start = $mid + 1;
			}
			else if($location->GetLocationId() < $locationsList[$mid]->GetLocationId())
			{
				$end = $mid - 1;
			}
			else if($location->GetLocationId() == $locationsList[$mid]->GetLocationId())
			{
				$start = $mid + 1;
				$end = $mid - 1;
				$location->SetLocation($locationsList[$mid]);
			}
	
		}while($start <= $end);
	}
	
	return $locations;
}

function AddLocation($database, $location)
{
	$query = "INSERT INTO Locations(Content, FirstVarient, SecondVarient, CorrectVarient, X, Y, CreationTime) VALUES(";
	$query = $query . "'" . mysqli_real_escape_string($database->connection ,$location->GetContent()) . "', ";
	$query = $query . "'" . mysqli_real_escape_string($database->connection ,$location->GetFirstVarient()) . "', ";
	$query = $query . "'" . mysqli_real_escape_string($database->connection ,$location->GetSecondVarient()) . "', ";
	$query = $query . mysqli_real_escape_string($database->connection ,$location->GetCorrectVarient()).", ";
	$query = $query . "'" . mysqli_real_escape_string($database->connection ,$location->GetX()) . "', ";
	$query = $query . "'" . mysqli_real_escape_string($database->connection ,$location->GetY()) . "', ";
	$query = $query . "NOW()"."";
	
	$query = $query . ");";
	$database->ExecuteSqlWithoutWarning($query);
	$id = $database->GetLastInsertedId();
	$location->SetLocationId($id);
	$location->SetCreationTime(date('Y-m-d H:i:s'));
	return $location;
	
}

function DeleteLocation($database, $locationId)
{
	$location = GetLocationsByLocationId($database, $locationId)[0];
	
	$query = "DELETE FROM Locations WHERE LocationId=$locationId";
	
	$result = $database->ExecuteSqlWithoutWarning($query);
	
	if(0 != $result)
	{
		$location->SetLocationId(0);
	}
	
	return $location;
	
}

function UpdateLocation($database, $location)
{
	$query = "UPDATE Locations SET ";
	$query = $query . "Content='" . $location->GetContent() . "', ";
	$query = $query . "FirstVarient='" . $location->GetFirstVarient() . "', ";
	$query = $query . "SecondVarient='" . $location->GetSecondVarient() . "', ";
	$query = $query . "CorrectVarient=" . $location->GetCorrectVarient().", ";
	$query = $query . "X='" . $location->GetX() . "', ";
	$query = $query . "Y='" . $location->GetY() . "'";
	$query = $query . " WHERE LocationId=" . $location->GetLocationId();
	
	$result = $database->ExecuteSqlWithoutWarning($query);
	if(0 == $result)
	{
		$location->SetLocationId(0);
	}
	return $location;
	
}

function TestAddLocation($database)
{
	$location = new Location(
		'Test',//Content
		'Test',//FirstVarient
		'Test',//SecondVarient
		0,//CorrectVarient
		0,//X
		0//Y
	);
	
	AddLocation($database, $location);
}

function GetEmptyLocation()
{
	$location = new Location(
		'',//Content
		'',//FirstVarient
		'',//SecondVarient
		0,//CorrectVarient
		0,//X
		0//Y
	);
	
	return $location;
}

if(CheckGetParameters(["cmd"]))
{
	if("getLocations" == $_GET["cmd"])
	{
		$database = new DatabaseOperations();
			echo json_encode(GetLocations($database));
	}

	if("getLastLocation" == $_GET["cmd"])
	{
		$database = new DatabaseOperations();
			echo json_encode(GetLastLocation($database));
	}

	else if("getLocationsByLocationId" == $_GET["cmd"])
	{
		if(CheckGetParameters([
			'locationId'
			]))
		{
			$database = new DatabaseOperations();
			echo json_encode(GetLocationsByLocationId($database, 
				$_GET["locationId"]
			));
		}
	
	}

	else if("addLocation" == $_GET["cmd"])
	{
		if(CheckGetParameters([
			'content',
			'firstVarient',
			'secondVarient',
			'correctVarient',
			'x',
			'y'
		]))
		{
			$database = new DatabaseOperations();
			$location = new Location(
				$_GET['content'],
				$_GET['firstVarient'],
				$_GET['secondVarient'],
				$_GET['correctVarient'],
				$_GET['x'],
				$_GET['y']
			);
		
			echo json_encode(AddLocation($database, $location));
		}
	
	}

}

if(CheckGetParameters(["cmd"]))
{
	if("addLocation" == $_GET["cmd"])
	{
		if(CheckPostParameters([
			'content',
			'firstVarient',
			'secondVarient',
			'correctVarient',
			'x',
			'y'
		]))
		{
			$database = new DatabaseOperations();
			$location = new Location(
				$_POST['content'],
				$_POST['firstVarient'],
				$_POST['secondVarient'],
				$_POST['correctVarient'],
				$_POST['x'],
				$_POST['y']
			);
	
			echo json_encode(AddLocation($database, $location));
		}

	}
}

if(CheckGetParameters(["cmd"]))
{
	if("updateLocation" == $_GET["cmd"])
	{
		$database = new DatabaseOperations();
		$location = new Location(
			$_POST['content'],
			$_POST['firstVarient'],
			$_POST['secondVarient'],
			$_POST['correctVarient'],
			$_POST['x'],
			$_POST['y']
		);
		$location->SetLocationId($_POST['locationId']);
		$location->SetCreationTime($_POST['creationTime']);
		
		$location = UpdateLocation($database, $location);
		echo json_encode($location);

	}
}

if("DELETE" == $_SERVER['REQUEST_METHOD']
	&& CheckGetParameters(["cmd"]))
{
	if("deleteLocation" == $_GET["cmd"])
	{
		$database = new DatabaseOperations();
		$locationId = $_GET['locationId'];
		
		$location = DeleteLocation($database, $locationId);
		echo json_encode($location);

	}
}


function GetLastLocation($database)
{
	$data = $database->ReadData("SELECT * FROM Locations ORDER BY CreationTime DESC LIMIT 1");
	$locations = ConvertListToLocations($data);
	return $locations;
}

?>
