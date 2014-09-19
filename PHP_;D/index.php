<?php
include_once("core/dB.php");
$db= new dB();
?>

<a href="settings.php"><button name="StartNewGame" id="startNew">start</button></a>
<button name="LoadGame" id="load">load</button>
<?php
if(isset($_COOKIE['gameID'])){
    echo "<a href='game.php'><button name='ContinueGame' id='continue'>continue</button></a>";
}
?>