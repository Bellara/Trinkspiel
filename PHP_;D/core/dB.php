<?php
/**
 * Created by IntelliJ IDEA.
 * User: mbi
 * Date: 17.09.14
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */ 
class dB {
    public $username = "sguser";
    public $password = "sg2014";
    public $server = "localhost";
    public $database = "sg2014";
    public $dbConn;


    function __construct(){
        $this->dbConn = mysql_connect($this->server,$this->username,$this->password) or die('Could not connect to mysql server.' );
        $db_selected = mysql_select_db($this->database, $this->dbConn);
        if (!$db_selected) {
            die ('Kann foo nicht benutzen : ' . mysql_error());
        }

    }

    public function execute($sSql){
        $result = mysql_query($sSql,$this->dbConn);
        return $result;
    }
    public function getAll($sSql){
        $aResultArray=array();
        $result = mysql_query($sSql,$this->dbConn);
        if(!$result) {
            die("Database query failed: " . mysql_error());
        }
        $i=0;
        while ( $row = mysql_fetch_array( $result ,MYSQL_ASSOC) )
        {
            foreach($row as $key => $value){
                $aResultArray[$i][$key] = $value;
            }
            $i++;
        }
        return $aResultArray;
    }
    public function getOne($sSql){
        $sSql.=" limit 1";
        $result = mysql_query($sSql,$this->dbConn);
        $result = mysql_fetch_array($result,MYSQL_NUM);
        if(!is_array($result[0]) && count($result) ==1){
            return $result[0];
        }
        return false;

    }
}
