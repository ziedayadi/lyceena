import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-teacher-serssion-details',
  templateUrl: './teacher-serssion.component.html',
  styleUrls: ['./teacher-serssion.component.css']
})
export class TeacherSerssionComponent implements OnInit {

  sessionId;


  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.sessionId = this.route.snapshot.paramMap.get('sessionId'); 
  }

}
