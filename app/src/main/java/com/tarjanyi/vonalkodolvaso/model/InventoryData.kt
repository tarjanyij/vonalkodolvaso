package com.tarjanyi.vonalkodolvaso.model

data class InventoryData(
    var id:Int,
    var eszkozAzon:Int,
    var leltariSzam:String,
    var vonalkod:String,
    var megnevezes:String,
    var gyariSzam:String,
    var mennyiseg:Int,
    var leltarKorzet:String,
    var leltarKorzetMegnevezes:String,
    var hasznalatbaVetelDatuma:String,
    var elhelyezesIdeje:String,
    var bruttoErtek:Int,
    var leltSzemely:Int,
    var leltSzemelyNeve:String,
    var megnevezes2:String
)
