<?php
include_once("core/dB.php");
include_once("core/sg_game.php");
include_once("core/sg_drink.php");
include_once("core/sg_player.php");

$game = new sg_game();

$countDrinks = $_POST['drinkCount'];
for ($i = 1; $i <= $countDrinks; $i++) {
    $drink = $_POST["Drink" . $i];
    $oDrink = new sg_drink();
    $oDrink->setAttr("sName", $drink);
    $game->addDrink($oDrink);
}
$countPlayers = $_POST['playerCount'];
for ($j = 1; $j <= $countPlayers; $j++) {
    $player = $_POST["Player" . $j];
    $oPlayer = new sg_player();
    $oPlayer->setAttr("sName", $player);
    $game->addPlayer($oPlayer);
}
$game->save();
http_redirect("game.php", array("name" => "value"), true, HTTP_REDIRECT_PERM);