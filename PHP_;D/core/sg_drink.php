<?php
/**
 * Created by IntelliJ IDEA.
 * User: mbi
 * Date: 17.09.14
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */ 
class sg_drink {
    public $iAlcohol;
    public $sName;
    public $sAmount;
    public $iDrinkID;
    public $gameID=null;

    function __construct(){
        $this->iDrinkID = uniqid();
    }
    public function setAttr($AttrName, $AttrValue){
     $this->$AttrName=$AttrValue;
    }
    public function load($iDrinkID)
    {
        $dB = new dB();
        $sSql = "Select * from drinks where id='" . $iDrinkID . "'";
        $data=$dB->getAll($sSql);
        $this->iDrinkID=$data[0]['id'];
        $this->gameID=$data[0]['gameid'];
        $this->sName=$data[0]['name'];
        $this->iAlcohol=$data[0]['alcohol'];
        $this->sAmount=$data[0]['size'];
    }

    public function save($gameID){
        $oDB= new dB();
        if($this->gameID!=null){
            $sSql= "UPDATE drinks SET name=".$this->sName.", alcohol=".$this->iAlcohol.",size=".$this->sAmount." WHERE id=".$this->iDrinkID;
        }else{
        $this->gameID=$gameID;
            $sSql = "INSERT INTO drinks (id,gameid, name ,alcohol,size) VALUES ('".$this->iDrinkID."','".$gameID."','". $this->sName."','20' ,'Liter') ";
        }
            $oDB->execute($sSql);
    }

    public function delete(){

    }


}
