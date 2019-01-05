package space.dongik.artistpizza

data class AttendRequest(val member_id: String)

data class AttendRequestResult(val message: String)

data class AskResult(val message: String, val is_attendable:Boolean)

data class Member(val _id: String,
                  val name: String,
                  val phone: String,
                  val count: Int,
                  val credit: Int,
                  val expire_date: String,
                  val course: String)

//data class Attend(val course_name: String, val check: Boolean)

//data class Rollbook(val _id: String, val check: Map<String, Attend>)

//data class Rollbook(val name: String, val weekday: Int, )