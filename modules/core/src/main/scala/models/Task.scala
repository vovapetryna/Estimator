package models

case class Task(id: Long, name: String, description: String, startTime: java.time.LocalDateTime, endTime: java.time.LocalDateTime)
