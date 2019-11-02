<?php
header('Access-Control-Allow-Origin: *'); 
header('Access-Control-Allow-Headers: *'); 
header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS');
$_POST = json_decode(file_get_contents('php://input'), true);
require_once 'Models/Question.php';
require_once 'DatabaseOperations.php';
require_once 'Helpers.php';
function ConvertListToQuestions($data)
{
	$questions = [];
	
	foreach($data as $row)
	{
		$question = new Question(
		);
	
		$question->SetQuestionId($row["QuestionId"]);
		$question->SetCreationTime($row["CreationTime"]);
	
		$questions[count($questions)] = $question;
	}
	
	return $questions;
}

function GetQuestions($database)
{
	$data = $database->ReadData("SELECT * FROM Questions");
	$questions = ConvertListToQuestions($data);
	return $questions;
}

function GetQuestionsByQuestionId($database, $questionId)
{
	$data = $database->ReadData("SELECT * FROM Questions WHERE QuestionId = $questionId");
	$questions = ConvertListToQuestions($data);
	if(0== count($questions))
	{
		return [GetEmptyQuestion()];
	}
	return $questions;
}

function CompleteQuestions($database, $questions)
{
	$questionsList = GetQuestions($database);
	foreach($questions as $question)
	{
		$start = 0;
		$end = count($questionsList) - 1;
		do
		{
	
			$mid = floor(($start + $end) / 2);
			if($question->GetQuestionId() > $questionsList[$mid]->GetQuestionId())
			{
				$start = $mid + 1;
			}
			else if($question->GetQuestionId() < $questionsList[$mid]->GetQuestionId())
			{
				$end = $mid - 1;
			}
			else if($question->GetQuestionId() == $questionsList[$mid]->GetQuestionId())
			{
				$start = $mid + 1;
				$end = $mid - 1;
				$question->SetQuestion($questionsList[$mid]);
			}
	
		}while($start <= $end);
	}
	
	return $questions;
}

function AddQuestion($database, $question)
{
	$query = "INSERT INTO Questions(CreationTime) VALUES(";
	$query = $query . "NOW()"."";
	
	$query = $query . ");";
	$database->ExecuteSqlWithoutWarning($query);
	$id = $database->GetLastInsertedId();
	$question->SetQuestionId($id);
	$question->SetCreationTime(date('Y-m-d H:i:s'));
	return $question;
	
}

function DeleteQuestion($database, $questionId)
{
	$question = GetQuestionsByQuestionId($database, $questionId)[0];
	
	$query = "DELETE FROM Questions WHERE QuestionId=$questionId";
	
	$result = $database->ExecuteSqlWithoutWarning($query);
	
	if(0 != $result)
	{
		$question->SetQuestionId(0);
	}
	
	return $question;
	
}

function UpdateQuestion($database, $question)
{
	$query = "UPDATE Questions SET ";
	$query = $query . " WHERE QuestionId=" . $question->GetQuestionId();
	
	$result = $database->ExecuteSqlWithoutWarning($query);
	if(0 == $result)
	{
		$question->SetQuestionId(0);
	}
	return $question;
	
}

function TestAddQuestion($database)
{
	$question = new Question(
	);
	
	AddQuestion($database, $question);
}

function GetEmptyQuestion()
{
	$question = new Question(
	);
	
	return $question;
}

if(CheckGetParameters(["cmd"]))
{
	if("getQuestions" == $_GET["cmd"])
	{
		$database = new DatabaseOperations();
			echo json_encode(GetQuestions($database));
	}

	if("getLastQuestion" == $_GET["cmd"])
	{
		$database = new DatabaseOperations();
			echo json_encode(GetLastQuestion($database));
	}

	else if("getQuestionsByQuestionId" == $_GET["cmd"])
	{
		if(CheckGetParameters([
			'questionId'
			]))
		{
			$database = new DatabaseOperations();
			echo json_encode(GetQuestionsByQuestionId($database, 
				$_GET["questionId"]
			));
		}
	
	}

	else if("addQuestion" == $_GET["cmd"])
	{
		if(CheckGetParameters([
		]))
		{
			$database = new DatabaseOperations();
			$question = new Question(
			);
		
			echo json_encode(AddQuestion($database, $question));
		}
	
	}

}

if(CheckGetParameters(["cmd"]))
{
	if("addQuestion" == $_GET["cmd"])
	{
		if(CheckPostParameters([
		]))
		{
			$database = new DatabaseOperations();
			$question = new Question(
			);
	
			echo json_encode(AddQuestion($database, $question));
		}

	}
}

if(CheckGetParameters(["cmd"]))
{
	if("updateQuestion" == $_GET["cmd"])
	{
		$database = new DatabaseOperations();
		$question = new Question(
		);
		$question->SetQuestionId($_POST['questionId']);
		$question->SetCreationTime($_POST['creationTime']);
		
		$question = UpdateQuestion($database, $question);
		echo json_encode($question);

	}
}

if("DELETE" == $_SERVER['REQUEST_METHOD']
	&& CheckGetParameters(["cmd"]))
{
	if("deleteQuestion" == $_GET["cmd"])
	{
		$database = new DatabaseOperations();
		$questionId = $_GET['questionId'];
		
		$question = DeleteQuestion($database, $questionId);
		echo json_encode($question);

	}
}


function GetLastQuestion($database)
{
	$data = $database->ReadData("SELECT * FROM Questions ORDER BY CreationTime DESC LIMIT 1");
	$questions = ConvertListToQuestions($data);
	return $questions;
}

?>
