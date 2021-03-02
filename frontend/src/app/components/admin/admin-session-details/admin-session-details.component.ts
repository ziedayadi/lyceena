import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-admin-session-details',
  templateUrl: './admin-session-details.component.html',
  styleUrls: ['./admin-session-details.component.css']
})
export class AdminSessionDetailsComponent implements OnInit {

  sessionId;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.sessionId = this.route.snapshot.paramMap.get('sessionId'); 
  }

}
