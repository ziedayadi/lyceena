import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClassLevelsRefService } from 'src/app/services/class-levels-ref.service';





@Component({
  selector: 'app-class-levels-ref-details',
  templateUrl: './class-levels-ref-details.component.html',
  styleUrls: ['./class-levels-ref-details.component.css']
})
export class ClassLevelsRefDetailsComponent implements OnInit {
  selectedMenu : any; 
  opened: boolean = true;
  classLevel : any; 

  menuItems = [
    {
      code : 'materials', text : 'Matières'
    },
  ]
  

  constructor(private route: ActivatedRoute, 
    private classLevelsRefService : ClassLevelsRefService) { }

  ngOnInit(): void {
    const classId  = this.route.snapshot.paramMap.get('levelId');
    this.selectedMenu = this.menuItems.find(x=>x.code == 'materials');
    this.classLevelsRefService.findById(classId).subscribe(r=>this.classLevel = r) ;
  }

  onSelectMenu(menuItem){
    this.selectedMenu = menuItem; 
  }

}
