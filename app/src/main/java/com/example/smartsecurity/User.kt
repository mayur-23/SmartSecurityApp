package com.example.smartsecurity

class User(){
   var userId=""
    var name=""
    var password=""
    var mobileno=0
    var emailId=""
    var date=""
    constructor(userId:String,name:String,emailId:String,password:String,mobileno:Int,date:String) : this() {
        this.userId=userId
        this.name=name
        this.emailId=emailId
        this.password=password
        this.mobileno=mobileno
        this.date=date
    }
}
