<?php


class sg_game {
    public $playerList=array();
    public $drinks=array();
    public $gameID;
    public $gameState;
    public $saveKey;

    function __construct(){
      $this->gameID = uniqid();
    }
    public function addPlayer($player){
        $this->playerList[]=$player;
    }
    public function addDrink($drink){
       $this->drinks[]=$drink;
    }
    public function load($gameid){
      $oDB=new dB();
      $sSql="Select * from games where id='".$gameid."'";
      $data=$oDB->getAll($sSql);
      $this->gameID=$data[0]['id'];
      $this->gameState=$data[0]['game_state'];
      $this->saveKey=$data[0]['save_key'];
      $this->loadDrinks();
      $this->loadPlayers();

    }
    public function loadPlayers(){
        $oDB=new dB();
        $sSql="Select id from user where gameid='".$this->gameID."'";
        $data=$oDB->getAll($sSql);
        foreach($data as $playerData){
            $oPlayer=new sg_player();
            $oPlayer->load($playerData['id']);
            $this->addPlayer($oPlayer);
        }
    }
    public function loadDrinks(){
        $oDB=new dB();
        $sSql="Select id from drinks where gameid='".$this->gameID."'";
        $data=$oDB->getAll($sSql);
        foreach($data as $drinksData){
            $oDrink=new sg_drink();
            $oDrink->load($drinksData['id']);
            $this->addDrink($oDrink);
        }
    }
    public function save(){
        setcookie("gameID",$this->gameID,time()+60*60*24*30 );
        $oDB= new dB();
        $sSql = "INSERT INTO games ( id ,game_state,save_key) VALUES ('".$this->gameID."','WAITING','0') ";
        $oDB->execute($sSql);
        foreach($this->drinks as $oDrink){
            $oDrink->save($this->gameID);
        }
        foreach($this->playerList as $oPLayer){
            $oPLayer->save($this->gameID);
        }
    }


}
