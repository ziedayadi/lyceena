import { Component, OnInit } from '@angular/core';
import { RefService } from 'src/app/services/ref.service';

@Component({
  selector: 'app-school-contact',
  templateUrl: './school-contact.component.html',
  styleUrls: ['./school-contact.component.css']
})
export class SchoolContactComponent implements OnInit {
  globalRef: any;

  constructor(private refService : RefService) { }

  ngOnInit(): void {
    this.refService.findGlobalRef().subscribe(r=>this.globalRef = r);
  }

  getSchoolInfo(code : String){
    if(this.globalRef){
      let param =  this.globalRef.filter(e=>e.code == code)[0]
      if(param) return param.value 
      else return 'NA'
    }
    else {
      return 'NA'
    }
  }

}
