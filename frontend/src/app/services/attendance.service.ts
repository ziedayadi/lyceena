import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants';

const BACK_END_SERVICE_NAME = '/attendance/';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {

  constructor(private http : HttpClient) { }


  getCurrentSessionForTeacher(){
    return this.http.get(BASE_URL + BACK_END_SERVICE_NAME + "teacher/currentSession" );
  }


  saveStudentAttendanceForSessionByTeacher(saveStudentAttendance){
    return this.http.post(BASE_URL + BACK_END_SERVICE_NAME + "teacher/student/attendance", saveStudentAttendance );
  }

  getSessionAttendanceTraduction(code){
    if(code ==='NEW') return "Nouveau"
    if(code === 'SENT') return "Envoyé"
    if(code === 'SUBMITTED') return "Validé"
  }
  

  sendSession(sendSessionRequest){
    return this.http.post(BASE_URL + BACK_END_SERVICE_NAME + "teacher/session/send", sendSessionRequest );
  }

  submitSession(sendSessionRequest){
    return this.http.post(BASE_URL + BACK_END_SERVICE_NAME + "admin/session/submit", sendSessionRequest );
  }

  saveSessionText(saveSessionTextReq) {
    return this.http.post(BASE_URL + BACK_END_SERVICE_NAME + "teacher/session/text", saveSessionTextReq );
  }

  getTeacherSessions(){
    return this.http.get(BASE_URL + BACK_END_SERVICE_NAME + "teacher/sessions" );
  }

  getSessionById(sessionId){
    return this.http.get(BASE_URL + BACK_END_SERVICE_NAME + "session/" + sessionId );
  }

  getAdminSessions(){
    return this.http.get(BASE_URL + BACK_END_SERVICE_NAME + "admin/sessions" );
  }

  getStudentSessions(){
    return this.http.get(BASE_URL + BACK_END_SERVICE_NAME + "student/sessions" );
  }

  getSessionForStudentBySessionId(sessionId) {
    return this.http.get(BASE_URL + BACK_END_SERVICE_NAME + "student/session/" +  sessionId );
  }
}
