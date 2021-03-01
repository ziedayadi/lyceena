import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-teacher-serssion',
  templateUrl: './teacher-serssion.component.html',
  styleUrls: ['./teacher-serssion.component.css']
})
export class TeacherSerssionComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    const sessionId = this.route.snapshot.paramMap.get('sessionId');
    console.log(sessionId); 
  }

}
