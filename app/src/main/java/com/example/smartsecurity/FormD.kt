package com.example.smartsecurity

class FormD() {
    var userId=""
    var name=""
    var vehicalno=""
    var emailId=""
    var mobileno=0
    var reason=""

    var date=""
    constructor(userId:String,name:String,emailId:String,vehicalno:String,mobileno:Int,reason:String,date:String) : this() {
        this.userId=userId
        this.name=name
        this.emailId=emailId
        this.vehicalno=vehicalno
        this.mobileno=mobileno
        this.reason=reason
        this.date=date
    }
}