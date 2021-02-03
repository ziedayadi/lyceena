import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ParentsService } from 'src/app/services/parents.service';
import { StudentsService } from 'src/app/services/students.service';

@Component({
  selector: 'app-parent-details',
  templateUrl: './parent-details.component.html',
  styleUrls: ['./parent-details.component.css']
})
export class ParentDetailsComponent implements OnInit {

  parent: any;
  students: any;


  constructor(private route: ActivatedRoute,
    private parentService: ParentsService,
    private studentService: StudentsService) { }

  ngOnInit(): void {
    const parentId = this.route.snapshot.paramMap.get('parentId');
    this.parentService.findOne(parentId).subscribe(r => this.parent = r)
    this.studentService.findByParentId(parentId).subscribe(r => this.students = r); 
  }

}
