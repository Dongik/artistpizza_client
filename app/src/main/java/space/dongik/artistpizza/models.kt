package space.dongik.artistpizza

data class AttendRequest(val member_id: String, val date: Int)

data class Member(val _id: String, val name: String, val phone: String)

data class Attend(val course_name: String, val check: Boolean)

data class Rollbook(val _id: String, val check: Map<String, Attend>)

data class AttendResult(val message: String)
//data class Rollbook(val name: String, val weekday: Int, )