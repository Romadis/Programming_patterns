class StudentShort {
    val id:Int;
    val surnameInitials:String;
    val gitHub:String?;
    val contact:String?;

    constructor(studentLong:Student){
        this.id = studentLong.id as Int;
        this.surnameInitials = studentLong.getInitials()
        this.gitHub = studentLong.gitHub
        this.contact = studentLong.getOneContact()?.values?.first()
    }
    // Конструктор по строке
    private fun getSurnameInitials(stringInfo:String) = stringInfo.split(",".toRegex())[0].split(":")[1]
    private fun getGit(stringInfo:String) = stringInfo.split(",".toRegex())[1].split(":")[1]
    private fun getContact(stringInfo: String) = stringInfo.split(",".toRegex())[2].split(":")[2]
    constructor(studentId:Int,stringInfo:String){
        this.id = studentId
        this.surnameInitials = this.getSurnameInitials(stringInfo)
        this.gitHub = this.getGit(stringInfo)
        this.contact = this.getContact(stringInfo)
    }
    override fun toString(): String {
        return "StudentShort(${this.id},${this.surnameInitials},${this.gitHub},${this.contact})"
    }
}