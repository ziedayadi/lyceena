import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-parent-details',
  templateUrl: './parent-details.component.html',
  styleUrls: ['./parent-details.component.css']
})
export class ParentDetailsComponent implements OnInit {
  
  parentId : any; 

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.parentId  = this.route.snapshot.paramMap.get('parentId');
  }

}
