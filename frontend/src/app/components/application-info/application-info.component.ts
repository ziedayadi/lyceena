import { Component, OnInit } from '@angular/core';
import { ApplicationInfoService } from 'src/app/services/application-info.service';
import { RefService } from 'src/app/services/ref.service';

@Component({
  selector: 'app-application-info',
  templateUrl: './application-info.component.html',
  styleUrls: ['./application-info.component.css']
})
export class ApplicationInfoComponent implements OnInit {

  buildInfo : any; 
  health : any;
  env : any; 
  globalRef : any;
  constructor(private applicationInfoService : ApplicationInfoService, 
    private refService : RefService) { }

  ngOnInit(): void {
    this.applicationInfoService.getInfo().subscribe(r=>this.buildInfo = r); 
    this.applicationInfoService.getHealth().subscribe(r=>this.health = r); 
    this.applicationInfoService.getEnv().subscribe(r=> this.env = r); 
    this.refService.findGlobalRef().subscribe(r=>this.globalRef=r)
  }
  

  getEnvirementValues(){
    let out =  this.env.propertySources.filter(e=>e.name.indexOf('application.properties') > -1)[0]
    return out; 
  }

  getProperty(propertyName){
    return this.getEnvirementValues().properties[propertyName].value
  }




}
