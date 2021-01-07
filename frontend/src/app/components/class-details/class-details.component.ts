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
  selectedMenu : any; 
  constructor(private route: ActivatedRoute,
    private classesService : ClassesService) { }

    menueItems = [
      {
        code : 'students', text : 'Élèves'
      },
      {
        code : 'materials', text : 'Matières'
      },
      {
        code : 'timesheet', text : 'Emploi du temps'
      },
      {
        code : 'teachers', text : 'Enseignant'
      }
    ]

  ngOnInit(): void {
    const classId  = this.route.snapshot.paramMap.get('classId');
    this.classesService.findOne(classId).subscribe(r=>this.class = r);
    this.selectedMenu = this.menueItems.find(x=>x.code == 'students');
  }

  onSelectMenu(menuItem){
    console.log(menuItem)
    this.selectedMenu = menuItem; 
  }
}
