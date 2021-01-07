import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClassesService } from 'src/app/services/classes.service';

@Component({
  selector: 'app-class-details',
  templateUrl: './class-details.component.html',
  styleUrls: ['./class-details.component.css']
})
export class ClassDetailsComponent implements OnInit {

  class : any; 
  opened: boolean = true;
  constructor(private route: ActivatedRoute,
    private classesService : ClassesService) { }

  ngOnInit(): void {
    const classId  = this.route.snapshot.paramMap.get('classId');
    this.classesService.findOne(classId).subscribe(r=>this.class = r);
  }
}
