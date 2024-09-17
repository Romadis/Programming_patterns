class Student{
    companion object{
        var classId:Int = 0;
        fun autoGenerateId():Int{
            classId+=1;
            return classId;
        }
    }

    var id:Int?
        get():Int?{
            return field;
        }
        set(value:Int?) {
            field = value;
        };

    var surname:String
        get():String{
            return field;
        }
        set(value:String) {
            field = value;
        };

    var name:String
        get():String{
            return field;
        }
        set(value:String) {
            field = value;
        };

    var patronymic:String
        get():String{
            return field;
        }
        set(value:String) {
            field = value;
        };

    var phoneNumber:String?
        get():String?{
            return field;
        }
        set(value:String?) {
            field = value;
        };

    var telegram:String?
        get():String?{
            return field;
        }
        set(value:String?) {
            field = value;
        };

    var email:String?
        get():String?{
            return field;
        }
        set(value:String?) {
            field = value;
        };

    var gitHub:String?
        get():String?{
            return field;
        }
        set(value:String?) {
            field = value;
        };

    constructor(
        studentSurname:String,
        studentName:String,
        studentPatronymic:String,
        studentPhone:String?=null,
        studentTelegram:String?=null,
        studentEmail:String?=null,
        studentGit:String?=null
    )
    {
        this.id = autoGenerateId();
        this.surname = studentSurname;
        this.name = studentName;
        this.patronymic = studentPatronymic;
        this.phoneNumber = studentPhone;
        this.telegram = studentTelegram;
        this.email = studentEmail;
        this.gitHub = studentGit;
    }

}