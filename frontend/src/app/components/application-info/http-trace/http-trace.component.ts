import { Component, OnInit } from '@angular/core';
import { ApplicationInfoService } from 'src/app/services/application-info.service';

@Component({
  selector: 'app-http-trace',
  templateUrl: './http-trace.component.html',
  styleUrls: ['./http-trace.component.css']
})
export class HttpTraceComponent implements OnInit {

  httpTrace : any; 

  constructor(private applicationInfoService : ApplicationInfoService) { }

  ngOnInit(): void {
    this.applicationInfoService.getHttpTraces().subscribe(r=>{this.httpTrace = r; console.log(this.httpTrace)})
  }

}
